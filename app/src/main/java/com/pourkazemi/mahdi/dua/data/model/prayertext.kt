package com.pourkazemi.mahdi.dua.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "prayertext",
    foreignKeys = [
        ForeignKey(
            entity = prayers::class,
            parentColumns = ["id"],
            childColumns = ["prayerid"],
            //onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["prayerid"])]
)
data class prayertext(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "prayerid")
    val prayerid: Int,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "translation")
    val translation: String
)
