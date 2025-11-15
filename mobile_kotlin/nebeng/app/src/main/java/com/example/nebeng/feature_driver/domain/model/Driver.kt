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
) {
    companion object {
        fun getEmpty(): Driver = Driver(
            id = 0,
            userId = 0,
            fullName = "",
            telephone = "",
            fullAddress = "",
            profileImg = null,
            faceImg = null,
            faceWithIdImg = null,
            idCardImg = null,
            idCardFullname = "",
            idCardNumber = "",
            idCardBirthdate = "",
            driverLicenseNumber = "",
            driverLicenseType = "",
            driverLicenseExpired = "",
            driverLicenseImg = null,
            policeClearanceCertificateNumber = "",
            policeClearanceCertificateFullname = "",
            policeClearanceCertificateImg = null,
            policeClearanceCertificateExpired = "",
            idCardVerified = false,
            driverLicenseVerified = false,
            policeClearanceVerified = false,
            creditScore = 0,
            balance = 0,
            createdAt = "",
            updatedAt = "",
            totalRating = 0,
            ratingCount = 0,
            averageRating = 0f
        )
    }
}
