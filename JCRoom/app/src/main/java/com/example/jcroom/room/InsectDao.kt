package com.example.jcroom.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jcroom.room.entities.Insect

@Dao
interface InsectDao {
    @Query("SELECT * FROM ${Constants.E_INSECTS}")
    suspend fun getAllInsects(): List<Insect>

    @Query("SELECT * FROM ${Constants.E_INSECTS} WHERE uid = :uid")
    suspend fun getInsectsByUserId(uid: Long): List<Insect>

    @Insert
    suspend fun addInsect(insect: Insect): Long

    @Insert
    suspend fun addInsects(insect: List<Insect>): List<Long>

    @Delete
    suspend fun deleteInsect(insect: Insect): Int
}