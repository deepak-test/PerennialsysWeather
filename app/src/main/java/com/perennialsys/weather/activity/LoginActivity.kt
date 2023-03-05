package com.perennialsys.weather.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.perennialsys.weather.R
import com.perennialsys.weather.WeatherApplication
import com.perennialsys.weather.databinding.ActivityLoginBinding
import com.perennialsys.weather.viewmodels.LoginViewModel
import com.perennialsys.weather.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as WeatherApplication).applicationComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        binding.btnLogin.setOnClickListener {
            if (loginViewModel.userNameLiveData.value.isNullOrEmpty()) {
                binding.etUsernameLogin.error = "Please enter User Name"
            } else if (loginViewModel.userPasswordLiveData.value.isNullOrEmpty()) {
                binding.etPasswordLogin.error = "Please enter Password"
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (loginViewModel.isUserValid()) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                applicationContext,
                                "Invalid UserName or Password!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}