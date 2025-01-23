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

    //? sometime good practice to used and create this function
    //? but in this case we don't need it because of list wrapper
    override fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText> =
        prayerTextDao.getPrayerWithTextsList(prayerId)

}
