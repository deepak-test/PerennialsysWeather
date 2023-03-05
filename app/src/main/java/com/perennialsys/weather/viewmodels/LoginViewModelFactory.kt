package com.perennialsys.weather.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.perennialsys.weather.repository.WeatherRepository
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(private val repository: WeatherRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}