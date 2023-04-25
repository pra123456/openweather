package com.example.openweather.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openweather.apis.ForecastApi
import com.example.openweather.apis.RetrofitHelper
import com.example.openweather.models.WeatherForecast
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherDetailsActivityViewModel(application: Application):AndroidViewModel(application) {

    var forecastApi:ForecastApi? = null
    var forecastResponse = MutableLiveData<WeatherForecast>()
    init{
        forecastApi = RetrofitHelper.retrofit
    }

    fun getWeatherForecastOfLocation(latitude: String?, longitude: String?, appid: String) {
        forecastApi!!.getForecast(latitude.toString(), longitude.toString(), appid)
            .enqueue(object : Callback<WeatherForecast> {
                override fun onResponse(
                    call: Call<WeatherForecast>,
                    response: Response<WeatherForecast>
                ) {

                    Log.d("response", response.body().toString())
                    forecastResponse.value = response.body()
                }

                override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                }

            })

    }

}
