package com.example.nebeng.feature_driver.domain.model

data class Driver(
    val id: Int,
    val userId: Int,
    val fullName: String,
    val balance: Int,

    val telephone: String,
    val fullAddress: String,
    val profileImg: String?,
    val faceImg: String?,
    val idCardImg: String?,
    val faceWithIdImg: String?,
    val driverLicenseImg: String?,
    val policeClearanceCertificateImg: String?,
    val creditScore: Int,
    val idCardFullname: String,
    val idCardVerified: Boolean,
    val driverLicenseVerified: Boolean,
    val policeClearanceVerified: Boolean,
    val idCardNumber: String,
    val driverLicenseNumber: String,
    val driverLicenseType: String,
    val idCardBirthdate: String,
    val driverLicenseExpired: String,
    val policeClearanceCertificateExpired: String?,
    val createdAt: String,
    val updatedAt: String,
    val policeClearanceCertificateNumber: String?,
    val policeClearanceCertificateFullname: String?,
    
    
    // Ditambahi oleh anggota lain
    val totalRating: Int?,
    val ratingCount: Int?,
    val averageRating: Float?

    // 🔹 Relasi opsional (bisa kosong / null tergantung endpoint)
//    val ratings: List<Rating> = emptyList(),
//    val user: User? = null
)