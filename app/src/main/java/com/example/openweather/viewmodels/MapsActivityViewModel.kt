package com.example.openweather.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.openweather.database.LocationRepository
import com.example.openweather.database.LocationTable
import com.example.openweather.database.OpenWeatherDbHelper

class MapsActivityViewModel(application: Application): AndroidViewModel(application) {
    private var locationRepository:LocationRepository? = null


    init{
        var locationDao = OpenWeatherDbHelper.getDatabase(application).locationDao()
        locationRepository = LocationRepository(locationDao)
    }

    fun addLocationToDatabase(latitude: String, longitude: String, name: String) {
        var locationTable = LocationTable(latitude,longitude,name)
        locationRepository?.insertLocation(locationTable)
    }

}