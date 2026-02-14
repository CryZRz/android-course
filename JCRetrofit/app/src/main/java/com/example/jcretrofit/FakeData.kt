package com.example.jcretrofit

import com.example.jcretrofit.entities.Data
import com.example.jcretrofit.entities.SingleUserResponse
import com.example.jcretrofit.entities.Support

fun getUser() = SingleUserResponse(
    Data(1, "chris@mial.com", "llandes", "Kernea", ""),
    Support("Hola como estas", "Descripcion")
)

fun getUsers() = listOf(getUser().data, getUser().data)