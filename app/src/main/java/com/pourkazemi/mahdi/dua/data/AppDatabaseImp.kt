package com.pourkazemi.mahdi.dua.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pourkazemi.mahdi.dua.data.dao.PrayerTextDao
import com.pourkazemi.mahdi.dua.data.dao.PrayersDao
import com.pourkazemi.mahdi.dua.data.model.Prayertext
import com.pourkazemi.mahdi.dua.data.model.Prayers

@Database(
    entities = [Prayers::class, Prayertext::class],
    exportSchema = true, // Good practice for version control
    version = 1)
abstract class AppDatabaseImp : RoomDatabase() {
    abstract fun prayersDao(): PrayersDao
    abstract fun prayerTextDao(): PrayerTextDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseImp? = null

        fun getDatabase(context: Context): AppDatabaseImp {
            return INSTANCE ?: synchronized(this) {
                 Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabaseImp::class.java,
                    "dua.sqlite" // نام فایل SQLite موجود شما
                ).createFromAsset("dua.sqlite")
                    .fallbackToDestructiveMigration() // از اینجا می‌توانید مهاجرت تخریبی استفاده کنید
                    .build().also { INSTANCE=it }
            }
        }
    }
}
