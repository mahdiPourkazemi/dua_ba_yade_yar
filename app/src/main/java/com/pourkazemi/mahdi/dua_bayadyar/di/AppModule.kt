package com.pourkazemi.mahdi.dua_bayadyar.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.pourkazemi.mahdi.dua_bayadyar.data.container.AppContainer
import com.pourkazemi.mahdi.dua_bayadyar.data.container.AppDataContainer
import com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils.MyPreferencesImp
import com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils.PrayersViewModel
import com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils.viewModelFactory

interface AppModule{
    val container:AppContainer
    val preferences:MyPreferencesImp
    val myViewModelFactory:ViewModelProvider.Factory
}
class AppModuleImp(
    private val appContext:Context
):AppModule{
    // کانتینر برنامه
    override val container: AppContainer by lazy { AppDataContainer.getInstance(appContext) }
    // Preferences برنامه
    override val preferences: MyPreferencesImp by lazy { MyPreferencesImp.getInstance(appContext) }

    override val myViewModelFactory: ViewModelProvider.Factory by lazy {
        viewModelFactory{
            PrayersViewModel(
                repository = container.prayerRepository,
                myPreferencesImp = preferences
            )
        }
    }
}
