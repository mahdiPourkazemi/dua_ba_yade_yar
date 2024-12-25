package com.pourkazemi.mahdi.dua.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.pourkazemi.mahdi.dua.data.model.Prayers
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayersDao {

    @Query("SELECT * FROM prayers")
    fun getAllPrayers(): Flow<List<Prayers>>

    @Query("SELECT * FROM prayers WHERE id = :prayerId")
    fun getPrayerById(prayerId: Int): Flow<Prayers?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayer(prayer: Prayers)

    @Update
    suspend fun updatePrayer(prayer: Prayers)

    @Delete
    suspend fun deletePrayer(prayer: Prayers)
}
