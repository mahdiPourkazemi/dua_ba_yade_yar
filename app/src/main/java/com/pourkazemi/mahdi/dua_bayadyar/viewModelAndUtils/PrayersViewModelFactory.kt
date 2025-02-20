package com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pourkazemi.mahdi.dua_bayadyar.data.repository.PrayerRepository

/*class PrayersViewModelFactory(
    private val repository: PrayerRepository,
    private val myPreferences: MyPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrayersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrayersViewModel(repository,myPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
fun <VM : ViewModel> viewModelFactory(initializer : () -> VM):ViewModelProvider.Factory{
    return object :ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }
}