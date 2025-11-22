package com.example.nebeng.feature_payment_method.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class DataDto(
    @field:SerializedName("account_number") val accountNumber: String,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("account_name") val accountName: String,
    @field:SerializedName("bank_name") val bankName: String,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("id") val id: Int
)
