package com.example.nebeng.feature_vehicle.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class PatchVerifyFalseByIdVehicleRequest(
	@field:SerializedName("reason") val reason: String
)
