package com.example.nebeng.feature_user.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
	@field:SerializedName("password") val password: String,
	@field:SerializedName("user_type") val userType: String,
	@field:SerializedName("name") val name: String,
	@field:SerializedName("banned") val banned: Boolean,
	@field:SerializedName("email") val email: String,
	@field:SerializedName("username") val username: String
)
