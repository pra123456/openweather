package com.example.openweather.models

data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)