package com.example.nebeng.feature_auth.domain.model

data class Auth(
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: String
)
// Role customer, posko_admin, & posko mitra