package com.example.nebeng.feature_vehicle.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateVehicleRequest(
	@field:SerializedName("vehicle_color") val vehicleColor: String,
	@field:SerializedName("registration_year") val registrationYear: Int,
	@field:SerializedName("registration_number") val registrationNumber: String,
	@field:SerializedName("registration_img") val registrationImg: String,
	@field:SerializedName("registration_expired") val registrationExpired: String,
	@field:SerializedName("vehicle_type") val vehicleType: String,
	@field:SerializedName("vehicle_verified") val vehicleVerified: Boolean,
	@field:SerializedName("vehicle_rejected_reason") val vehicleRejectedReason: String,
	@field:SerializedName("vehicle_img") val vehicleImg: String,
	@field:SerializedName("vehicle_name") val vehicleName: String
)
