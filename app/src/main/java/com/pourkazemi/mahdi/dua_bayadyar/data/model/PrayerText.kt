package com.pourkazemi.mahdi.dua_bayadyar.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "prayertext",
    foreignKeys = [
        ForeignKey(
            entity = Prayers::class,
            parentColumns = ["id"],
            childColumns = ["prayerid"],
            //onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["prayerid"])]
)
data class PrayerText(
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

//? sometime good practice to used and create this class but
//? in this case we don't need it because of list wrapper
data class PrayerWithText(
    @Embedded val prayer: Prayers,
    @Relation(
        parentColumn = "id",
        entityColumn = "prayerid"
    )
    val texts: List<PrayerText> = emptyList()
)
