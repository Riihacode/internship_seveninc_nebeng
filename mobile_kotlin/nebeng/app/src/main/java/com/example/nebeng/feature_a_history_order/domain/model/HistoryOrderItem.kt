package com.example.nebeng.feature_a_history_order.domain.model

import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType

/**
 * ============================================================
 * 🧾 Domain Model: HistoryOrderItem
 * ------------------------------------------------------------
 * Mewakili satu entitas riwayat pemesanan (order history)
 * yang ditampilkan di layar customer maupun driver.
 * ============================================================
 */
data class HistoryOrderItem(
    val bookingId: Int,
    val bookingCode: String?,
    val createdAt: String,

    val departureTerminalId: Int,
    val arrivalTerminalId: Int,

    val seatsReserved: Int,
    val totalPrice: Int,

    val bookingStatus: BookingStatus?,
    val vehicleType: VehicleType,
    val rideStatus: RideStatus?,
    val driverName: String?
)