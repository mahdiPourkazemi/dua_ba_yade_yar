package com.pourkazemi.mahdi.dua_bayadyar.data.repository

import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayerTextDao
import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayersDao
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerText
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerWithText
import com.pourkazemi.mahdi.dua_bayadyar.data.model.Prayers
import com.pourkazemi.mahdi.dua_bayadyar.data.cache.PrayerCache
import com.pourkazemi.mahdi.dua_bayadyar.util.AppError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last

class PrayersRepository (
    private val prayersDao: PrayersDao,
    private val prayerTextDao: PrayerTextDao,
    private val prayerCache: PrayerCache,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
):PrayerRepository {

    override val allPrayers: Flow<List<Prayers>> = prayersDao.getAllPrayers()


    override fun getPrayerById(prayerId: Int): Flow<Result<Any>> = flow {
        try {
            // Try cache first
            val cached = prayerCache.get(prayerId)
            if (cached != null) {
                emit(Result.success(cached))
                return@flow
            }

            // Fetch from database (without withContext)
            val prayer = prayerTextDao.getPrayerTextById(prayerId)

            prayer.last()?.let { prayerCache.put(prayerId, it) }
            emit(Result.success(prayer))
        } catch (e: Exception) {
            emit(Result.failure(handleError(e)))
        }
    }.flowOn(dispatcher) // Dispatching to IO thread

    private fun handleError(exception: Exception): AppError {
        return when (exception) {
            is AppError -> exception
            is java.net.UnknownHostException -> AppError.NetworkError
            else -> AppError.DatabaseError(exception)
        }
    }

    override fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>> =
        prayerTextDao.getPrayerTextsByPrayerId(prayerId)

    //? sometime good practice to used and create this function
    //? but in this case we don't need it because of list wrapper
    override fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText> =
        prayerTextDao.getPrayerWithTextsList(prayerId)

}
