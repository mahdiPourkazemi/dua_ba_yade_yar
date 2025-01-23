package com.pourkazemi.mahdi.dua.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
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

    //? sometime good practice to used and create this function
    //? but in this case we don't need it because of list wrapper
    @Transaction
    @Query("SELECT * FROM prayers WHERE id = :prayerId")
    fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText>
}
