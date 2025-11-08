package com.example.nebeng.feature_goods_ride.data.remote.model.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.feature_goods_ride.data.remote.model.dto.ArrivalTerminalDto
import com.example.nebeng.feature_goods_ride.data.remote.model.dto.DepartureTerminalDto
import com.example.nebeng.feature_goods_ride.data.remote.model.dto.DriverDto
import com.example.nebeng.feature_goods_ride.data.remote.model.response.*
import com.example.nebeng.feature_goods_ride.domain.model.*
import com.example.nebeng.core.utils.RideStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * ============================================
 * 🔹 GOODS RIDE DTO → DOMAIN MAPPER
 * ============================================
 */
@RequiresApi(Build.VERSION_CODES.O)
private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

@RequiresApi(Build.VERSION_CODES.O)
fun DataItem.toDomain(): GoodsRide {
    return GoodsRide(
        id = id,
        driverId = driverId,
        transportType = TransportType.fromString(transportType),
        publicTerminalSubtype = PublicTerminalSubtype.fromString(publicTerminalSubtype),
        departureTerminalId = departureTerminalId,
        arrivalTerminalId = arrivalTerminalId,
        departureTime = LocalDateTime.parse(departureTime, formatter),
        maxWeight = maxWeight,
        weightReserved = weightReserved,
        pricePerKg = pricePerKg,
        commissionPercentage = commissionPercentage,
        rideStatus = RideStatus.fromString(rideStatus) ?: RideStatus.PENDING,
        createdAt = LocalDateTime.parse(createdAt, formatter),
        updatedAt = LocalDateTime.parse(updatedAt, formatter)
    )
}

/**
 * ============================================
 * 🔹 CREATE RESPONSE DTO → DOMAIN MAPPER
 * ============================================
 */
@RequiresApi(Build.VERSION_CODES.O)
fun DataCreate.toDomain(): GoodsRide {
    return GoodsRide(
        id = id,
        driverId = driverId,
        transportType = TransportType.fromString(transportType),
        publicTerminalSubtype = PublicTerminalSubtype.TERMINAL_BIS, // Tidak tersedia di DTO ini
        departureTerminalId = departureTerminalId,
        arrivalTerminalId = arrivalTerminalId,
        departureTime = LocalDateTime.parse(departureTime, formatter),
        maxWeight = maxWeight,
        weightReserved = weightReserved,
        pricePerKg = pricePerKg,
        commissionPercentage = commissionPercentage,
        rideStatus = RideStatus.fromString(rideStatus) ?: RideStatus.PENDING,
        createdAt = LocalDateTime.parse(createdAt, formatter),
        updatedAt = LocalDateTime.parse(updatedAt, formatter)
    )
}

/**
 * ============================================
 * 🔹 UPDATE RESPONSE DTO → DOMAIN MAPPER
 * ============================================
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Data.toDomain(): GoodsRide {
    return GoodsRide(
        id = id,
        driverId = driverId,
        transportType = TransportType.fromString(transportType),
        publicTerminalSubtype = PublicTerminalSubtype.fromString(publicTerminalSubtype),
        departureTerminalId = departureTerminalId,
        arrivalTerminalId = arrivalTerminalId,
        departureTime = LocalDateTime.parse(departureTime, formatter),
        maxWeight = maxWeight,
        weightReserved = weightReserved,
        pricePerKg = pricePerKg,
        commissionPercentage = commissionPercentage,
        rideStatus = RideStatus.fromString(rideStatus) ?: RideStatus.PENDING,
        createdAt = LocalDateTime.parse(createdAt, formatter),
        updatedAt = LocalDateTime.parse(updatedAt, formatter)
    )
}

/**
 * ============================================
 * 🔹 READ BY ID DTO → DOMAIN MAPPER
 * ============================================
 */
@RequiresApi(Build.VERSION_CODES.O)
fun DataReadById.toDomain(): GoodsRide {
    return GoodsRide(
        id = id,
        driverId = driverId,
        transportType = TransportType.fromString(transportType),
        publicTerminalSubtype = PublicTerminalSubtype.fromString(publicTerminalSubtype),
        departureTerminalId = departureTerminalId,
        arrivalTerminalId = arrivalTerminalId,
        departureTime = LocalDateTime.parse(departureTime, formatter),
        maxWeight = maxWeight,
        weightReserved = weightReserved,
        pricePerKg = pricePerKg,
        commissionPercentage = commissionPercentage,
        rideStatus = RideStatus.fromString(rideStatus) ?: RideStatus.PENDING,
        createdAt = LocalDateTime.parse(createdAt, formatter),
        updatedAt = LocalDateTime.parse(updatedAt, formatter)
    )
}

/**
 * ============================================
 * 🔹 TERMINAL DTO → DOMAIN (untuk keperluan nested detail)
 * ============================================
 */
data class Terminal(
    val id: Int,
    val name: String,
    val fullAddress: String,
    val terminalType: String,
    val publicTerminalSubtype: String,
    val latitude: String?,
    val longitude: String?,
    val regencyId: Int,
    val districtId: Int,
    val provinceId: Int,
    val createdAt: String,
    val updatedAt: String
)

fun ArrivalTerminalDto.toDomain(): Terminal {
    return Terminal(
        id = id,
        name = name,
        fullAddress = fullAddress,
        terminalType = terminalType,
        publicTerminalSubtype = publicTerminalSubtype,
        latitude = latitude.toString(),
        longitude = longitude.toString(),
        regencyId = regencyId,
        districtId = districtId,
        provinceId = provinceId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun DepartureTerminalDto.toDomain(): Terminal {
    return Terminal(
        id = id,
        name = name,
        fullAddress = fullAddress,
        terminalType = terminalType,
        publicTerminalSubtype = publicTerminalSubtype,
        latitude = latitude.toString(),
        longitude = longitude.toString(),
        regencyId = regencyId,
        districtId = districtId,
        provinceId = provinceId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * ============================================
 * 🔹 DRIVER DTO → DOMAIN (untuk future reference)
 * ============================================
 */
data class Driver(
    val id: Int,
    val userId: Int,
    val fullName: String,
    val telephone: String,
    val fullAddress: String,
    val profileImg: Any?,
    val faceImg: Any?,
    val faceWithIdImg: Any?,
    val idCardImg: Any?,
    val idCardFullname: String,
    val idCardNumber: String,
    val idCardBirthdate: String,
    val driverLicenseNumber: String,
    val driverLicenseType: String,
    val driverLicenseExpired: String,
    val driverLicenseImg: Any?,
    val policeClearanceCertificateNumber: String,
    val policeClearanceCertificateFullname: String,
    val policeClearanceCertificateImg: Any?,
    val policeClearanceCertificateExpired: String,
    val idCardVerified: Boolean,
    val driverLicenseVerified: Boolean,
    val policeClearanceVerified: Int,
    val creditScore: Int,
    val balance: Int,
    val averageRating: Int,
    val totalRating: Int,
    val ratingCount: Int,
    val createdAt: String,
    val updatedAt: String
)

fun DriverDto.toDomain(): Driver {
    return Driver(
        id = id,
        userId = userId,
        fullName = fullName,
        telephone = telephone,
        fullAddress = fullAddress,
        profileImg = profileImg,
        faceImg = faceImg,
        faceWithIdImg = faceWithIdImg,
        idCardImg = idCardImg,
        idCardFullname = idCardFullname,
        idCardNumber = idCardNumber,
        idCardBirthdate = idCardBirthdate,
        driverLicenseNumber = driverLicenseNumber,
        driverLicenseType = driverLicenseType,
        driverLicenseExpired = driverLicenseExpired,
        driverLicenseImg = driverLicenseImg,
        policeClearanceCertificateNumber = policeClearanceCertificateNumber,
        policeClearanceCertificateFullname = policeClearanceCertificateFullname,
        policeClearanceCertificateImg = policeClearanceCertificateImg,
        policeClearanceCertificateExpired = policeClearanceCertificateExpired,
        idCardVerified = idCardVerified,
        driverLicenseVerified = driverLicenseVerified,
        policeClearanceVerified = policeClearanceVerified,
        creditScore = creditScore,
        balance = balance,
        averageRating = averageRating,
        totalRating = totalRating,
        ratingCount = ratingCount,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}