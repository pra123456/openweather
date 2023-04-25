package com.example.openweather.database

import androidx.lifecycle.LiveData

class LocationRepository(private val locationDao: LocationDao) {

    fun insertLocation(locationTable: LocationTable) {
        locationDao.insertLocation(locationTable)
    }

    fun getLocationsList(): LiveData<List<LocationTable>> {
        return locationDao.getLocationsList()
    }
}