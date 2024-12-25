package com.pourkazemi.mahdi.dua

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.dua.data.model.Prayertext
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.data.repository.PrayersRepository
import kotlinx.coroutines.launch

class PrayersViewModel(private val repository: PrayersRepository) : ViewModel() {

    val allPrayers: LiveData<List<Prayers>> = repository.allPrayers.asLiveData()

    fun getPrayerById(prayerId: Int): LiveData<Prayers?> =
        repository.getPrayerById(prayerId).asLiveData()

    fun getPrayerTextsByPrayerId(prayerId: Int): LiveData<List<Prayertext>> =
        repository.getPrayerTextsByPrayerId(prayerId).asLiveData()

    fun insertPrayer(prayer: Prayers) = viewModelScope.launch {
        repository.insertPrayer(prayer)
    }

    fun updatePrayer(prayer: Prayers) = viewModelScope.launch {
        repository.updatePrayer(prayer)
    }

    fun deletePrayer(prayer: Prayers) = viewModelScope.launch {
        repository.deletePrayer(prayer)
    }
}
