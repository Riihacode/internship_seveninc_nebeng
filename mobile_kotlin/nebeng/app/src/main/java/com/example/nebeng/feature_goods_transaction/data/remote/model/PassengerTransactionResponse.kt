package com.example.nebeng.feature_goods_transaction.data.remote.model

import com.google.gson.annotations.SerializedName

data class PassengerTransactionResponse(

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class Customer(

	@field:SerializedName("face_img")
	val faceImg: Any,

	@field:SerializedName("id_card_fullname")
	val idCardFullname: String,

	@field:SerializedName("verified")
	val verified: Boolean,

	@field:SerializedName("credit_amount")
	val creditAmount: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("telephone")
	val telephone: String,

	@field:SerializedName("full_address")
	val fullAddress: String,

	@field:SerializedName("face_with_id_img")
	val faceWithIdImg: Any,

	@field:SerializedName("profile_img")
	val profileImg: Any,

	@field:SerializedName("id_card_img")
	val idCardImg: Any,

	@field:SerializedName("full_name")
	val fullName: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_card_number")
	val idCardNumber: String,

	@field:SerializedName("id_card_birthdate")
	val idCardBirthdate: String
)

data class DataItem(

	@field:SerializedName("transaction_date")
	val transactionDate: String,

	@field:SerializedName("payment_status")
	val paymentStatus: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("payment_proof_img")
	val paymentProofImg: Any,

	@field:SerializedName("credit_used")
	val creditUsed: Int,

	@field:SerializedName("passenger_ride_booking")
	val passengerRideBooking: PassengerRideBooking,

	@field:SerializedName("payment_method_id")
	val paymentMethodId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("total_amount")
	val totalAmount: Int,

	@field:SerializedName("passenger_ride_booking_id")
	val passengerRideBookingId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("customer_id")
	val customerId: Int,

	@field:SerializedName("payment_method")
	val paymentMethod: PaymentMethod,

	@field:SerializedName("customer")
	val customer: Customer
)

data class PassengerRideBooking(

	@field:SerializedName("passenger_ride_id")
	val passengerRideId: Int,

	@field:SerializedName("total_price")
	val totalPrice: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("customer_id")
	val customerId: Int,

	@field:SerializedName("seats_reserved")
	val seatsReserved: Int,

	@field:SerializedName("status")
	val status: String
)

data class PaymentMethod(

	@field:SerializedName("account_number")
	val accountNumber: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("account_name")
	val accountName: String,

	@field:SerializedName("bank_name")
	val bankName: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int
)
