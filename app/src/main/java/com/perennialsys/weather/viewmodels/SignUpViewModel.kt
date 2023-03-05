package com.perennialsys.weather.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perennialsys.weather.models.entities.UserRegisterModel
import com.perennialsys.weather.repository.WeatherRepository

class SignUpViewModel(private val repository: WeatherRepository) : ViewModel() {

    var fullName: String = ""
    var userPassword: String = ""
    var userConfirmPassword: String = ""

    val uniqueUserNameLiveData = MutableLiveData<String>()
    val userEmailLiveData = MutableLiveData<String>()

    suspend fun checkUserName(): Boolean = repository.checkUserName(uniqueUserNameLiveData.value.toString())

    suspend fun checkUserEmail(): Boolean = repository.checkEmail(userEmailLiveData.value.toString())

    fun isPasswordMatched() : Boolean = (userPassword == userConfirmPassword)

    suspend fun addUser() {
        repository.addUser(UserRegisterModel(uniqueUserNameLiveData.value.toString(), fullName, userEmailLiveData.value.toString(), userPassword))
    }

}