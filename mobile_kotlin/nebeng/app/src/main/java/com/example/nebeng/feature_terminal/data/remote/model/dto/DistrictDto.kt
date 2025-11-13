package com.example.nebeng.feature_terminal.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class DistrictDto(
    @field:SerializedName("regency_id") val regencyId: Int? = null,
    @field:SerializedName("updated_at") val updatedAt: String? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("created_at") val createdAt: String? = null,
    @field:SerializedName("id") val id: Int? = null
)
