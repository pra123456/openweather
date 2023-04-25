package com.example.openweather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocation(location:LocationTable?)

    @Query("SELECT * FROM location")
    fun getLocationsList(): LiveData<List<LocationTable>>
}