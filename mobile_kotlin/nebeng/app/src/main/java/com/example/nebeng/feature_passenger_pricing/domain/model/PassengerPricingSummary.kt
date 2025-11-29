package com.example.nebeng.feature_passenger_pricing.domain.model

import com.example.nebeng.core.utils.VehicleType

data class PassengerPricingSummary(
    val id: Int,
    val vehicleType: VehicleType,
    val departureTerminalId: Int,
    val arrivalTerminalId: Int,
    val pricePerSeat: Int,
    val commissionPercentage: Int,
    val createdAt: String,
    val updatedAt: String
)