package com.example.nebeng.feature_passenger_ride.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdatePassengerRideRequest(

	@field:SerializedName("departure_terminal_id")
	val departureTerminalId: Int,

	@field:SerializedName("price_per_seat")
	val pricePerSeat: Int,

	@field:SerializedName("commission_percentage")
	val commissionPercentage: Int,

	@field:SerializedName("arrival_terminal_id")
	val arrivalTerminalId: Int,

	@field:SerializedName("vehicle_type")
	val vehicleType: String,

	@field:SerializedName("seats_available")
	val seatsAvailable: Int,

	@field:SerializedName("departure_time")
	val departureTime: String,

	@field:SerializedName("seats_reserved")
	val seatsReserved: Int,

	@field:SerializedName("ride_status")
	val rideStatus: String
)
