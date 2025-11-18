package com.example.nebeng.feature_auth.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CreateRegisterResponse(
	@field:SerializedName("data") val data: DataRegister,
	@field:SerializedName("success") val success: Boolean,
	@field:SerializedName("message") val message: String
)

data class DataRegister(
	@field:SerializedName("user") val user: UserRegister,
	@field:SerializedName("token") val token: String
)

data class UserRegister(
	@field:SerializedName("user_type") val userType: String,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("name") val name: String,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("email") val email: String,
	@field:SerializedName("username") val username: String
)


