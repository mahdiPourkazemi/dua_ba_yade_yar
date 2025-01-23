package com.pourkazemi.mahdi.dua_bayadyar.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.pourkazemi.mahdi.dua_bayadyar.data.model.Prayers
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayersDao {

    @Query("SELECT * FROM prayers")
    fun getAllPrayers(): Flow<List<Prayers>>

    @Query("SELECT * FROM prayers WHERE id = :prayerId")
    fun getPrayerById(prayerId: Int): Flow<Prayers?>

}
