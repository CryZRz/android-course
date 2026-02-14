package com.example.jcretrofit.retrofit

import com.example.jcretrofit.entities.LoginResponse
import com.example.jcretrofit.entities.RegisterResponse
import com.example.jcretrofit.entities.UserInfo
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("x-api-key: api-key")
    @POST("${Constants.API_PATH}${Constants.LOGIN_PATH}")
    suspend fun loginUser(@Body user: UserInfo): LoginResponse

    @Headers("x-api-key: api-key")
    @POST("${Constants.API_PATH}${Constants.REGISTER_PATH}")
    suspend fun registerUser(@Body user: UserInfo): RegisterResponse
}