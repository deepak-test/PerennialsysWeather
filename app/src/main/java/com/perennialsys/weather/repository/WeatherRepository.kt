package com.perennialsys.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.perennialsys.weather.db.MyDatabase
import com.perennialsys.weather.models.entities.UserRegisterModel
import com.perennialsys.weather.models.network.WeatherModel
import com.perennialsys.weather.retrofit.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherAPI: WeatherAPI,
    private val myDatabase: MyDatabase
) {

    private val _weather = MutableLiveData<WeatherModel>()
    val weather: LiveData<WeatherModel>
        get() = _weather

    suspend fun getCurrentWeather(apiKey: String, lat: String, lon: String) {
        val currentWeather = weatherAPI.getCurrentWeather(apiKey, lat, lon)
        if (currentWeather.isSuccessful && currentWeather.body() != null) {
            _weather.postValue(currentWeather.body())
        }
    }

    suspend fun isUserValid(userName: String, password: String): Boolean {
        return myDatabase.myDao().isUserValid(userName, password)
    }

    suspend fun checkUserName(userName: String): Boolean {
        return myDatabase.myDao().checkUserName(userName)
    }

    suspend fun checkEmail(email: String): Boolean {
        return myDatabase.myDao().checkEmail(email)
    }

    suspend fun addUser(user: UserRegisterModel) {
        return myDatabase.myDao().addUser(user)
    }

}