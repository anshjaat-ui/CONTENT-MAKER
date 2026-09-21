package com.example.data.repository

import com.example.data.local.ReelDao
import com.example.data.local.ReelEntity
import com.example.data.model.ReelIdea
import com.example.data.model.ReelIdeaResponse
import com.example.data.remote.GeminiApiService
import kotlinx.coroutines.flow.Flow

class ReelRepository(
    private val reelDao: ReelDao,
    private val geminiApiService: GeminiApiService = GeminiApiService()
) {

    val allSavedReels: Flow<List<ReelEntity>> = reelDao.getAllSavedReels()

    fun isReelSavedFlow(id: String): Flow<Boolean> = reelDao.isReelSavedFlow(id)

    suspend fun saveReel(niche: String, idea: ReelIdea) {
        val entity = ReelEntity.fromReelIdea(niche, idea)
        reelDao.insertReel(entity)
    }

    suspend fun removeReel(id: String) {
        reelDao.deleteReelById(id)
    }

    suspend fun generateReelIdeas(
        niche: String,
        targetAudience: String = "",
        language: String = "Hinglish",
        followerCount: String = "",
        pastReelsContext: String = ""
    ): Result<ReelIdeaResponse> {
        return geminiApiService.generateReelIdeas(
            niche = niche,
            targetAudience = targetAudience,
            language = language,
            followerCount = followerCount,
            pastReelsContext = pastReelsContext
        )
    }
}
