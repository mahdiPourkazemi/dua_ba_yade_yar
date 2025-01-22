package com.pourkazemi.mahdi.dua.viewModelAndUtils

import android.app.Application
import com.pourkazemi.mahdi.dua.data.container.AppContainer
import com.pourkazemi.mahdi.dua.data.container.AppDataContainer

class MyApplication : Application() {

    // اینجا کانتینر برنامه را تعریف می‌کنیم
/*    lateinit var container: AppContainer
        private set
    lateinit var preference: MyPreferences
        private set*/
    // کانتینر برنامه
    val container: AppContainer by lazy { AppDataContainer(this) }

    // Preferences برنامه
    val preference: MyPreferences by lazy { MyPreferences.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        // ایجاد نمونه از کانتینر در زمان شروع برنامه
        //container = AppDataContainer(this)
        //preference= MyPreferences.getInstance(this)
    }
}