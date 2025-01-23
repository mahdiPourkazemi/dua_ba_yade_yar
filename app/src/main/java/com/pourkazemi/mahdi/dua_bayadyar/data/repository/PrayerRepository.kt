package com.pourkazemi.mahdi.dua_bayadyar.data.repository

import com.pourkazemi.mahdi.dua_bayadyar.data.model.Prayers
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerText
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerWithText
import kotlinx.coroutines.flow.Flow

interface PrayerRepository {

    val allPrayers: Flow<List<Prayers>>

    fun getPrayerById(prayerId: Int): Flow<Prayers?>

    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>>

    //? check speed of this query instead of above query(model)
    fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText>

}