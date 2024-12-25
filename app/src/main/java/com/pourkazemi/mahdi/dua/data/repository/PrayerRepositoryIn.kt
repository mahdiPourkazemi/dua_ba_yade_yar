package com.pourkazemi.mahdi.dua.data.repository

import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.data.model.Prayertext
import kotlinx.coroutines.flow.Flow

interface PrayerRepositoryIn {

    val allPrayers: Flow<List<Prayers>>

    fun getPrayerById(prayerId: Int): Flow<Prayers?>

    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<Prayertext>>

    suspend fun insertPrayer(prayer: Prayers)

    suspend fun updatePrayer(prayer: Prayers)

    suspend fun deletePrayer(prayer: Prayers)
}