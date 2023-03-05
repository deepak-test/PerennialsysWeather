package com.perennialsys.weather.retrofit

import com.perennialsys.weather.models.network.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {

    @GET("weather?lat={lat}&lon={lon}&appid={apikey}")
    suspend fun getCurrentWeather(
        @Path("apikey") apikey: String?,
        @Path("lat") lat: String?,
        @Path("lon") lon: String?
    ): Response<WeatherModel>

}