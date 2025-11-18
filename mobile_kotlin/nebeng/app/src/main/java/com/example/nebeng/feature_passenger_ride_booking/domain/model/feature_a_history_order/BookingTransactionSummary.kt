package com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order

data class BookingTransactionSummary(
    val id: Int,
    val totalAmount: Int,
    val paymentStatus: String?,
    val creditUsed: Int
)
