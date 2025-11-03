package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdatePassengerRideBookingRequest(
	@field:SerializedName("total_price") val totalPrice: Int,
	@field:SerializedName("seats_reserved") val seatsReserved: Int,
	@field:SerializedName("status") val status: String
)
