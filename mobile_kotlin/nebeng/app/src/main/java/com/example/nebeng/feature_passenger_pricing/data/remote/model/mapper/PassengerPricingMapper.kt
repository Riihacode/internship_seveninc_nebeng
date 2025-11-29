package com.example.nebeng.feature_passenger_pricing.data.remote.model.mapper

import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_passenger_pricing.data.remote.model.dto.DataDto
import com.example.nebeng.feature_passenger_pricing.domain.model.PassengerPricingSummary

fun DataDto.toSummary(): PassengerPricingSummary {
    return PassengerPricingSummary(
        id                  = id,
        vehicleType         = VehicleType.fromString(vehicleType),
        departureTerminalId = departureTerminalId ,
        arrivalTerminalId   = arrivalTerminalId,
        pricePerSeat        = pricePerSeat,
        commissionPercentage= commisionPercentage,
        createdAt           = createdAt,
        updatedAt           = updatedAt
    )
}