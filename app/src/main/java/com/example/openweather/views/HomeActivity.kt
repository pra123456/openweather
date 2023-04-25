package com.example.openweather.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openweather.R
import com.example.openweather.adapters.CustomAdapter
import com.example.openweather.databinding.ActivityHomeBinding
import com.example.openweather.viewmodels.HomeActivityViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeActivityViewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        setSupportActionBar(binding.toolbar)
        binding.addLocation.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@HomeActivity,MapsActivity::class.java))
            }

        })
        homeActivityViewModel.getLocationsList()?.observe(this@HomeActivity, Observer { response ->

                if (response != null && response.size > 0) {

                    // this creates a vertical layout Manager
                    binding.layoutContentHome.recyclerview.layoutManager = LinearLayoutManager(this)

                    val adapter = CustomAdapter(response,this@HomeActivity)

                    // Setting the Adapter with the recyclerview
                    binding.layoutContentHome.recyclerview.adapter = adapter
                }

        })

        homeActivityViewModel.location.observe(this@HomeActivity,Observer{
            location->
            Log.d("locationId = ",location.toString())
            if(location!=null) {
                var intent = Intent(this@HomeActivity, WeatherDetailsActivity::class.java)
                intent.putExtra("latitude", location.latitude)
                intent.putExtra("longitude", location.longitude)
                startActivity(intent)
            }
        })


    }


}