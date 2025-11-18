package com.example.nebeng.feature_user.domain.model

import com.example.nebeng.core.utils.UserType

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val userType: UserType,
    val banned: Boolean,
    val createdAt: String,
    val updatedAt: String
)
