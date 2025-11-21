package com.example.nebeng.feature_auth.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CreateLogoutResponse(
    @field:SerializedName("success") val success: Boolean,
    @field:SerializedName("message") val message: String
)