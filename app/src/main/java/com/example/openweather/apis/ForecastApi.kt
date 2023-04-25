package com.example.openweather.apis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openweather.models.WeatherForecast
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("/data/2.5/weather")
    fun getForecast(@Query("lat") lat:String,@Query("lon") lon:String,@Query("appid") appid:String): Call<WeatherForecast>
}