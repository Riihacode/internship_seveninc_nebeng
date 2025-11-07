package com.example.nebeng.feature_goods_ride.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateGoodsRideRequest(
	@field:SerializedName("departure_terminal_id") val departureTerminalId: Int,
	@field:SerializedName("max_weight") val maxWeight: Int,
	@field:SerializedName("price_per_kg") val pricePerKg: Int,
	@field:SerializedName("transport_type") val transportType: String,
	@field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int,
	@field:SerializedName("public_terminal_subtype") val publicTerminalSubtype: String,
	@field:SerializedName("departure_time") val departureTime: String,
	@field:SerializedName("ride_status") val rideStatus: String
)
