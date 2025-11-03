package com.example.nebeng.feature_passenger_ride_booking.domain.model


import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide

data class PassengerRideBooking(
    val id: Int,
    val bookingCode: String?,
    val passengerRideId: Int,
    val customerId: Int,
    val seatsReserved: Int,
    val totalPrice: Int,
    val status: String,
    val createdAt: String,
    val updatedAt: String,

    val passengerRide: PassengerRide?,      // domain model
    val passengerTransaction: PassengerTransaction?, // domain model
    val customer: Customer?,                // domain model
    val driver: Driver?                     // domain model
)
