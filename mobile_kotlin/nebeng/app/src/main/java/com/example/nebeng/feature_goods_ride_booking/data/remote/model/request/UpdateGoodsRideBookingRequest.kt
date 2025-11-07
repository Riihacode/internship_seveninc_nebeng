package com.example.nebeng.feature_goods_ride_booking.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateGoodsRideBookingRequest(
	@field:SerializedName("item_weight") val itemWeight: Int,
	@field:SerializedName("item_img") val itemImg: String,
	@field:SerializedName("total_price") val totalPrice: Int,
	@field:SerializedName("item_description") val itemDescription: String,
	@field:SerializedName("status") val status: String
)
