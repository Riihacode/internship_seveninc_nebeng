package com.example.nebeng.feature_goods_ride_booking.data.remote.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class GoodsRide(

	@field:SerializedName("departure_terminal_id")
	val departureTerminalId: Int? = null,

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

	@field:SerializedName("price_per_kg")
	val pricePerKg: Int? = null,

	@field:SerializedName("commission_percentage")
	val commissionPercentage: Int? = null,

	@field:SerializedName("arrival_terminal_id")
	val arrivalTerminalId: Int? = null,

	@field:SerializedName("weight_reserved")
	val weightReserved: Int? = null,

	@field:SerializedName("public_terminal_subtype")
	val publicTerminalSubtype: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("max_weight")
	val maxWeight: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("driver")
	val driver: Driver? = null,

	@field:SerializedName("transport_type")
	val transportType: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("departure_time")
	val departureTime: String? = null,

	@field:SerializedName("ride_status")
	val rideStatus: String? = null
)

data class GoodsTransaction(

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("transaction_code")
	val transactionCode: String? = null,

	@field:SerializedName("payment_method_id")
	val paymentMethodId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("total_amount")
	val totalAmount: Int? = null,

	@field:SerializedName("payment_status")
	val paymentStatus: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("goods_ride_booking_id")
	val goodsRideBookingId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("customer_id")
	val customerId: Int? = null,

	@field:SerializedName("payment_proof_img")
	val paymentProofImg: Any? = null,

	@field:SerializedName("credit_used")
	val creditUsed: Int? = null
)

data class DataItem(

	@field:SerializedName("booking_code")
	val bookingCode: String? = null,

	@field:SerializedName("item_img")
	val itemImg: Any? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("goods_ride_id")
	val goodsRideId: Int? = null,

	@field:SerializedName("item_weight")
	val itemWeight: Int? = null,

	@field:SerializedName("goods_transaction")
	val goodsTransaction: GoodsTransaction? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("goods_ride")
	val goodsRide: GoodsRide? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("customer_id")
	val customerId: Int? = null,

	@field:SerializedName("item_description")
	val itemDescription: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("customer")
	val customer: Customer? = null
)

data class Driver(

	@field:SerializedName("credit_score")
	val creditScore: Int? = null,

	@field:SerializedName("face_img")
	val faceImg: Any? = null,

	@field:SerializedName("driver_license_number")
	val driverLicenseNumber: String? = null,

	@field:SerializedName("driver_license_img")
	val driverLicenseImg: Any? = null,

	@field:SerializedName("id_card_rejected_reason")
	val idCardRejectedReason: Any? = null,

	@field:SerializedName("police_clearance_certificate_expired")
	val policeClearanceCertificateExpired: String? = null,

	@field:SerializedName("police_clearance_certificate_number")
	val policeClearanceCertificateNumber: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("full_address")
	val fullAddress: String? = null,

	@field:SerializedName("face_with_id_img")
	val faceWithIdImg: Any? = null,

	@field:SerializedName("police_clearance_verified")
	val policeClearanceVerified: Int? = null,

	@field:SerializedName("driver_license_type")
	val driverLicenseType: String? = null,

	@field:SerializedName("id_card_img")
	val idCardImg: Any? = null,

	@field:SerializedName("police_clearance_certificate_fullname")
	val policeClearanceCertificateFullname: String? = null,

	@field:SerializedName("balance")
	val balance: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("total_rating")
	val totalRating: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("driver_license_rejected_reason")
	val driverLicenseRejectedReason: Any? = null,

	@field:SerializedName("id_card_fullname")
	val idCardFullname: String? = null,

	@field:SerializedName("telephone")
	val telephone: String? = null,

	@field:SerializedName("average_rating")
	val averageRating: Int? = null,

	@field:SerializedName("id_card_verified")
	val idCardVerified: Boolean? = null,

	@field:SerializedName("police_clearance_certificate_img")
	val policeClearanceCertificateImg: Any? = null,

	@field:SerializedName("rating_count")
	val ratingCount: Int? = null,

	@field:SerializedName("profile_img")
	val profileImg: Any? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("driver_license_verified")
	val driverLicenseVerified: Boolean? = null,

	@field:SerializedName("police_clearance_rejected_reason")
	val policeClearanceRejectedReason: Any? = null,

	@field:SerializedName("id_card_number")
	val idCardNumber: String? = null,

	@field:SerializedName("driver_license_expired")
	val driverLicenseExpired: String? = null,

	@field:SerializedName("id_card_birthdate")
	val idCardBirthdate: String? = null
)

data class Customer(

	@field:SerializedName("face_img")
	val faceImg: Any? = null,

	@field:SerializedName("id_card_fullname")
	val idCardFullname: String? = null,

	@field:SerializedName("verified")
	val verified: Boolean? = null,

	@field:SerializedName("credit_amount")
	val creditAmount: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("telephone")
	val telephone: String? = null,

	@field:SerializedName("full_address")
	val fullAddress: String? = null,

	@field:SerializedName("face_with_id_img")
	val faceWithIdImg: Any? = null,

	@field:SerializedName("profile_img")
	val profileImg: Any? = null,

	@field:SerializedName("id_card_img")
	val idCardImg: Any? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_card_number")
	val idCardNumber: String? = null,

	@field:SerializedName("id_card_birthdate")
	val idCardBirthdate: String? = null
)
