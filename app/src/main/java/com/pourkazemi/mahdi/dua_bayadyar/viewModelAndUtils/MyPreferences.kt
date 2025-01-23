package com.pourkazemi.mahdi.dua_bayadyar.viewModelAndUtils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "my_int_preferences")

class MyPreferences private constructor(context: Context) {
    private val myKey = intPreferencesKey("my_Int_key")
    private val dataStore = context.applicationContext.dataStore

    companion object {
        @Volatile private var instance: MyPreferences? = null

        fun getInstance(context: Context): MyPreferences {
            return instance ?: synchronized(this) {
                instance ?: MyPreferences(context.applicationContext).also { instance = it }
            }
        }
    }
    suspend fun writeToDataStore(value: Int) {
        try {
            dataStore.edit { preferences ->
                preferences[myKey] = value
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFromDataStore(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[myKey] ?: 18
            }
    }

}