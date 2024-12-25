package com.pourkazemi.mahdi.dua.data.repository

import com.pourkazemi.mahdi.dua.data.dao.PrayerTextDao
import com.pourkazemi.mahdi.dua.data.dao.PrayersDao
import com.pourkazemi.mahdi.dua.data.model.Prayertext
import com.pourkazemi.mahdi.dua.data.model.Prayers
import kotlinx.coroutines.flow.Flow

class PrayersRepository(
    private val prayersDao: PrayersDao,
    private val prayerTextDao: PrayerTextDao
):PrayerRepositoryIn {

    override val allPrayers: Flow<List<Prayers>> = prayersDao.getAllPrayers()

    override fun getPrayerById(prayerId: Int): Flow<Prayers?> = prayersDao.getPrayerById(prayerId)

    override fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<Prayertext>> =
        prayerTextDao.getPrayerTextsByPrayerId(prayerId)

    override suspend fun insertPrayer(prayer: Prayers) {
        prayersDao.insertPrayer(prayer)
    }

    override suspend fun updatePrayer(prayer: Prayers) {
        prayersDao.updatePrayer(prayer)
    }

    override suspend fun deletePrayer(prayer: Prayers) {
        prayersDao.deletePrayer(prayer)
    }
}
