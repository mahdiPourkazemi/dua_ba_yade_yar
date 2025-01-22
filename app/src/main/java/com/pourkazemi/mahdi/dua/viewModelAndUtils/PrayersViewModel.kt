package com.pourkazemi.mahdi.dua.viewModelAndUtils

import android.util.Log
import androidx.compose.runtime.collectAsState
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
        //#Todo check stateFlow and call fun in init for allPrayers
        loadAllPrayers()
        loadData()
    }

    //val allPrayers: LiveData<List<Prayers>> = repository.allPrayers.asLiveData()

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


/*    fun getPrayerById(prayerId: Int): LiveData<Prayers?> =
        repository.getPrayerById(prayerId).asLiveData()*/

    fun getPrayerTextsByPrayerId(prayerId: Int): Flow<List<PrayerText>> =
        repository.getPrayerTextsByPrayerId(prayerId)

    //#Todo check speed of this query instead of above query(model)
/*    fun getPrayerWithTextsList(prayerId: Int): Flow<PrayerWithText> =
        repository.getPrayerWithTextsList(prayerId)*/

/*
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
    }*/

    private val _data = MutableStateFlow<Int>(18)
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
