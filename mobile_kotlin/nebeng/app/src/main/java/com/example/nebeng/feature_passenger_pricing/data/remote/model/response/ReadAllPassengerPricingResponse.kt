package com.example.nebeng.feature_passenger_pricing.data.remote.model.response

import com.example.nebeng.feature_passenger_pricing.data.remote.model.dto.DataDto
import com.google.gson.annotations.SerializedName

data class ReadAllPassengerPricingResponse(
	@field:SerializedName("data") val data: List<DataDto>
)

//data class ArrivalTerminal(
//	@field:SerializedName("terminal_type") val terminalType: String? = null,
//	@field:SerializedName("regency_id") val regencyId: Int? = null,
//	@field:SerializedName("updated_at") val updatedAt: String? = null,
//	@field:SerializedName("province_id") val provinceId: Int? = null,
//	@field:SerializedName("latitude") val latitude: Double? = null,
//	@field:SerializedName("name") val name: String? = null,
//	@field:SerializedName("public_terminal_subtype") val publicTerminalSubtype: String? = null,
//	@field:SerializedName("created_at") val createdAt: String? = null,
//	@field:SerializedName("id") val id: Int? = null,
//	@field:SerializedName("district_id") val districtId: Int? = null,
//	@field:SerializedName("full_address") val fullAddress: String? = null,
//	@field:SerializedName("longitude") val longitude: Double? = null
//)

//data class DataItem(
//	@field:SerializedName("departure_terminal_id") val departureTerminalId: Int? = null,
//	@field:SerializedName("departure_terminal") val departureTerminal: DepartureTerminal? = null,
//	@field:SerializedName("price_per_seat") val pricePerSeat: Int? = null,
//	@field:SerializedName("commision_percentage") val commisionPercentage: Int? = null,
//	@field:SerializedName("updated_at") val updatedAt: String? = null,
//	@field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int? = null,
//	@field:SerializedName("vehicle_type") val vehicleType: String? = null,
//	@field:SerializedName("created_at") val createdAt: String? = null,
//	@field:SerializedName("id") val id: Int? = null,
//	@field:SerializedName("arrival_terminal") val arrivalTerminal: ArrivalTerminal? = null
//)

//data class DepartureTerminal(
//	@field:SerializedName("terminal_type") val terminalType: String? = null,
//	@field:SerializedName("regency_id") val regencyId: Int? = null,
//	@field:SerializedName("updated_at") val updatedAt: String? = null,
//	@field:SerializedName("province_id") val provinceId: Int? = null,
//	@field:SerializedName("latitude") val latitude: Double? = null,
//	@field:SerializedName("name") val name: String? = null,
//	@field:SerializedName("public_terminal_subtype") val publicTerminalSubtype: String? = null,
//	@field:SerializedName("created_at") val createdAt: String? = null,
//	@field:SerializedName("id") val id: Int? = null,
//	@field:SerializedName("district_id") val districtId: Int? = null,
//	@field:SerializedName("full_address") val fullAddress: String? = null,
//	@field:SerializedName("longitude") val longitude: Double? = null
//)
