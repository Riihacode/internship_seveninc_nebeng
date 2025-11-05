package com.example.nebeng.feature_customer.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateCustomerRequest(
	@field:SerializedName("profile_img") val profileImg: String,
	@field:SerializedName("face_img") val faceImg: String,
	@field:SerializedName("id_card_img") val idCardImg: String,
	@field:SerializedName("full_name") val fullName: String,
	@field:SerializedName("id_card_fullname") val idCardFullname: String,
	@field:SerializedName("verified") val verified: Boolean,
	@field:SerializedName("credit_amount") val creditAmount: Int,
	@field:SerializedName("telephone") val telephone: String,
	@field:SerializedName("full_address") val fullAddress: String,
	@field:SerializedName("id_card_number") val idCardNumber: String,
	@field:SerializedName("face_with_id_img") val faceWithIdImg: String,
	@field:SerializedName("id_card_birthdate") val idCardBirthdate: String
)
