package com.example.jcroom.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jcroom.room.entities.User
import com.example.jcroom.room.entities.UserAuth

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User): Long

    @Insert
    suspend fun updateUser(user: User): Long

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("SELECT * FROM ${Constants.E_USERS} WHERE email = :email AND pin = :pin LIMIT 1")
    suspend fun login(email: String, pin: Int): UserAuth?

    @Query("SELECT * FROM ${Constants.E_USERS} WHERE email = :email LIMIT 1")
    suspend fun findUserByEmail(email: String): UserAuth?
}