package com.example.jcretrofit.entities

class Data(
    val data: Long,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
) {

    fun getFullName(): String = "$first_name $last_name"
}