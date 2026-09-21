package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.ReelIdea
import com.example.data.model.ReelScript

@Entity(tableName = "saved_reels")
data class ReelEntity(
    @PrimaryKey val id: String,
    val niche: String,
    val ideaTitle: String,
    val hookLine: String,
    val trendingElement: String,
    val scriptSec0_3: String,
    val scriptSec3_15: String,
    val scriptSec15_25: String,
    val scriptSec25_30: String,
    val caption: String,
    val hashtags: String,
    val whyItWorks: String,
    val formatType: String,
    val difficulty: String,
    val hookType: String,
    val savedAt: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
) {
    fun toReelIdea(): ReelIdea {
        return ReelIdea(
            id = id,
            ideaTitle = ideaTitle,
            hookLine = hookLine,
            trendingElement = trendingElement,
            script = ReelScript(
                sec0_3 = scriptSec0_3,
                sec3_15 = scriptSec3_15,
                sec15_25 = scriptSec15_25,
                sec25_30 = scriptSec25_30
            ),
            caption = caption,
            hashtags = if (hashtags.isBlank()) emptyList() else hashtags.split(" ").filter { it.isNotBlank() },
            whyItWorks = whyItWorks,
            formatType = formatType,
            difficulty = difficulty,
            hookType = hookType,
            isSaved = true,
            timestamp = savedAt
        )
    }

    companion object {
        fun fromReelIdea(niche: String, idea: ReelIdea): ReelEntity {
            return ReelEntity(
                id = idea.id,
                niche = niche,
                ideaTitle = idea.ideaTitle,
                hookLine = idea.hookLine,
                trendingElement = idea.trendingElement,
                scriptSec0_3 = idea.script.sec0_3,
                scriptSec3_15 = idea.script.sec3_15,
                scriptSec15_25 = idea.script.sec15_25,
                scriptSec25_30 = idea.script.sec25_30,
                caption = idea.caption,
                hashtags = idea.hashtags.joinToString(" "),
                whyItWorks = idea.whyItWorks,
                formatType = idea.formatType,
                difficulty = idea.difficulty,
                hookType = idea.hookType,
                savedAt = idea.timestamp
            )
        }
    }
}
