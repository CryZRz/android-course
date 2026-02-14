package com.example.jcroom.room.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.jcroom.room.Constants

@Entity(tableName = Constants.E_INSECTS)
data class Insect(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String = "",
    var imgLocation: String = "",
    var inDanger: Boolean = false,
    var uid: Long = 0
)
