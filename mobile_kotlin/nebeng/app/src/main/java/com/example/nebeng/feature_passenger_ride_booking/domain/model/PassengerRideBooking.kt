package com.example.nebeng.feature_passenger_ride_booking.domain.model

import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_driver.domain.model.Driver
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction

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
