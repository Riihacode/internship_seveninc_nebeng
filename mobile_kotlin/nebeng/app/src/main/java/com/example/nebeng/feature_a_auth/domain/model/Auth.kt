package com.example.nebeng.feature_a_auth.domain.model

data class Auth(
    val id: Int,
    val name: String,
    val username: String,
    val email: String?,
    val password: String? = null,
    val user_type: String,
    val token: String = "",

    // [BELUM DITAMBAH DI BACKEND]
    val phone: String? = null,      // sementara
    val avatarUrl: String? = null,   // sementara

    // [BELUM DITAMBAH DI BACKEND] HomepageDriverScreenUi
    val isVerified: Boolean = false
)