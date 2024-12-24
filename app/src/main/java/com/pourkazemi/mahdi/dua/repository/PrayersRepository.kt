package com.pourkazemi.mahdi.dua.repository

import com.pourkazemi.mahdi.dua.data.PrayerTextDao
import com.pourkazemi.mahdi.dua.data.PrayersDao
import com.pourkazemi.mahdi.dua.data.model.prayertext
import com.pourkazemi.mahdi.dua.data.model.prayers
import kotlinx.coroutines.flow.Flow

class PrayersRepository(private val prayersDao: PrayersDao, private val prayerTextDao: PrayerTextDao) {

    val allPrayers: Flow<List<prayers>> = prayersDao.getAllPrayers()

    fun getPrayerById(prayerId: Int): Flow<prayers?> = prayersDao.getPrayerById(prayerId)

    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<prayertext>> =
        prayerTextDao.getPrayerTextsByPrayerId(prayerId)

    suspend fun insertPrayer(prayer: prayers) {
        prayersDao.insertPrayer(prayer)
    }

    suspend fun updatePrayer(prayer: prayers) {
        prayersDao.updatePrayer(prayer)
    }

    suspend fun deletePrayer(prayer: prayers) {
        prayersDao.deletePrayer(prayer)
    }
}
