package com.perennialsys.weather

import android.app.Application
import com.perennialsys.weather.di.ApplicationComponent
import com.perennialsys.weather.di.DaggerApplicationComponent

class WeatherApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }

}