package com.example.nebeng.feature_goods_ride_booking.data.remote.model.response

import com.example.nebeng.feature_goods_ride_booking.data.remote.model.dto.CustomerDto
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.dto.GoodsRideReadAllReadByIdDto
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.dto.GoodsTransactionDto
import com.google.gson.annotations.SerializedName

data class ReadAllGoodsRideBookingResponse(
	@field:SerializedName("data") val data: List<DataItemReadAll>
)

data class DataItemReadAll(
	@field:SerializedName("booking_code") val bookingCode: String,
	@field:SerializedName("item_img") val itemImg: Any,
	@field:SerializedName("total_price") val totalPrice: Int,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("goods_ride_id") val goodsRideId: Int,
	@field:SerializedName("item_weight") val itemWeight: Int,
	@field:SerializedName("goods_transaction") val goodsTransaction: GoodsTransactionDto,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("goods_ride") val goodsRide: GoodsRideReadAllReadByIdDto,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("customer_id") val customerId: Int,
	@field:SerializedName("item_description") val itemDescription: String,
	@field:SerializedName("status") val status: String,
	@field:SerializedName("customer") val customer: CustomerDto
)
//data class Driver(
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
//	@field:SerializedName("id_card_rejected_reason")
//	val idCardRejectedReason: Any,
//
//	@field:SerializedName("police_clearance_certificate_expired")
//	val policeClearanceCertificateExpired: String,
//
//	@field:SerializedName("police_clearance_certificate_number")
//	val policeClearanceCertificateNumber: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("full_address")
//	val fullAddress: String,
//
//	@field:SerializedName("face_with_id_img")
//	val faceWithIdImg: Any,
//
//	@field:SerializedName("police_clearance_verified")
//	val policeClearanceVerified: Int,
//
//	@field:SerializedName("driver_license_type")
//	val driverLicenseType: String,
//
//	@field:SerializedName("id_card_img")
//	val idCardImg: Any,
//
//	@field:SerializedName("police_clearance_certificate_fullname")
//	val policeClearanceCertificateFullname: String,
//
//	@field:SerializedName("balance")
//	val balance: Int,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
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
//	@field:SerializedName("id_card_fullname")
//	val idCardFullname: String,
//
//	@field:SerializedName("telephone")
//	val telephone: String,
//
//	@field:SerializedName("average_rating")
//	val averageRating: Int,
//
//	@field:SerializedName("id_card_verified")
//	val idCardVerified: Boolean,
//
//	@field:SerializedName("police_clearance_certificate_img")
//	val policeClearanceCertificateImg: Any,
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
//	@field:SerializedName("police_clearance_rejected_reason")
//	val policeClearanceRejectedReason: Any,
//
//	@field:SerializedName("id_card_number")
//	val idCardNumber: String,
//
//	@field:SerializedName("driver_license_expired")
//	val driverLicenseExpired: String,
//
//	@field:SerializedName("id_card_birthdate")
//	val idCardBirthdate: String
//)

//data class Customer(
//
//	@field:SerializedName("face_img")
//	val faceImg: Any,
//
//	@field:SerializedName("id_card_fullname")
//	val idCardFullname: String,
//
//	@field:SerializedName("verified")
//	val verified: Boolean,
//
//	@field:SerializedName("credit_amount")
//	val creditAmount: Int,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("telephone")
//	val telephone: String,
//
//	@field:SerializedName("full_address")
//	val fullAddress: String,
//
//	@field:SerializedName("face_with_id_img")
//	val faceWithIdImg: Any,
//
//	@field:SerializedName("profile_img")
//	val profileImg: Any,
//
//	@field:SerializedName("id_card_img")
//	val idCardImg: Any,
//
//	@field:SerializedName("full_name")
//	val fullName: String,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("user_id")
//	val userId: Int,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("id_card_number")
//	val idCardNumber: String,
//
//	@field:SerializedName("id_card_birthdate")
//	val idCardBirthdate: String
//)

//data class GoodsRide(
//
//	@field:SerializedName("departure_terminal_id")
//	val departureTerminalId: Int,
//
//	@field:SerializedName("driver_id")
//	val driverId: Int,
//
//	@field:SerializedName("price_per_kg")
//	val pricePerKg: Int,
//
//	@field:SerializedName("commission_percentage")
//	val commissionPercentage: Int,
//
//	@field:SerializedName("arrival_terminal_id")
//	val arrivalTerminalId: Int,
//
//	@field:SerializedName("weight_reserved")
//	val weightReserved: Int,
//
//	@field:SerializedName("public_terminal_subtype")
//	val publicTerminalSubtype: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("max_weight")
//	val maxWeight: Int,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("driver")
//	val driver: Driver,
//
//	@field:SerializedName("transport_type")
//	val transportType: String,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("departure_time")
//	val departureTime: String,
//
//	@field:SerializedName("ride_status")
//	val rideStatus: String
//)



//data class GoodsTransaction(
//
//	@field:SerializedName("transaction_date")
//	val transactionDate: String,
//
//	@field:SerializedName("transaction_code")
//	val transactionCode: String,
//
//	@field:SerializedName("payment_method_id")
//	val paymentMethodId: Int,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("total_amount")
//	val totalAmount: Int,
//
//	@field:SerializedName("payment_status")
//	val paymentStatus: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("goods_ride_booking_id")
//	val goodsRideBookingId: Int,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("customer_id")
//	val customerId: Int,
//
//	@field:SerializedName("payment_proof_img")
//	val paymentProofImg: Any,
//
//	@field:SerializedName("credit_used")
//	val creditUsed: Int
//)
