package com.pourkazemi.mahdi.dua_bayadyar.data.dao

import androidx.room.*
import androidx.room.Query
import androidx.room.Transaction
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerText
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerWithText
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerTextDao {

    @Query("SELECT * FROM prayertext")
    fun getAllPrayerTexts(): Flow<List<PrayerText>>

    @Query("SELECT * FROM prayertext WHERE id = :textId")
    fun getPrayerTextById(textId: Int): Flow<PrayerText?>

    @Query("SELECT * FROM prayertext WHERE prayerid = :prayerId")
    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>>

    @Transaction
    @Query("SELECT * FROM prayers WHERE id = :prayerId")
    fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText>
}
