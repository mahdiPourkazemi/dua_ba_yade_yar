package com.pourkazemi.mahdi.dua.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.pourkazemi.mahdi.dua.data.model.prayers
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayersDao {

    @Query("SELECT * FROM prayers")
    fun getAllPrayers(): Flow<List<prayers>>

    @Query("SELECT * FROM prayers WHERE id = :prayerId")
    fun getPrayerById(prayerId: Int): Flow<prayers?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayer(prayer: prayers)

    @Update
    suspend fun updatePrayer(prayer: prayers)

    @Delete
    suspend fun deletePrayer(prayer: prayers)
}
