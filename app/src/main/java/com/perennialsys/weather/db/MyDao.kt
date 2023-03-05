package com.perennialsys.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.perennialsys.weather.models.entities.UserRegisterModel

@Dao
interface MyDao {

    @Insert
    suspend fun addUser(user: UserRegisterModel)

    @Query("SELECT EXISTS (SELECT * FROM users WHERE userName = :userName AND userPassword = :password)")
    suspend fun isUserValid(userName: String, password: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM users WHERE userName = :userName)")
    suspend fun checkUserName(userName: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM users WHERE userEmail = :email)")
    suspend fun checkEmail(email: String): Boolean
}