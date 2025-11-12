package com.example.nebeng.feature_vehicle.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteVehicleResponse(
	@field:SerializedName("message") val message: String
)
