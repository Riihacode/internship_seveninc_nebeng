package com.example.nebeng.feature_auth.domain.model

//data class Auth(
//    val id: Int = 0,
//    val username: String,
//    val password: String,
//    val role: String
//)
// Role customer, posko_admin, & posko mitra

data class Auth(
    val id: Int,
    val name: String,
    val username: String,
    val email: String?,
    val password: String? = null,
    val user_type: String,
    val token: String = ""
)