package com.pourkazemi.mahdi.dua.viewModelAndUtils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.dua.data.model.PrayerText
import com.pourkazemi.mahdi.dua.data.model.Prayers
import com.pourkazemi.mahdi.dua.data.repository.PrayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PrayersViewModel(
    private val repository: PrayerRepository,
    private val myPreferences: MyPreferences
) : ViewModel() {

    init {
        loadAllPrayers()
        loadData()
    }

    private var _allPrayersState = MutableStateFlow<List<Prayers>>(emptyList())
    val allPrayersState: StateFlow<List<Prayers>> = _allPrayersState.asStateFlow()

    private fun loadAllPrayers() {
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


    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>> =
        repository.getPrayerTextsByPrayerId(prayerId)


    private val _data = MutableStateFlow(18)
    val data: StateFlow<Int> = _data.asStateFlow()

    fun saveData(value: Int) {
        viewModelScope.launch {
            myPreferences.writeToDataStore(value)
        }
    }

    fun loadData() {
        viewModelScope.launch {
            val storedValue = myPreferences.readFromDataStore()
            storedValue.collect{
                _data.value = it
            }
        }
    }

}
