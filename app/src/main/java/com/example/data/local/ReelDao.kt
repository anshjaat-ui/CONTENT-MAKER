package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReelDao {
    @Query("SELECT * FROM saved_reels ORDER BY savedAt DESC")
    fun getAllSavedReels(): Flow<List<ReelEntity>>

    @Query("SELECT * FROM saved_reels WHERE niche = :niche ORDER BY savedAt DESC")
    fun getSavedReelsByNiche(niche: String): Flow<List<ReelEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_reels WHERE id = :id)")
    fun isReelSavedFlow(id: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_reels WHERE id = :id)")
    suspend fun isReelSaved(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReel(reel: ReelEntity)

    @Query("DELETE FROM saved_reels WHERE id = :id")
    suspend fun deleteReelById(id: String)

    @Delete
    suspend fun deleteReel(reel: ReelEntity)
}
