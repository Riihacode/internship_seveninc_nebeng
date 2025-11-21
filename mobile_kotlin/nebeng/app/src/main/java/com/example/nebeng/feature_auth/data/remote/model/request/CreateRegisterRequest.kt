package com.example.nebeng.feature_auth.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateRegisterRequest(
    @field:SerializedName("password") val password: String,
    @field:SerializedName("password_confirmation") val passwordConfirmation: String,
    @field:SerializedName("user_type") val userType: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("username") val username: String
)