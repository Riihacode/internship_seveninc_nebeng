package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request

import com.example.nebeng.feature_passenger_ride_booking.domain.model.BookingStatus
import com.google.gson.annotations.SerializedName

data class PatchPassengerRideBookingRequest(
	@field:SerializedName("status") val status: String
) {
	companion object {
		fun fromEnum(status: BookingStatus): PatchPassengerRideBookingRequest {
			return PatchPassengerRideBookingRequest(status.value)
		}
	}
}
