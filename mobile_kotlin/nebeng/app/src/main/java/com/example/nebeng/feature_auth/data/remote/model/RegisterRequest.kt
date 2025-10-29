package com.example.nebeng.feature_auth.data.remote.model

data class RegisterRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
    val user_type: String
)

