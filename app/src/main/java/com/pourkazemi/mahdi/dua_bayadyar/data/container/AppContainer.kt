package com.pourkazemi.mahdi.dua_bayadyar.data.container

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.pourkazemi.mahdi.dua_bayadyar.data.AppDatabaseImp
import com.pourkazemi.mahdi.dua_bayadyar.data.cache.PrayerCache
import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayerTextDao
import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayersDao
import com.pourkazemi.mahdi.dua_bayadyar.data.repository.PrayerRepository
import com.pourkazemi.mahdi.dua_bayadyar.data.repository.PrayersRepository
import kotlinx.coroutines.Dispatchers

interface AppContainer {
    val prayersDao: PrayersDao
    val prayerTextDao: PrayerTextDao
    val prayerCache: PrayerCache
    val prayerRepository: PrayerRepository
}

class AppDataContainer private constructor(context: Context) : AppContainer {

    private val database: AppDatabaseImp by lazy {
        AppDatabaseImp.getDatabase(context.applicationContext)
    }

    override val prayersDao: PrayersDao by lazy { database.prayersDao() }
    override val prayerTextDao: PrayerTextDao by lazy { database.prayerTextDao() }
    override val prayerCache: PrayerCache by lazy { PrayerCache() }

    override val prayerRepository: PrayerRepository by lazy {
        PrayersRepository(
            prayersDao,
            prayerTextDao,
            prayerCache,
            Dispatchers.IO
        )
    }

    companion object {
        @Volatile
        private var instance: AppContainer? = null

        fun getInstance(context: Context): AppContainer {
            return instance ?: synchronized(this) {
                instance ?: AppDataContainer(context.applicationContext).also { instance = it }
            }
        }

        @VisibleForTesting
        fun destroyInstance() {
            instance = null
        }
    }
}