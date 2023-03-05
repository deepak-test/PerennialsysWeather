package com.perennialsys.weather.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserRegisterModel(

    @PrimaryKey
    val userName: String,
    val fullName: String,
    val userEmail: String,
    val userPassword: String

)
