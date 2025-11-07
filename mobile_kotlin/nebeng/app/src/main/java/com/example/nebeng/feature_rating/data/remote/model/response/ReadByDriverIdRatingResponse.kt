package com.example.nebeng.feature_rating.data.remote.model.response

import com.example.nebeng.feature_rating.data.remote.model.dto.DataItemDto
import com.google.gson.annotations.SerializedName

data class ReadByDriverIdRatingResponse(
	@field:SerializedName("data") val data: List<DataItemDto>
)

//data class DataItem(
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
//	val customerId: Int,
//
//	@field:SerializedName("customer")
//	val customer: Customer
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
