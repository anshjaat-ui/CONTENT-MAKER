package com.example.data.remote

import android.util.Log
import com.example.BuildConfig
import com.example.data.model.ReelIdea
import com.example.data.model.ReelIdeaResponse
import com.example.data.model.ReelScript
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID
import java.util.concurrent.TimeUnit

class GeminiApiService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun generateReelIdeas(
        niche: String,
        targetAudience: String = "",
        language: String = "Hinglish",
        followerCount: String = "",
        pastReelsContext: String = ""
    ): Result<ReelIdeaResponse> = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        // If no valid API key configured, use the high quality curated preset engine
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            Log.d("GeminiApiService", "Using curated Reel Guru vault (API key not configured in secrets)")
            return@withContext Result.success(PresetReelVault.getPresetForNiche(niche, language))
        }

        try {
            val systemPrompt = """
Tum ek expert short-form content strategist ho jiska naam hai "Reel Guru". Tumhara kaam hai user ke niche ke hisaab se daily trending, high-engagement Instagram/YouTube Reels ideas generate karna — jo sirf "ideas" na ho, balki turant shoot karne layak ready scripts hon.

INPUT:
Niche: $niche
Target Audience: ${if (targetAudience.isBlank()) "General viewers & enthusiasts" else targetAudience}
Language: $language
Follower Stage: ${if (followerCount.isBlank()) "Growing creator" else followerCount}
Past Context: ${if (pastReelsContext.isBlank()) "None" else pastReelsContext}

OUTPUT REQUIREMENT:
Har baar EXACTLY 3 alag-alag reel ideas do. Teeno ideas different angles se hone chahiye — kabhi 3 same-type ideas mat do.
- Format diversity: kam se kam ek trending audio/format-based, ek storytelling/personal, ek educational/listicle style.
- Difficulty diversity: kam se kam ek "easy to shoot today" (no props/editing skill needed), doosre thode zyada production wale ho sakte hain.
- Hook diversity: alag-alag hook types use karo (question hook, shocking stat hook, relatable pain-point hook, controversial opinion hook).

HOOK LINE RULES:
- Pehle 3 words mein curiosity/emotion trigger karo. Generic intros "Aaj main aapko bataunga" strictly prohibited.
- Proven patterns: Pattern interrupt, Curiosity gap, Bold claim, Relatable callout, Direct value promise.
- Language: Follow $language strictly.

OUTPUT STRICT JSON ONLY (no markdown code fences, pure json):
{
  "niche": "$niche",
  "date_context": "seasonal / trending algorithmic context",
  "ideas": [
    {
      "idea_title": "Short catchy title",
      "hook_line": "Exact words for first 3 seconds",
      "trending_element": "Audio / transition / format recommendation",
      "script": {
        "0-3s": "Visual description + spoken line",
        "3-15s": "Visual description + spoken line",
        "15-25s": "Visual description + spoken line",
        "25-30s": "Visual description + spoken line"
      },
      "caption": "1-2 line caption",
      "hashtags": ["#tag1", "#tag2", "#tag3", "#tag4"],
      "why_it_works": "1 line algorithmic reason",
      "format_type": "Trending Audio / Storytelling / Educational Listicle",
      "difficulty": "Easy Shoot Today / Medium Production",
      "hook_type": "Pattern Interrupt / Curiosity Gap / Bold Claim / Relatable Callout"
    }
  ]
}
            """.trimIndent()

            val requestJson = JSONObject().apply {
                val contentsArray = JSONArray().apply {
                    val contentObj = JSONObject().apply {
                        val partsArray = JSONArray().apply {
                            val partObj = JSONObject().apply {
                                put("text", systemPrompt)
                            }
                            put(partObj)
                        }
                        put("parts", partsArray)
                    }
                    put(contentObj)
                }
                put("contents", contentsArray)

                val generationConfig = JSONObject().apply {
                    put("temperature", 0.7)
                    put("response_mime_type", "application/json")
                }
                put("generationConfig", generationConfig)
            }

            val requestBody = requestJson.toString().toRequestBody("application/json".toMediaType())
            val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$apiKey"

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string() ?: ""

            if (!response.isSuccessful) {
                Log.w("GeminiApiService", "Gemini API error: ${response.code} - $responseBody. Falling back to vault.")
                return@withContext Result.success(PresetReelVault.getPresetForNiche(niche, language))
            }

            val parsedResponse = parseGeminiResponse(responseBody, niche)
            Result.success(parsedResponse)
        } catch (e: Exception) {
            Log.e("GeminiApiService", "Exception calling Gemini API: ${e.message}. Using fallback vault.", e)
            Result.success(PresetReelVault.getPresetForNiche(niche, language))
        }
    }

    private fun parseGeminiResponse(rawJsonString: String, fallbackNiche: String): ReelIdeaResponse {
        try {
            val rootJson = JSONObject(rawJsonString)
            val candidates = rootJson.optJSONArray("candidates")
            val firstCandidate = candidates?.optJSONObject(0)
            val content = firstCandidate?.optJSONObject("content")
            val parts = content?.optJSONArray("parts")
            val rawText = parts?.optJSONObject(0)?.optString("text") ?: ""

            val cleanedJsonText = cleanJsonString(rawText)
            val jsonObject = JSONObject(cleanedJsonText)

            val niche = jsonObject.optString("niche", fallbackNiche)
            val dateContext = jsonObject.optString("date_context", "Trending on Instagram & YouTube Shorts")
            val ideasArray = jsonObject.optJSONArray("ideas") ?: JSONArray()

            val ideasList = mutableListOf<ReelIdea>()
            for (i in 0 until ideasArray.length()) {
                val ideaObj = ideasArray.optJSONObject(i) ?: continue
                val scriptObj = ideaObj.optJSONObject("script")

                val hashtagsList = mutableListOf<String>()
                val tagsArr = ideaObj.optJSONArray("hashtags")
                if (tagsArr != null) {
                    for (j in 0 until tagsArr.length()) {
                        hashtagsList.add(tagsArr.optString(j))
                    }
                }

                ideasList.add(
                    ReelIdea(
                        id = UUID.randomUUID().toString(),
                        ideaTitle = ideaObj.optString("idea_title", "Viral Reel Idea #${i + 1}"),
                        hookLine = ideaObj.optString("hook_line", "Pehele 3 seconds mein curiosity trigger karo"),
                        trendingElement = ideaObj.optString("trending_element", "Trending upbeat audio with beat drop"),
                        script = ReelScript(
                            sec0_3 = scriptObj?.optString("0-3s", "") ?: "",
                            sec3_15 = scriptObj?.optString("3-15s", "") ?: "",
                            sec15_25 = scriptObj?.optString("15-25s", "") ?: "",
                            sec25_30 = scriptObj?.optString("25-30s", "") ?: ""
                        ),
                        caption = ideaObj.optString("caption", ""),
                        hashtags = if (hashtagsList.isEmpty()) listOf("#TrendingReels", "#ViralHacks", "#ReelGuru") else hashtagsList,
                        whyItWorks = ideaObj.optString("why_it_works", "Strong hook pattern ensures high retention"),
                        formatType = ideaObj.optString("format_type", when (i) {
                            0 -> "Trending Audio / Transition"
                            1 -> "Relatable Storytelling / POV"
                            else -> "Educational / Quick Listicle"
                        }),
                        difficulty = ideaObj.optString("difficulty", when (i) {
                            0 -> "Easy Shoot Today (No props)"
                            1 -> "Medium Production"
                            else -> "Easy Shoot Today"
                        }),
                        hookType = ideaObj.optString("hook_type", when (i) {
                            0 -> "Pattern Interrupt"
                            1 -> "Relatable Callout"
                            else -> "Curiosity Gap"
                        })
                    )
                )
            }

            if (ideasList.isNotEmpty()) {
                return ReelIdeaResponse(niche = niche, dateContext = dateContext, ideas = ideasList)
            }
        } catch (e: Exception) {
            Log.w("GeminiApiService", "Failed to parse API output: ${e.message}")
        }
        return PresetReelVault.getPresetForNiche(fallbackNiche)
    }

    private fun cleanJsonString(raw: String): String {
        var clean = raw.trim()
        if (clean.startsWith("```json")) {
            clean = clean.removePrefix("```json")
        } else if (clean.startsWith("```")) {
            clean = clean.removePrefix("```")
        }
        if (clean.endsWith("```")) {
            clean = clean.removeSuffix("```")
        }
        return clean.trim()
    }
}
