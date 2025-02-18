package com.pourkazemi.mahdi.dua_bayadyar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayerTextDao
import com.pourkazemi.mahdi.dua_bayadyar.data.dao.PrayersDao
import com.pourkazemi.mahdi.dua_bayadyar.data.model.PrayerText
import com.pourkazemi.mahdi.dua_bayadyar.data.model.Prayers

@Database(
    entities = [Prayers::class, PrayerText::class],
    exportSchema = true,
    version = 1)
abstract class AppDatabaseImp : RoomDatabase() {
    abstract fun prayersDao(): PrayersDao
    abstract fun prayerTextDao(): PrayerTextDao

/*    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseImp? = null

        fun getDatabase(context: Context): AppDatabaseImp {
            return INSTANCE ?: synchronized(this) {
                 Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabaseImp::class.java,
                    "dua.sqlite"
                ).createFromAsset("dua.sqlite")
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE=it }
            }
        }
    }*/
}
