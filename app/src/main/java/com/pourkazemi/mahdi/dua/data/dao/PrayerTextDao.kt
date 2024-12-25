package com.pourkazemi.mahdi.dua.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pourkazemi.mahdi.dua.data.model.Prayertext
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerTextDao {

    @Query("SELECT * FROM prayertext")
    fun getAllPrayerTexts(): Flow<List<Prayertext>>

    @Query("SELECT * FROM prayertext WHERE id = :textId")
    fun getPrayerTextById(textId: Int): Flow<Prayertext?>

    @Query("SELECT * FROM prayertext WHERE prayerid = :prayerId")
    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<Prayertext>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerText(prayerText: Prayertext)

    @Update
    suspend fun updatePrayerText(prayerText: Prayertext)

    @Delete
    suspend fun deletePrayerText(prayerText: Prayertext)
}
