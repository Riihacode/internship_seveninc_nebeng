package com.example.nebeng.feature_driver.data.remote.model.mapper

import com.example.nebeng.feature_driver.data.remote.model.dto.DataDto
import com.example.nebeng.feature_driver.data.remote.model.dto.DataItemDto
import com.example.nebeng.feature_driver.data.remote.model.dto.RatingsItemDto
import com.example.nebeng.feature_driver.data.remote.model.dto.UserDto
import com.example.nebeng.feature_driver.domain.model.Driver
import com.example.nebeng.feature_rating.domain.model.Rating
import com.example.nebeng.feature_user.domain.model.User

/* ============================================================
   🔹 Mapper untuk DataDto (digunakan oleh endpoint detail/create/update)
   ============================================================ */
fun DataDto.toDomain(): Driver {
    return Driver(
        id = id,
        userId = userId,
        fullName = fullName,
        telephone = telephone,
        fullAddress = fullAddress,
        profileImg = profileImg,
        faceImg = faceImg,
        idCardImg = idCardImg,
        faceWithIdImg = faceWithIdImg,
        driverLicenseImg = driverLicenseImg,
        policeClearanceCertificateImg = policeClearanceCertificateImg,
        creditScore = creditScore,
        balance = balance,
        idCardVerified = idCardVerified,
        driverLicenseVerified = driverLicenseVerified,
        policeClearanceVerified = policeClearanceVerified,
        idCardNumber = idCardNumber,
        driverLicenseNumber = driverLicenseNumber,
        driverLicenseType = driverLicenseType,
        idCardBirthdate = idCardBirthdate,
        driverLicenseExpired = driverLicenseExpired,
        policeClearanceCertificateExpired = policeClearanceCertificateExpired,
        createdAt = createdAt,
        updatedAt = updatedAt,

        // relasi kosong karena endpoint ini tidak kirim ratings/user
        ratings = emptyList(),
        user = null
    )
}

/* ============================================================
   🔹 Mapper untuk DataItemDto (digunakan oleh endpoint list)
   ============================================================ */
fun DataItemDto.toDomain(): Driver {
    return Driver(
        id = id,
        userId = userId,
        fullName = fullName,
        telephone = telephone,
        fullAddress = fullAddress,
        profileImg = profileImg,
        faceImg = faceImg,
        idCardImg = idCardImg,
        faceWithIdImg = faceWithIdImg,
        driverLicenseImg = driverLicenseImg,
        policeClearanceCertificateImg = policeClearanceCertificateImg,
        creditScore = creditScore,
        balance = balance,
        idCardVerified = idCardVerified,
        driverLicenseVerified = driverLicenseVerified,
        policeClearanceVerified = policeClearanceVerified,
        idCardNumber = idCardNumber,
        driverLicenseNumber = driverLicenseNumber,
        driverLicenseType = driverLicenseType,
        idCardBirthdate = idCardBirthdate,
        driverLicenseExpired = driverLicenseExpired,
        policeClearanceCertificateExpired = policeClearanceCertificateExpired,
        createdAt = createdAt,
        updatedAt = updatedAt,

        // 🔹 map nested relasi
        ratings = ratings.map { it.toDomain() },
        user = user.toDomain()
    )
}

/* ============================================================
   🔹 Mapper untuk RatingsItemDto → DriverRating
   ============================================================ */
fun RatingsItemDto.toDomain(): Rating {
    return Rating(
        id = id,
        ratingValue = rating.toDouble(),
        comment = feedback,
        createdAt = createdAt
    )
}

/* ============================================================
   🔹 Mapper untuk UserDto → DriverUser
   ============================================================ */
fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        userType = userType
    )
}