package com.example.jcretrofit.retrofit

import com.example.jcretrofit.entities.SingleUserResponse
import com.example.jcretrofit.entities.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserService {

    @Headers("x-api-key: api-key")
    @GET("${Constants.API_PATH}${Constants.USERS_PATH}${Constants.ID_PATH}")
    suspend fun getSingleUser(): SingleUserResponse

    @Headers("x-api-key: api-key")
    @GET("${Constants.API_PATH}${Constants.USERS_PATH}${Constants.PAGE_TWO}")
    suspend fun getListUsers(): UserResponse
}