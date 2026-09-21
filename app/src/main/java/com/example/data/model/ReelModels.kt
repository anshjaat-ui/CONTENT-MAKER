package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class ReelIdeaResponse(
    @Json(name = "niche") val niche: String = "",
    @Json(name = "date_context") val dateContext: String? = null,
    @Json(name = "ideas") val ideas: List<ReelIdea> = emptyList()
)

@JsonClass(generateAdapter = true)
data class ReelIdea(
    val id: String = UUID.randomUUID().toString(),
    @Json(name = "idea_title") val ideaTitle: String = "",
    @Json(name = "hook_line") val hookLine: String = "",
    @Json(name = "trending_element") val trendingElement: String = "",
    @Json(name = "script") val script: ReelScript = ReelScript(),
    @Json(name = "caption") val caption: String = "",
    @Json(name = "hashtags") val hashtags: List<String> = emptyList(),
    @Json(name = "why_it_works") val whyItWorks: String = "",
    val formatType: String = "",
    val difficulty: String = "",
    val hookType: String = "",
    val isSaved: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

@JsonClass(generateAdapter = true)
data class ReelScript(
    @Json(name = "0-3s") val sec0_3: String = "",
    @Json(name = "3-15s") val sec3_15: String = "",
    @Json(name = "15-25s") val sec15_25: String = "",
    @Json(name = "25-30s") val sec25_30: String = ""
)
