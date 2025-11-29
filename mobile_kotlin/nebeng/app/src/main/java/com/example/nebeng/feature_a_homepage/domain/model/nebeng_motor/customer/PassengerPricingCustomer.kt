package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

import com.example.nebeng.core.utils.VehicleType

data class PassengerPricingCustomer(
    val id: Int,
    val vehicleType: VehicleType,
    val departureTerminalId: Int,
    val arrivalTerminalId: Int,
    val pricePerSeat: Int,
    val commissionPercentage: Int,
    val createdAt: String,
    val updatedAt: String
)
