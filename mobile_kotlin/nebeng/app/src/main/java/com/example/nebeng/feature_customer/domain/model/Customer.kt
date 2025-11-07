package com.example.nebeng.feature_customer.domain.model

import com.example.nebeng.feature_user.domain.model.User

data class Customer(
    val id: Int,
    val userId: Int,
    val fullName: String,
    val telephone: String,
    val fullAddress: String,

    val profileImg: String?,
    val verified: Boolean,
    val faceImg: String?,
    val faceWithIdImg: String?,

    val idCardImg: String?,
    val idCardNumber: String,
    val idCardFullName: String,
    val idCardBirthdate: String,

    val creditAmount: Int,
    val createdAt: String,
    val updatedAt: String

    // Relasi
//    val user: User? = null
)