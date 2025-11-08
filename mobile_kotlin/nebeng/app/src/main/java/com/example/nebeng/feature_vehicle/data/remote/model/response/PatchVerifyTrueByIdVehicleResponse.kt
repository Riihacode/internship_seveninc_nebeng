package com.example.nebeng.feature_vehicle.data.remote.model.response

import com.example.nebeng.feature_vehicle.data.remote.model.dto.DataDto
import com.google.gson.annotations.SerializedName

data class PatchVerifyTrueByIdVehicleResponse(
	@field:SerializedName("data") val data: DataDto,
	@field:SerializedName("message") val message: String
)

//data class Data(
//
//	@field:SerializedName("driver_id")
//	val driverId: Int,
//
//	@field:SerializedName("vehicle_color")
//	val vehicleColor: String,
//
//	@field:SerializedName("registration_number")
//	val registrationNumber: String,
//
//	@field:SerializedName("registration_expired")
//	val registrationExpired: String,
//
//	@field:SerializedName("vehicle_type")
//	val vehicleType: String,
//
//	@field:SerializedName("vehicle_verified")
//	val vehicleVerified: Boolean,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("vehicle_img")
//	val vehicleImg: String,
//
//	@field:SerializedName("registration_year")
//	val registrationYear: String,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("registration_img")
//	val registrationImg: String,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("vehicle_rejected_reason")
//	val vehicleRejectedReason: Any,
//
//	@field:SerializedName("vehicle_name")
//	val vehicleName: String
//)
