package com.example.openweather.views

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.openweather.viewmodels.MapsActivityViewModel
import com.example.openweather.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.openweather.databinding.ActivityMapsBinding
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var mapsActivityViewModel: MapsActivityViewModel

    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapsActivityViewModel = ViewModelProvider(this).get(MapsActivityViewModel::class.java)
        binding.idSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var location = binding.idSearchView.query.toString()
                if (!location.isNullOrEmpty()) {
                    var geocoder: Geocoder = Geocoder(this@MapsActivity)
                    try {
                        var addressList = geocoder.getFromLocationName(
                            location, 1
                        )
                        if (addressList != null) {
                            var address: Address = addressList[0]
                            var latLng = LatLng(address.latitude, address.longitude)
                            mMap.addMarker(MarkerOptions().position(latLng).title(location))
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                            mapsActivityViewModel.addLocationToDatabase(
                                "" + address.latitude,
                                "" + address.longitude,
                                location
                            )
                            this@MapsActivity.finish()
                        }

                    } catch (e: Exception) {

                    }

                } else {
                    Toast.makeText(this@MapsActivity, "Enter Valid Location", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}