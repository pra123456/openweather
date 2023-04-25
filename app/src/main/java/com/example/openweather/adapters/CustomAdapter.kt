package com.example.openweather.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.openweather.R
import com.example.openweather.database.LocationTable
import com.example.openweather.viewmodels.HomeActivityViewModel
import com.example.openweather.views.HomeActivity

class CustomAdapter(private val mList: List<LocationTable>,context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var homeActivityViewModel:HomeActivityViewModel? = null
    init{
        homeActivityViewModel = ViewModelProvider(context as HomeActivity).get(HomeActivityViewModel::class.java)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view 
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
  
        return ViewHolder(view)
    }
  
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  
        val locationTable = mList[position]

        holder.name.text = locationTable.locationName
        holder.latitude.text = locationTable.latitude
        holder.longitude.text = locationTable.longitude

        holder.cardView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                homeActivityViewModel?.location?.value = locationTable
            }

        })
  
    }
  
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }
  
    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.name_value)
        val latitude: TextView = itemView.findViewById(R.id.latitude_value)
        val longitude: TextView = itemView.findViewById(R.id.longitude_value)
        val cardView:CardView = itemView.findViewById(R.id.card_view)
    }
}