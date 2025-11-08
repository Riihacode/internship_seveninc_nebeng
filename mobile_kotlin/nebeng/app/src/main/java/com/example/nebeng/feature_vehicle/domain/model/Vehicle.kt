package com.example.nebeng.feature_vehicle.domain.model

import com.example.nebeng.core.utils.VehicleType

data class Vehicle(
    val id: Int,
    val driverId: Int,
    val registrationNumber: String,
    val registrationYear: String,
    val registrationExpired: String,
    val registrationImg: String,
    val vehicleName: String,
    val vehicleColor: String,
    val vehicleType: VehicleType?,
    val vehicleImg: String,
    val vehicleVerified: Boolean = false,
    val vehicleRejectedReason: String? = null,
)