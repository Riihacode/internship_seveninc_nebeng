package com.example.nebeng.feature_goods_ride_booking.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class DataCreateUpdateDto(
    @field:SerializedName("goods_ride_id") val goodsRideId: Int,
    @field:SerializedName("booking_code") val bookingCode: String,
    @field:SerializedName("item_weight") val itemWeight: Int,
    @field:SerializedName("item_img") val itemImg: String,
    @field:SerializedName("total_price") val totalPrice: Int,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("customer_id") val customerId: Int,
    @field:SerializedName("item_description") val itemDescription: String,
    @field:SerializedName("status") val status: String
)