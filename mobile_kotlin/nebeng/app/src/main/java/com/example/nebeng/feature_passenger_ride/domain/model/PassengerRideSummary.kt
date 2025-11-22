package com.example.nebeng.feature_passenger_ride.domain.model

data class PassengerRideSummary(
    val id: Int,
    val driverId: Int,              // dari tabel drivers
    val vehicleType: String,             // dari passenger_rides
    val departureTerminalId: Int,   // dari terminals
    val arrivalTerminalId: Int,     // dari terminals
    val departureTime: String,           // dari passenger_rides
    val seatsAvailable: Int,             // dari passenger_rides
    val seatsReserved: Int,              // dari passenger_rides
    val pricePerSeat: Int,               // dari passenger_rides
    val commissionPercentage: Int,       // dari passenger_rides
    val rideStatus: String,              // dari passenger_rides
    val createdAt: String,               // dari passenger_rides
    val updatedAt: String                // dari passenger_rides
) {
    companion object {
        fun getEmpty(): PassengerRideSummary = PassengerRideSummary(
            id = 0,
            driverId = 0,
            departureTerminalId = 0,
            arrivalTerminalId = 0,
            vehicleType = "",
            pricePerSeat = 0,
            commissionPercentage = 0,
            seatsAvailable = 0,
            seatsReserved = 0,
            rideStatus = "",
            departureTime = "",
            createdAt = "",
            updatedAt = ""
        )
    }
}