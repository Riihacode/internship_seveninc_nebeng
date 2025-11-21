package com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order

data class BookingRideSummary(
    val id: Int,
    val departureTerminalId: Int,
    val arrivalTerminalId: Int,
    val vehicleType: String,
    val departureTime: String,
    val seatsReserved: Int,
    val pricePerSeat: Int,
    val rideStatus: String
)