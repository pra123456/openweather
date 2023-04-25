package com.example.openweather.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long
)
