package com.jt.mysalon.data.remote.entity

data class LoginResponseEntity(
    val email: String,
    val status: String,
    val token: String,
)
