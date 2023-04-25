package com.example.openweather.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.openweather.databinding.ActivityWeatherDetailsBinding
import com.example.openweather.viewmodels.WeatherDetailsActivityViewModel

class WeatherDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherDetailsBinding
    private lateinit var viewModel: WeatherDetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this@WeatherDetailsActivity).get(WeatherDetailsActivityViewModel::class.java)
        setSupportActionBar(binding.toolbar)

        var latitude = intent.getStringExtra("latitude")
        var longitude = intent.getStringExtra("longitude")


        viewModel.getWeatherForecastOfLocation(latitude,longitude,"c57fa7ab5264c20ea78cf494c35f965f")
        viewModel.forecastResponse.observe(this@WeatherDetailsActivity,Observer{
            response->

            if(response!=null){
                binding.layout.humidityValue.text = response.main.humidity.toString()
                binding.layout.sunriseValue.text = response.sys.sunrise.toString()
                binding.layout.sunsetValue.text = response.sys.sunset.toString()
                binding.layout.windValue.text = response.wind.speed.toString()
                binding.layout.temp.text = response.main.temp.toString()
                binding.layout.maxTemp.text = "Max Temp: " + response.main.tempMax.toString()
                binding.layout.minTemp.text = "Min Temp: " + response.main.tempMin.toString()
                binding.layout.pressureValue.text = response.main.pressure.toString()
                binding.layout.sealevelValue.text = response.main.seaLevel.toString()
                binding.layout.name.text = response.name.toString()
            }
        })






    }


}