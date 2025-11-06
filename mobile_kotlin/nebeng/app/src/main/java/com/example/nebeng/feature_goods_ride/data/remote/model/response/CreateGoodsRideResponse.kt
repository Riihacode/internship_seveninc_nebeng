package com.example.nebeng.feature_goods_ride.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CreateGoodsRideResponse(
	@field:SerializedName("data") val data: DataCreate
)

data class DataCreate(
	@field:SerializedName("departure_terminal_id") val departureTerminalId: Int,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("price_per_kg") val pricePerKg: Int,
	@field:SerializedName("commission_percentage") val commissionPercentage: Int,
	@field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int,
	@field:SerializedName("weight_reserved") val weightReserved: Int,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("max_weight") val maxWeight: Int,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("transport_type") val transportType: String,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("departure_time") val departureTime: String,
	@field:SerializedName("ride_status") val rideStatus: String
)
