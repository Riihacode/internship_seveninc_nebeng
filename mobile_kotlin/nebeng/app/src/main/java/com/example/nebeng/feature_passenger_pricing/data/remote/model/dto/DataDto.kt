package com.example.nebeng.feature_passenger_pricing.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class DataDto(
    @field:SerializedName("departure_terminal_id") val departureTerminalId: Int,
    @field:SerializedName("departure_terminal") val departureTerminal: DepartureTerminalDto,
    @field:SerializedName("price_per_seat") val pricePerSeat: Int,
    @field:SerializedName("commision_percentage") val commisionPercentage: Int,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int,
    @field:SerializedName("vehicle_type") val vehicleType: String,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("arrival_terminal") val arrivalTerminal: ArrivalTerminalDto
)