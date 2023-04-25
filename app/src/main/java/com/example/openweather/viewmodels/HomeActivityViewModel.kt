package com.example.openweather.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openweather.database.LocationRepository
import com.example.openweather.database.LocationTable
import com.example.openweather.database.OpenWeatherDbHelper

class HomeActivityViewModel(application: Application):AndroidViewModel(application) {
    private var locationRepository: LocationRepository? = null
    var location = MutableLiveData<LocationTable>()

    init{
        var locationDao = OpenWeatherDbHelper.getDatabase(application).locationDao()
        locationRepository = LocationRepository(locationDao)
    }


    fun getLocationsList():LiveData<List<LocationTable>>?{
        return locationRepository?.getLocationsList()
    }





}
