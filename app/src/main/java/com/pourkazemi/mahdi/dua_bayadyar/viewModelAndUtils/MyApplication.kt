package com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils

import android.app.Application
import com.pourkazemi.mahdi.dua_bayadyar.data.container.AppContainer
import com.pourkazemi.mahdi.dua_bayadyar.data.container.AppDataContainer
import com.pourkazemi.mahdi.dua_bayadyar.di.AppModule
import com.pourkazemi.mahdi.dua_bayadyar.di.AppModuleImp

class MyApplication : Application() {
  //val appInject by lazy { AppModuleImp(this) }
  companion object{
    lateinit var appModule: AppModule
  }

  override fun onCreate() {
    super.onCreate()
    appModule = AppModuleImp(this@MyApplication)
  }
}