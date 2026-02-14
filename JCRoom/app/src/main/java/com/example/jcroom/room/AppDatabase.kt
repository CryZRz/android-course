package com.example.jcroom.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jcroom.room.entities.Insect
import com.example.jcroom.room.entities.User

@Database(entities = [Insect::class, User::class], version = Constants.DB_2026_INIT_VERSION)
abstract class AppDatabase: RoomDatabase() {
    abstract fun insectDao(): InsectDao
    abstract fun userDao(): UserDao
}