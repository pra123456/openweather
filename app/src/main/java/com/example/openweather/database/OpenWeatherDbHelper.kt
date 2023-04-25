package com.example.openweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LocationTable::class], version = 1)
abstract class OpenWeatherDbHelper : RoomDatabase() {
    abstract fun locationDao(): LocationDao

    companion object{
        @Volatile
        private var instance:OpenWeatherDbHelper? = null
        @Synchronized
        fun getDatabase(ctx: Context): OpenWeatherDbHelper {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    ctx.applicationContext, OpenWeatherDbHelper::class.java,
                    "open_weather"
                )
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build()
            }

            return instance!!

        }
    }
}
