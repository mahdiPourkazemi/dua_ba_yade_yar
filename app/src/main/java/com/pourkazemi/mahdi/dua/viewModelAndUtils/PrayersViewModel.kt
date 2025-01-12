package com.pourkazemi.mahdi.dua.viewModelAndUtils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.PrayerWithText
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.data.repository.PrayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PrayersViewModel(private val repository: PrayerRepository) : ViewModel() {

    init {
        //#Todo check stateFlow and call fun in init for allPrayers
        loadAllPrayers()
    }

    //val allPrayers: LiveData<List<Prayers>> = repository.allPrayers.asLiveData()

    private var _allPrayersState = MutableStateFlow<List<Prayers>>(emptyList())
    val allPrayersState: StateFlow<List<Prayers>> = _allPrayersState.asStateFlow()

    fun loadAllPrayers() {
        viewModelScope.launch {
            try {
                repository.allPrayers.collect { prayers ->
                    _allPrayersState.value = prayers
                } // Assuming repository.allPrayers() is a suspending function
            } catch (e: Exception) {
                Log.e("PrayerViewModel", "Error loading prayers", e)
                _allPrayersState.value = emptyList() // Or handle the error differently
            }
        }
    }


    fun getPrayerById(prayerId: Int): LiveData<Prayers?> =
        repository.getPrayerById(prayerId).asLiveData()

    fun getPrayerTextsByPrayerId(prayerId: Int): LiveData<List<PrayerText>> =
        repository.getPrayerTextsByPrayerId(prayerId).asLiveData()

    //#Todo check speed of this query instead of above query(model)
    fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText> =
        repository.getPrayerWithTextsList(prayerId)
    //

    private val _prayerUiState = MutableStateFlow<PrayerUiState>(PrayerUiState.Loading)
    val prayerUiState: StateFlow<PrayerUiState> = _prayerUiState

    // دریافت داده از دیتابیس و ذخیره در StateFlow
    fun loadPrayer(prayerId: Int) {
        viewModelScope.launch {
            try {
                val data = repository.getPrayerWithTextsList(prayerId)
                _prayerUiState.value = PrayerUiState.Success(data.first())
            } catch (e: Exception) {
                _prayerUiState.value = PrayerUiState.Error
            }
        }
    }


/*    fun insertPrayer(prayer: Prayers) = viewModelScope.launch {
        repository.insertPrayer(prayer)
    }

    fun updatePrayer(prayer: Prayers) = viewModelScope.launch {
        repository.updatePrayer(prayer)
    }

    fun deletePrayer(prayer: Prayers) = viewModelScope.launch {
        repository.deletePrayer(prayer)
    }*/
}
