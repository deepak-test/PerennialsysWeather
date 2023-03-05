package com.perennialsys.weather.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.perennialsys.weather.R
import com.perennialsys.weather.WeatherApplication
import com.perennialsys.weather.databinding.ActivitySignUpBinding
import com.perennialsys.weather.viewmodels.SignUpViewModel
import com.perennialsys.weather.viewmodels.SignUpViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    @Inject
    lateinit var signupViewModelFactory: SignUpViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as WeatherApplication).applicationComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this, signupViewModelFactory).get(SignUpViewModel::class.java)

        binding.signupViewModel = signUpViewModel
        binding.lifecycleOwner = this

        binding.btnSignUp.setOnClickListener {
            if (signUpViewModel.fullName.isNullOrEmpty()) {
                binding.etFullNameSignUp.error = "Please enter Full Name"
            } else if (signUpViewModel.uniqueUserNameLiveData.value.isNullOrEmpty()) {
                binding.etUsernameSignUp.error = "Please enter User Name"
            } else if (signUpViewModel.userEmailLiveData.value.isNullOrEmpty()) {
                binding.etEmailSignUp.error = "Please enter Email"
            } else if (signUpViewModel.userPassword.isNullOrEmpty()) {
                binding.etPasswordSingUp.error = "Please enter Password"
            } else if (signUpViewModel.userConfirmPassword.isNullOrEmpty()) {
                binding.etConfirmPasswordSignUp.error = "Please confirm Password"
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (!signUpViewModel.checkUserName() && !signUpViewModel.checkUserEmail()) {
                        if (signUpViewModel.isPasswordMatched()) {
                            signUpViewModel.addUser()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            withContext(Dispatchers.Main) {
                                binding.etConfirmPasswordSignUp.error = "Password Not Matched"
                            }
                        }
                    }
                }
            }
        }

        signUpViewModel.uniqueUserNameLiveData.observe(this, Observer {
            lifecycleScope.launch(Dispatchers.IO) {
                if (signUpViewModel.checkUserName()) {
                    withContext(Dispatchers.Main) {
                        binding.etUsernameSignUp.error = "User Name not available"
                    }
                }
            }
        })

        signUpViewModel.userEmailLiveData.observe(this, Observer {
            lifecycleScope.launch(Dispatchers.IO) {
                if (signUpViewModel.checkUserEmail()) {
                    withContext(Dispatchers.Main) {
                        binding.etEmailSignUp.error = "Email is already registered."
                    }
                }
            }
        })

    }
}