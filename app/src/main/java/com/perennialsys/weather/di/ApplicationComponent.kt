package com.perennialsys.weather.di

import android.content.Context
import com.perennialsys.weather.activity.LoginActivity
import com.perennialsys.weather.activity.MainActivity
import com.perennialsys.weather.activity.SignUpActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun inject(activity: LoginActivity)
    fun inject(activity: SignUpActivity)
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}