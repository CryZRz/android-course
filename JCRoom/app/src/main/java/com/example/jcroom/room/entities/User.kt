package com.example.jcroom.room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.jcroom.room.Constants

@Entity(tableName = Constants.E_USERS, indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val pin: Int,
)
