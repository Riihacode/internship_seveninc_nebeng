package com.example.nebeng.feature_passenger_ride_booking.domain.model

import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_driver.domain.model.Driver
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction

data class PassengerRideBookingFull(
    val booking: PassengerRideBooking,
    val customer: Customer,
    val ride: PassengerRide,
    val driver: Driver,
    val transaction: PassengerTransaction
)
