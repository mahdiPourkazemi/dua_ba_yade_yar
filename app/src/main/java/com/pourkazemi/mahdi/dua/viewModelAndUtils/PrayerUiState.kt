package com.pourkazemi.mahdi.dua.viewModelAndUtils

import com.pourkazemi.mahdi.dua.data.model.PrayerWithText

sealed class PrayerUiState {
    //#Todo put some data in loading and error (i know this is an offline app)
    data object Loading : PrayerUiState()
    data class Success(val prayerWithText: PrayerWithText) : PrayerUiState()
    data object Error : PrayerUiState()
}