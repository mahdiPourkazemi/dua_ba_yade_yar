package com.pourkazemi.mahdi.dua.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.PrayerWithText
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerTextDao {

    @Query("SELECT * FROM prayertext")
    fun getAllPrayerTexts(): Flow<List<PrayerText>>

    @Query("SELECT * FROM prayertext WHERE id = :textId")
    fun getPrayerTextById(textId: Int): Flow<PrayerText?>

    @Query("SELECT * FROM prayertext WHERE prayerid = :prayerId")
    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>>

    //#Todo check speed of this query instead of above query
    @Transaction
    @Query("SELECT * FROM prayers WHERE id = :prayerId")
    fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText>
    //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerText(prayerText: PrayerText)

    @Update
    suspend fun updatePrayerText(prayerText: PrayerText)

    @Delete
    suspend fun deletePrayerText(prayerText: PrayerText)
}
