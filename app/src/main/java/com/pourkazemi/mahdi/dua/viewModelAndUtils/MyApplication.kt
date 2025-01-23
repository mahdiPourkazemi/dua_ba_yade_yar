package com.pourkazemi.mahdi.dua.viewModelAndUtils

import android.app.Application
import com.pourkazemi.mahdi.dua.data.container.AppContainer
import com.pourkazemi.mahdi.dua.data.container.AppDataContainer

class MyApplication : Application() {
    // کانتینر برنامه
    val container: AppContainer by lazy { AppDataContainer(this) }
    // Preferences برنامه
    val preference: MyPreferences by lazy { MyPreferences.getInstance(this) }

}