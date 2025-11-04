package com.example.nebeng.feature_driver.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class RatingsItemDto(
    @field:SerializedName("feedback") val feedback: String,
    @field:SerializedName("driver_id") val driverId: Int,
    @field:SerializedName("rating") val rating: Int,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("customer_id") val customerId: Int
)
