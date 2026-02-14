package com.example.jcretrofit.entities

import com.example.jcretrofit.retrofit.Constants
import com.google.gson.annotations.SerializedName

class UserInfo(
    @SerializedName(Constants.EMAIL_PARAM) val email: String,
    @SerializedName(Constants.PASSWORD_PARAM) val password: String
)