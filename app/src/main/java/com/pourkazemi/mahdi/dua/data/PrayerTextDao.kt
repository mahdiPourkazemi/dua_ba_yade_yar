package com.pourkazemi.mahdi.dua.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pourkazemi.mahdi.dua.data.model.prayertext
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerTextDao {

    @Query("SELECT * FROM prayertext")
    fun getAllPrayerTexts(): Flow<List<prayertext>>

    @Query("SELECT * FROM prayertext WHERE id = :textId")
    fun getPrayerTextById(textId: Int): Flow<prayertext?>

    @Query("SELECT * FROM prayertext WHERE prayerid = :prayerId")
    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<prayertext>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerText(prayerText: prayertext)

    @Update
    suspend fun updatePrayerText(prayerText: prayertext)

    @Delete
    suspend fun deletePrayerText(prayerText: prayertext)
}
