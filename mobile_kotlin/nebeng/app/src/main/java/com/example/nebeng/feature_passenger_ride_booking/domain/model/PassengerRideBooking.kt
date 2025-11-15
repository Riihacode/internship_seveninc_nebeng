package com.example.nebeng.feature_passenger_ride_booking.domain.model

data class PassengerRideBooking(
    val id: Int,
    val bookingCode: String?,
    val passengerRideId: Int,
    val customerId: Int,
    val seatsReserved: Int,
    val totalPrice: Int,
    val status: String,
    val createdAt: String,
    val updatedAt: String
)
