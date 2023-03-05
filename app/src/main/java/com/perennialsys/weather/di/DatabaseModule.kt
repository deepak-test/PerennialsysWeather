package com.perennialsys.weather.di

import android.content.Context
import androidx.room.Room
import com.perennialsys.weather.db.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, "PerennialsysWeatherDB")
            .build()
    }

}