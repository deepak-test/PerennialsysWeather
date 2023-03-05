package com.perennialsys.weather.viewmodels

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.perennialsys.weather.repository.WeatherRepository

class LoginViewModel(private val repository: WeatherRepository) : ViewModel() {

    /*To set text of text view on two different colors*/
    private val signupText = "<font color=" + "#FFFFFF" + ">" + "Don't have an account ?" + "</font>"
    private val signupText2 = "<font color=" + "#131A51" + ">" + "Sign Up" + "</font>"

    fun getFullSignupText(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        (Html.fromHtml("$signupText $signupText2", Html.FROM_HTML_MODE_LEGACY))
    } else {
        (Html.fromHtml("$signupText $signupText2"))
    }

    val userNameLiveData = MutableLiveData<String>()
    val userPasswordLiveData = MutableLiveData<String>()

    suspend fun isUserValid(): Boolean {
        return repository.isUserValid(
            userNameLiveData.value.toString(),
            userPasswordLiveData.value.toString()
        )
    }

}