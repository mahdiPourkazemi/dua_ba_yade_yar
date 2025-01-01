package com.pourkazemi.mahdi.dua

import android.app.Application
import com.pourkazemi.mahdi.dua.data.container.AppContainer
import com.pourkazemi.mahdi.dua.data.container.AppDataContainer

class MyApplication : Application() {

    // اینجا کانتینر برنامه را تعریف می‌کنیم
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        // ایجاد نمونه از کانتینر در زمان شروع برنامه
        container = AppDataContainer(this)
    }
}