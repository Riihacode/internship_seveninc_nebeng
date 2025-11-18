package com.example.nebeng.feature_auth.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateLoginRequest(
    @field:SerializedName("userIdentifier") val userIdentifier: String,
    @field:SerializedName("password") val password: String
)