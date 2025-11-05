package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.dto.DataDto
import com.google.gson.annotations.SerializedName

data class ReadByIdPassengerRideBookingResponse(
	@field:SerializedName("data") val dataDto: DataDto
)
//{
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
//	data class Customer(
//		@field:SerializedName("face_img") val faceImg: String?,
//		@field:SerializedName("id_card_fullname") val idCardFullname: String,
//		@field:SerializedName("verified") val verified: Boolean,
//		@field:SerializedName("credit_amount") val creditAmount: Int,
//		@field:SerializedName("created_at") val createdAt: String,
//		@field:SerializedName("telephone") val telephone: String,
//		@field:SerializedName("full_address") val fullAddress: String,
//		@field:SerializedName("face_with_id_img") val faceWithIdImg: String?,
//		@field:SerializedName("profile_img") val profileImg: String?,
//		@field:SerializedName("id_card_img") val idCardImg: String?,
//		@field:SerializedName("full_name") val fullName: String,
//		@field:SerializedName("updated_at") val updatedAt: String,
//		@field:SerializedName("user_id") val userId: Int,
//		@field:SerializedName("id") val id: Int,
//		@field:SerializedName("id_card_number") val idCardNumber: String,
//		@field:SerializedName("id_card_birthdate") val idCardBirthdate: String
//	)
//
//	data class Driver(
//		@field:SerializedName("credit_score") val creditScore: Int,
//		@field:SerializedName("face_img") val faceImg: String?,
//		@field:SerializedName("driver_license_number") val driverLicenseNumber: String,
//		@field:SerializedName("driver_license_img") val driverLicenseImg: String?,
//		@field:SerializedName("Police_clearance_certificate_img") val policeClearanceCertificateImg: String?,
//		@field:SerializedName("id_card_rejected_reason") val idCardRejectedReason: String,
//		@field:SerializedName("created_at") val createdAt: String,
//		@field:SerializedName("full_address") val fullAddress: String,
//		@field:SerializedName("face_with_id_img") val faceWithIdImg: String?,
//		@field:SerializedName("Police_clearance_certificate_expired") val policeClearanceCertificateExpired: String,
//		@field:SerializedName("Police_clearance_certificate_number") val policeClearanceCertificateNumber: String,
//		@field:SerializedName("driver_license_type") val driverLicenseType: String,
//		@field:SerializedName("id_card_img") val idCardImg: String?,
//		@field:SerializedName("Police_clearance_rejected_reason") val policeClearanceRejectedReason: String,
//		@field:SerializedName("balance") val balance: Int,
//		@field:SerializedName("Police_clearance_verified") val policeClearanceVerified: Boolean,
//		@field:SerializedName("updated_at") val updatedAt: String,
//		@field:SerializedName("total_rating") val totalRating: Int,
//		@field:SerializedName("id") val id: Int,
//		@field:SerializedName("driver_license_rejected_reason") val driverLicenseRejectedReason: String,
//		@field:SerializedName("id_card_fullname") val idCardFullname: String,
//		@field:SerializedName("telephone") val telephone: String,
//		@field:SerializedName("average_rating") val averageRating: String,
//		@field:SerializedName("id_card_verified") val idCardVerified: Boolean,
//		@field:SerializedName("rating_count") val ratingCount: Int,
//		@field:SerializedName("profile_img") val profileImg: String?,
//		@field:SerializedName("full_name") val fullName: String,
//		@field:SerializedName("user_id") val userId: Int,
//		@field:SerializedName("driver_license_verified") val driverLicenseVerified: Boolean,
//		@field:SerializedName("id_card_number") val idCardNumber: String,
//		@field:SerializedName("driver_license_expired") val driverLicenseExpired: String,
//		@field:SerializedName("Police_clearance_certificate_fullname") val policeClearanceCertificateFullname: String,
//		@field:SerializedName("id_card_birthdate") val idCardBirthdate: String
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
//		@field:SerializedName("driver") val driver: Driver,
//		@field:SerializedName("id") val id: Int,
//		@field:SerializedName("departure_time") val departureTime: String,
//		@field:SerializedName("seats_reserved") val seatsReserved: Int,
//		@field:SerializedName("ride_status") val rideStatus: String
//	)
//
//	data class Data(
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
//		@field:SerializedName("status") val status: String,
//		@field:SerializedName("customer") val customer: Customer
//	)
//}
