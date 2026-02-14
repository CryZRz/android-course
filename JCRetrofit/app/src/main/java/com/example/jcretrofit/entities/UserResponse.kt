package com.example.jcretrofit.entities

class UserResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<Data>,
    val support: Support
)