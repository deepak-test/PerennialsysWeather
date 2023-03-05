package com.perennialsys.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.perennialsys.weather.models.entities.UserRegisterModel

@Database(entities = [UserRegisterModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao

}