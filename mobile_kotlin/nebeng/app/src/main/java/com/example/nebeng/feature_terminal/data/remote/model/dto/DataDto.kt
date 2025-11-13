package com.example.nebeng.feature_terminal.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class DataDto(
    @field:SerializedName("terminal_type") val terminalType: String? = null,
    @field:SerializedName("regency_id") val regencyId: Int? = null,
    @field:SerializedName("latitude") val latitude: Double? = null,
    @field:SerializedName("public_terminal_subtype") val publicTerminalSubtype: String? = null,
    @field:SerializedName("created_at") val createdAt: String? = null,
    @field:SerializedName("regency") val regency: RegencyDto? = null,
    @field:SerializedName("full_address") val fullAddress: String? = null,
    @field:SerializedName("updated_at") val updatedAt: String? = null,
    @field:SerializedName("province") val province: ProvinceDto? = null,
    @field:SerializedName("province_id") val provinceId: Int? = null,
    @field:SerializedName("district") val district: DistrictDto? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("district_id") val districtId: Int? = null,
    @field:SerializedName("longitude") val longitude: Double? = null
)
