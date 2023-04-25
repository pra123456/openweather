package com.example.openweather.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationTable(var latitude: String?,
                         var longitude: String?,
                         var locationName: String?) {
    @PrimaryKey(autoGenerate = true)
    var rowId: Int = 0
}