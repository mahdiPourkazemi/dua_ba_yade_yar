package com.pourkazemi.mahdi.dua

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pourkazemi.mahdi.dua.data.repository.PrayerRepository

class PrayersViewModelFactory(private val repository: PrayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrayersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrayersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
