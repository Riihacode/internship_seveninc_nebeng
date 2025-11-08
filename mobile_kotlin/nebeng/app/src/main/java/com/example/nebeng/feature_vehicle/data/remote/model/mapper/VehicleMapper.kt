package com.example.nebeng.feature_vehicle.data.remote.model.mapper

import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_vehicle.data.remote.model.dto.DataDto
import com.example.nebeng.feature_vehicle.data.remote.model.dto.DataItemDto
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import com.example.nebeng.feature_vehicle.data.remote.model.response.DataReadById


///**
// * Mapper untuk mengubah berbagai bentuk DTO kendaraan menjadi domain model Vehicle.
// * Tidak ada field yang dihilangkan — seluruh atribut di-keep sesuai definisi domain.
// */
//object VehicleMapper {
//
//    // 🔹 Mapping dari DataDto → Vehicle (biasanya untuk Create, Update)
//    fun fromDataDto(dto: DataDto): Vehicle {
//        return Vehicle(
//            id = dto.id,
//            driverId = dto.driverId,
//            registrationNumber = dto.registrationNumber,
//            registrationYear = dto.registrationYear.toString(),
//            registrationExpired = dto.registrationExpired,
//            registrationImg = dto.registrationImg,
//            vehicleName = dto.vehicleName,
//            vehicleColor = dto.vehicleColor,
//            vehicleType = VehicleType.fromString(dto.vehicleType),
//            vehicleImg = dto.vehicleImg,
//            vehicleVerified = dto.vehicleVerified,
//            vehicleRejectedReason = dto.vehicleRejectedReason
//        )
//    }
//
//    // 🔹 Mapping dari DataItemDto → Vehicle (biasanya untuk ReadAll / ReadByDriver)
//    fun fromDataItemDto(dto: DataItemDto): Vehicle {
//        return Vehicle(
//            id = dto.id,
//            driverId = dto.driver.id,
//            registrationNumber = dto.registrationNumber,
//            registrationYear = dto.registrationYear,
//            registrationExpired = dto.registrationExpired,
//            registrationImg = dto.registrationImg,
//            vehicleName = dto.vehicleName,
//            vehicleColor = dto.vehicleColor,
//            vehicleType = VehicleType.fromString(dto.vehicleType),
//            vehicleImg = dto.vehicleImg,
//            vehicleVerified = dto.vehicleVerified,
//            vehicleRejectedReason = dto.vehicleRejectedReason
//        )
//    }
//
//    // 🔹 Mapping dari DataReadById → Vehicle (biasanya untuk ReadById)
//    fun fromDataReadById(dto: DataReadById): Vehicle {
//        return Vehicle(
//            id = dto.id,
//            driverId = dto.driver.id,
//            registrationNumber = dto.registrationNumber,
//            registrationYear = dto.registrationYear,
//            registrationExpired = dto.registrationExpired,
//            registrationImg = dto.registrationImg,
//            vehicleName = dto.vehicleName,
//            vehicleColor = dto.vehicleColor,
//            vehicleType = VehicleType.fromString(dto.vehicleType),
//            vehicleImg = dto.vehicleImg,
//            vehicleVerified = dto.vehicleVerified,
//            vehicleRejectedReason = dto.vehicleRejectedReason
//        )
//    }
/**
 * Mapper extension functions untuk konversi berbagai DTO kendaraan ke domain model Vehicle.
 * Semua field dijaga lengkap tanpa dikurangi.
 */

// 🔹 Untuk Create/Update Response
fun DataDto.toDomain(): Vehicle =
    Vehicle(
        id = id,
        driverId = driverId,
        registrationNumber = registrationNumber,
        registrationYear = registrationYear.toString(),
        registrationExpired = registrationExpired,
        registrationImg = registrationImg,
        vehicleName = vehicleName,
        vehicleColor = vehicleColor,
        vehicleType = VehicleType.fromString(vehicleType),
        vehicleImg = vehicleImg,
        vehicleVerified = vehicleVerified,
        vehicleRejectedReason = vehicleRejectedReason
    )

// 🔹 Untuk ReadAll / ReadByDriver Response
fun DataItemDto.toDomain(): Vehicle =
    Vehicle(
        id = id,
        driverId = driver.id,
        registrationNumber = registrationNumber,
        registrationYear = registrationYear,
        registrationExpired = registrationExpired,
        registrationImg = registrationImg,
        vehicleName = vehicleName,
        vehicleColor = vehicleColor,
        vehicleType = VehicleType.fromString(vehicleType),
        vehicleImg = vehicleImg,
        vehicleVerified = vehicleVerified,
        vehicleRejectedReason = vehicleRejectedReason
    )

// 🔹 Untuk ReadById Response
fun DataReadById.toDomain(): Vehicle =
    Vehicle(
        id = id,
        driverId = driver.id,
        registrationNumber = registrationNumber,
        registrationYear = registrationYear,
        registrationExpired = registrationExpired,
        registrationImg = registrationImg,
        vehicleName = vehicleName,
        vehicleColor = vehicleColor,
        vehicleType = VehicleType.fromString(vehicleType),
        vehicleImg = vehicleImg,
        vehicleVerified = vehicleVerified,
        vehicleRejectedReason = vehicleRejectedReason
    )
