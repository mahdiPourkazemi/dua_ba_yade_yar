package com.pourkazemi.mahdi.dua.data.repository

import com.pourkazemi.mahdi.dua.data.dao.PrayerTextDao
import com.pourkazemi.mahdi.dua.data.dao.PrayersDao
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.PrayerWithText
import com.pourkazemi.mahdi.dua.data.model.Prayers
import kotlinx.coroutines.flow.Flow

class PrayersRepository(
    private val prayersDao: PrayersDao,
    private val prayerTextDao: PrayerTextDao
):PrayerRepository {

    override val allPrayers: Flow<List<Prayers>> = prayersDao.getAllPrayers()

    override fun getPrayerById(prayerId: Int): Flow<Prayers?> = prayersDao.getPrayerById(prayerId)

    override fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>> =
        prayerTextDao.getPrayerTextsByPrayerId(prayerId)

    //#Todo check speed of this query instead of above query
    override fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText> =
        prayerTextDao.getPrayerWithTextsList(prayerId)
    //

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
