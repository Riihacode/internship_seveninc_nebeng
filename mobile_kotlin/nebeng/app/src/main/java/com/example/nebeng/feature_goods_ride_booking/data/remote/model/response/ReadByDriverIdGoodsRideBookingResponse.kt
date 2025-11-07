package com.example.nebeng.feature_goods_ride_booking.data.remote.model.response

import com.example.nebeng.feature_goods_ride_booking.data.remote.model.dto.CustomerDto
import com.google.gson.annotations.SerializedName

data class ReadByDriverIdGoodsRideBookingResponse(
	@field:SerializedName("data") val data: List<DataItemReadByDriverId>
)
data class GoodsRideReadByDriverId(
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

data class DataItemReadByDriverId(
	@field:SerializedName("booking_code") val bookingCode: String,
	@field:SerializedName("item_img") val itemImg: String,
	@field:SerializedName("total_price") val totalPrice: Int,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("goods_ride_id") val goodsRideId: Int,
	@field:SerializedName("item_weight") val itemWeight: Int,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("goods_ride") val goodsRide: GoodsRideReadByDriverId,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("customer_id") val customerId: Int,
	@field:SerializedName("item_description") val itemDescription: String,
	@field:SerializedName("status") val status: String,
	@field:SerializedName("customer") val customer: CustomerDto
)

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