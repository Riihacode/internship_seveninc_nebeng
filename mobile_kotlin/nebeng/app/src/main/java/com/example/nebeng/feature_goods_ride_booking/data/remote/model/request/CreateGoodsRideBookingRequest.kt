package com.example.nebeng.feature_goods_ride_booking.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateGoodsRideBookingRequest(
	@field:SerializedName("goods_ride_id") val goodsRideId: Int,
	@field:SerializedName("item_weight") val itemWeight: Int,
	@field:SerializedName("item_img") val itemImg: String,
	@field:SerializedName("total_price") val totalPrice: Int,
	@field:SerializedName("customer_id") val customerId: Int,
	@field:SerializedName("item_description") val itemDescription: String,
	@field:SerializedName("status") val status: String
)
