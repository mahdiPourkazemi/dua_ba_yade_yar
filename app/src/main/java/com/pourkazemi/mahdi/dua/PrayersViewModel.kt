package com.pourkazemi.mahdi.dua

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.PrayerWithText
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.data.repository.PrayerRepository
import kotlinx.coroutines.launch

class PrayersViewModel(private val repository: PrayerRepository) : ViewModel() {

    val allPrayers: LiveData<List<Prayers>> = repository.allPrayers.asLiveData()

    fun getPrayerById(prayerId: Int): LiveData<Prayers?> =
        repository.getPrayerById(prayerId).asLiveData()

    fun getPrayerTextsByPrayerId(prayerId: Int): LiveData<List<PrayerText>> =
        repository.getPrayerTextsByPrayerId(prayerId).asLiveData()

    //#Todo check speed of this query instead of above query(model)
    fun getPrayerWithTextsList(prayerId: Int): LiveData<PrayerWithText> =
        repository.getPrayerWithTextsList(prayerId).asLiveData()
    //

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
