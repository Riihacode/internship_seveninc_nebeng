package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.dto.DataDto
import com.google.gson.annotations.SerializedName

data class ReadByCustomerIdPassengerRideBookingResponse(
	@field:SerializedName("data") val dataDto: List<DataDto>
)
//{
//
//	data class DataItem(
//		@field:SerializedName("booking_code") val bookingCode: String,
//		@field:SerializedName("passenger_ride") val passengerRide: PassengerRide,
//		@field:SerializedName("passenger_ride_id") val passengerRideId: Int,
//		@field:SerializedName("total_price") val totalPrice: Int,
//		@field:SerializedName("updated_at") val updatedAt: String,
//		@field:SerializedName("created_at") val createdAt: String,
//		@field:SerializedName("passenger_transaction") val passengerTransaction: PassengerTransaction,
//		@field:SerializedName("id") val id: Int,
//		@field:SerializedName("customer_id") val customerId: Int,
//		@field:SerializedName("seats_reserved") val seatsReserved: Int,
//		@field:SerializedName("status") val status: String
//	)
//
//	data class PassengerTransaction(
//		@field:SerializedName("transaction_date") val transactionDate: String,
//		@field:SerializedName("payment_method_id") val paymentMethodId: Int,
//		@field:SerializedName("updated_at") val updatedAt: String,
//		@field:SerializedName("total_amount") val totalAmount: Int,
//		@field:SerializedName("payment_status") val paymentStatus: String,
//		@field:SerializedName("passenger_ride_booking_id") val passengerRideBookingId: Int,
//		@field:SerializedName("created_at") val createdAt: String,
//		@field:SerializedName("id") val id: Int,
//		@field:SerializedName("customer_id") val customerId: Int,
//		@field:SerializedName("payment_proof_img") val paymentProofImg: String?,
//		@field:SerializedName("credit_used") val creditUsed: Int
//	)
//
//	data class PassengerRide(
//		@field:SerializedName("departure_terminal_id") val departureTerminalId: Int,
//		@field:SerializedName("driver_id") val driverId: Int,
//		@field:SerializedName("price_per_seat") val pricePerSeat: Int,
//		@field:SerializedName("commission_percentage") val commissionPercentage: Int,
//		@field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int,
//		@field:SerializedName("vehicle_type") val vehicleType: String,
//		@field:SerializedName("created_at") val createdAt: String,
//		@field:SerializedName("seats_available") val seatsAvailable: Int,
//		@field:SerializedName("updated_at") val updatedAt: String,
//		@field:SerializedName("id") val id: Int,
//		@field:SerializedName("departure_time") val departureTime: String,
//		@field:SerializedName("seats_reserved") val seatsReserved: Int,
//		@field:SerializedName("ride_status") val rideStatus: String
//	)
//}
