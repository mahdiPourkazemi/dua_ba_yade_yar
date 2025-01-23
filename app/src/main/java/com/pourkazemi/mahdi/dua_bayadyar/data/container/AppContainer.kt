package com.pourkazemi.mahdi.dua_bayadyar.data.container

import android.content.Context
import com.pourkazemi.mahdi.dua_bayadyar.data.AppDatabaseImp
import com.pourkazemi.mahdi.dua_bayadyar.data.repository.PrayerRepository
import com.pourkazemi.mahdi.dua_bayadyar.data.repository.PrayersRepository

interface AppContainer {
    val prayerRepository: PrayerRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val prayerRepository: PrayerRepository by lazy {
        PrayersRepository(
            AppDatabaseImp.getDatabase(context).prayersDao(),
            AppDatabaseImp.getDatabase(context).prayerTextDao()
        )
    }
}
