package com.example.nebeng.feature_driver.data.remote.model.response

import com.example.nebeng.feature_driver.data.remote.model.dto.DataDto
import com.google.gson.annotations.SerializedName

data class ReadByIdDriverResponse(
	@field:SerializedName("data") val data: DataDto
)

data class GoodsRidesItem(
	@field:SerializedName("departure_terminal_id") val departureTerminalId: Int,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("price_per_kg") val pricePerKg: Int,
	@field:SerializedName("commission_percentage") val commissionPercentage: Int,
	@field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int,
	@field:SerializedName("weight_reserved") val weightReserved: Int,
	@field:SerializedName("public_terminal_subtype") val publicTerminalSubtype: String,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("max_weight") val maxWeight: Int,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("transport_type") val transportType: String,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("departure_time") val departureTime: String,
	@field:SerializedName("ride_status") val rideStatus: String
)

data class PassengerRidesItem(
	@field:SerializedName("departure_terminal_id") val departureTerminalId: Int,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("price_per_seat") val pricePerSeat: Int,
	@field:SerializedName("commission_percentage") val commissionPercentage: Int,
	@field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int,
	@field:SerializedName("vehicle_type") val vehicleType: String,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("seats_available") val seatsAvailable: Int,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("departure_time") val departureTime: String,
	@field:SerializedName("seats_reserved") val seatsReserved: Int,
	@field:SerializedName("ride_status") val rideStatus: String
)

//data class User(
//
//	@field:SerializedName("user_type")
//	val userType: String,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("name")
//	val name: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("banned")
//	val banned: Int,
//
//	@field:SerializedName("email")
//	val email: String,
//
//	@field:SerializedName("username")
//	val username: String
//)

//data class Data(
//
//	@field:SerializedName("credit_score")
//	val creditScore: Int,
//
//	@field:SerializedName("face_img")
//	val faceImg: Any,
//
//	@field:SerializedName("driver_license_number")
//	val driverLicenseNumber: String,
//
//	@field:SerializedName("driver_license_img")
//	val driverLicenseImg: Any,
//
//	@field:SerializedName("Police_clearance_certificate_img")
//	val policeClearanceCertificateImg: Any,
//
//	@field:SerializedName("id_card_rejected_reason")
//	val idCardRejectedReason: Any,
//
//	@field:SerializedName("driver_withdrawals")
//	val driverWithdrawals: List<Any>,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("vehicles")
//	val vehicles: List<Any>,
//
//	@field:SerializedName("full_address")
//	val fullAddress: String,
//
//	@field:SerializedName("face_with_id_img")
//	val faceWithIdImg: Any,
//
//	@field:SerializedName("Police_clearance_certificate_expired")
//	val policeClearanceCertificateExpired: String,
//
//	@field:SerializedName("driver_commissions")
//	val driverCommissions: List<Any>,
//
//	@field:SerializedName("Police_clearance_certificate_number")
//	val policeClearanceCertificateNumber: String,
//
//	@field:SerializedName("driver_license_type")
//	val driverLicenseType: String,
//
//	@field:SerializedName("id_card_img")
//	val idCardImg: Any,
//
//	@field:SerializedName("Police_clearance_rejected_reason")
//	val policeClearanceRejectedReason: Any,
//
//	@field:SerializedName("balance")
//	val balance: Int,
//
//	@field:SerializedName("Police_clearance_verified")
//	val policeClearanceVerified: Boolean,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("ratings")
//	val ratings: List<RatingsItem>,
//
//	@field:SerializedName("total_rating")
//	val totalRating: Int,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("driver_license_rejected_reason")
//	val driverLicenseRejectedReason: Any,
//
//	@field:SerializedName("passenger_rides")
//	val passengerRides: List<PassengerRidesItem>,
//
//	@field:SerializedName("id_card_fullname")
//	val idCardFullname: String,
//
//	@field:SerializedName("telephone")
//	val telephone: String,
//
//	@field:SerializedName("average_rating")
//	val averageRating: Any,
//
//	@field:SerializedName("id_card_verified")
//	val idCardVerified: Boolean,
//
//	@field:SerializedName("goods_rides")
//	val goodsRides: List<GoodsRidesItem>,
//
//	@field:SerializedName("rating_count")
//	val ratingCount: Int,
//
//	@field:SerializedName("profile_img")
//	val profileImg: Any,
//
//	@field:SerializedName("full_name")
//	val fullName: String,
//
//	@field:SerializedName("user_id")
//	val userId: Int,
//
//	@field:SerializedName("driver_license_verified")
//	val driverLicenseVerified: Boolean,
//
//	@field:SerializedName("id_card_number")
//	val idCardNumber: String,
//
//	@field:SerializedName("driver_license_expired")
//	val driverLicenseExpired: String,
//
//	@field:SerializedName("Police_clearance_certificate_fullname")
//	val policeClearanceCertificateFullname: String,
//
//	@field:SerializedName("id_card_birthdate")
//	val idCardBirthdate: String,
//
//	@field:SerializedName("user")
//	val user: User
//)

//data class RatingsItem(
//
//	@field:SerializedName("feedback")
//	val feedback: String,
//
//	@field:SerializedName("driver_id")
//	val driverId: Int,
//
//	@field:SerializedName("rating")
//	val rating: Int,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("customer_id")
//	val customerId: Int
//)
