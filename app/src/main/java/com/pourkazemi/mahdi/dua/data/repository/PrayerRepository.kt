package com.pourkazemi.mahdi.dua.data.repository

import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.PrayerWithText
import kotlinx.coroutines.flow.Flow

interface PrayerRepository {

    val allPrayers: Flow<List<Prayers>>

    fun getPrayerById(prayerId: Int): Flow<Prayers?>

    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>>

    //#Todo check speed of this query instead of above query(model)
    fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText>
    //

    suspend fun insertPrayer(prayer: Prayers)

    suspend fun updatePrayer(prayer: Prayers)

    suspend fun deletePrayer(prayer: Prayers)
}