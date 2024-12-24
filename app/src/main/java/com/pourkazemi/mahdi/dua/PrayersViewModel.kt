package com.pourkazemi.mahdi.dua

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.dua.data.model.prayertext
import com.pourkazemi.mahdi.dua.data.model.prayers
import com.pourkazemi.mahdi.dua.repository.PrayersRepository
import kotlinx.coroutines.launch

class PrayersViewModel(private val repository: PrayersRepository) : ViewModel() {

    val allPrayers: LiveData<List<prayers>> = repository.allPrayers.asLiveData()

    fun getPrayerById(prayerId: Int): LiveData<prayers?> =
        repository.getPrayerById(prayerId).asLiveData()

    fun getPrayerTextsByPrayerId(prayerId: Int): LiveData<List<prayertext>> =
        repository.getPrayerTextsByPrayerId(prayerId).asLiveData()

    fun insertPrayer(prayer: prayers) = viewModelScope.launch {
        repository.insertPrayer(prayer)
    }

    fun updatePrayer(prayer: prayers) = viewModelScope.launch {
        repository.updatePrayer(prayer)
    }

    fun deletePrayer(prayer: prayers) = viewModelScope.launch {
        repository.deletePrayer(prayer)
    }
}
