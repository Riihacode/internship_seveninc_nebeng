package com.example.nebeng.feature_a_authentication.domain.model

import com.example.nebeng.core.utils.UserType

data class AuthenticationItem(
    val userId: Int,
    val name: String,
    val username: String,
    val email: String?,

    // passeord tidak ada di response -> biarakan null
    val password: String? = null,

    val userType: UserType,
    val token: String = "",

    val phone: String? = null,
    val avatarUrl: String? = null,
    val isVerified: Boolean? = false,

    val createdAt: String? = null,
    val updatedAt: String? = null,
    val banned: Boolean? = false,

    // 🆕 ADD for history order & customer profile
    val customerId: Int? = null,
    val customerName: String? = null
)
