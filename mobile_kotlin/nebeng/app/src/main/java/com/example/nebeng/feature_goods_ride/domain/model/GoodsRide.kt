package com.example.nebeng.feature_goods_ride.domain.model

import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.core.utils.RideStatus
import java.time.LocalDateTime

data class GoodsRide(
    val id: Int,
    val driverId: Int,
    val transportType: TransportType,
    val publicTerminalSubtype: PublicTerminalSubtype,
    val departureTerminalId: Int,
    val arrivalTerminalId: Int,
    val departureTime: LocalDateTime,
    val maxWeight: Int,
    val weightReserved: Int,
    val pricePerKg: Int,
    val commissionPercentage: Int,
    val rideStatus: RideStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
