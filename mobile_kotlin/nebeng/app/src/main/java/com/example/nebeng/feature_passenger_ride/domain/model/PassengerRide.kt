package com.example.nebeng.feature_passenger_ride.domain.model

data class PassengerRide(
    val id: Int,
    val driverName: String,              // dari tabel drivers
    val vehicleType: String,             // dari passenger_rides
    val departureTerminalName: String,   // dari terminals
    val arrivalTerminalName: String,     // dari terminals
    val departureTime: String,           // dari passenger_rides
    val seatsAvailable: Int,             // dari passenger_rides
    val seatsReserved: Int,              // dari passenger_rides
    val pricePerSeat: Int,               // dari passenger_rides
    val commissionPercentage: Int,       // dari passenger_rides
    val rideStatus: String,              // dari passenger_rides
    val createdAt: String,               // dari passenger_rides
    val updatedAt: String                // dari passenger_rides
)