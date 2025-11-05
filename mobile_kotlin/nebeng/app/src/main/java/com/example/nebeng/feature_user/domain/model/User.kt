package com.example.nebeng.feature_user.domain.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val userType: String
)
