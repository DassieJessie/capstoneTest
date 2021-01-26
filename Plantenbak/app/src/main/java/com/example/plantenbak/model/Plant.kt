package com.example.plantenbak.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "plantTable")
data class Plant(

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "plantDate")
    var plantDate: LocalDate,

    @ColumnInfo(name = "waterPerWeek")
    var waterPerWeek : String,

    @ColumnInfo(name = "sendNotification")
    var sendNotification : Boolean,

    @ColumnInfo(name = "zonlichtNodig")
    var zonlichtNodig : String,

    @ColumnInfo(name = "notitie")
    var notitie : String,


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)