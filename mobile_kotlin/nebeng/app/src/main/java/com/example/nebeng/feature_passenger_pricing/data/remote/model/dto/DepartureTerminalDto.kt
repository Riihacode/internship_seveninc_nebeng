package com.example.nebeng.feature_passenger_pricing.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class DepartureTerminalDto(
    @field:SerializedName("terminal_type") val terminalType: String,
    @field:SerializedName("regency_id") val regencyId: Int,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("province_id") val provinceId: Int,
    @field:SerializedName("latitude") val latitude: Double,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("public_terminal_subtype") val publicTerminalSubtype: String,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("district_id") val districtId: Int,
    @field:SerializedName("full_address") val fullAddress: String,
    @field:SerializedName("longitude") val longitude: Double
)