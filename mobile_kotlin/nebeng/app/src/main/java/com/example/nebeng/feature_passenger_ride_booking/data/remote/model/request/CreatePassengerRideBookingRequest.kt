package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request

import com.example.nebeng.feature_passenger_ride_booking.domain.model.BookingStatus
import com.google.gson.annotations.SerializedName

data class CreatePassengerRideBookingRequest(
	@field:SerializedName("passenger_ride_id") val passengerRideId: Int,
	@field:SerializedName("total_price") val totalPrice: Int,
	@field:SerializedName("customer_id") val customerId: Int,
	@field:SerializedName("seats_reserved") val seatsReserved: Int,
	@field:SerializedName("status") val status: String
) {
	companion object {
		fun fromEnum(
			passengerRideId: Int,
			totalPrice: Int,
			customerId: Int,
			seatsReserved: Int,
			status: BookingStatus
		): CreatePassengerRideBookingRequest {
			return CreatePassengerRideBookingRequest(
				passengerRideId = passengerRideId,
				totalPrice = totalPrice,
				customerId = customerId,
				seatsReserved = seatsReserved,
				status = status.value)
		}
	}
}
