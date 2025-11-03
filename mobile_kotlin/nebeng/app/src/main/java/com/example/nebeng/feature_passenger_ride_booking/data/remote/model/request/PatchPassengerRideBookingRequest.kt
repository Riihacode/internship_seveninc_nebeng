package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class PatchPassengerRideBookingRequest(
	@field:SerializedName("status") val status: String
)
