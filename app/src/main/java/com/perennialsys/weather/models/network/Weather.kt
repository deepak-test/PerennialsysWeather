package com.perennialsys.weather.models.network

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)