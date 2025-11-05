package com.example.nebeng.feature_driver.domain.model

import com.example.nebeng.feature_rating.domain.model.Rating
import com.example.nebeng.feature_user.domain.model.User

data class Driver(
    val id: Int,
    val userId: Int,
    val fullName: String,
    val telephone: String,
    val fullAddress: String,
    val profileImg: String?,
    val faceImg: String?,
    val idCardImg: String?,
    val faceWithIdImg: String?,
    val driverLicenseImg: String?,
    val policeClearanceCertificateImg: String?,
    val creditScore: Int,
    val balance: Int,
    val idCardVerified: Boolean,
    val driverLicenseVerified: Boolean,
    val policeClearanceVerified: Boolean,
    val idCardNumber: String,
    val driverLicenseNumber: String,
    val driverLicenseType: String,
    val idCardBirthdate: String,
    val driverLicenseExpired: String,
    val policeClearanceCertificateExpired: String,
    val createdAt: String,
    val updatedAt: String,

    // 🔹 Relasi opsional (bisa kosong / null tergantung endpoint)
    val ratings: List<Rating> = emptyList(),
    val user: User? = null
)