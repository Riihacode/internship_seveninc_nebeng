package com.example.nebeng.feature_vehicle.data.remote.model.response

import com.example.nebeng.feature_vehicle.data.remote.model.dto.DataItemDto
import com.google.gson.annotations.SerializedName

data class ReadByDriverIdVehicleResponse(
	@field:SerializedName("data") val data: List<DataItemDto>
)

//data class Driver(
//
//	@field:SerializedName("credit_score")
//	val creditScore: Int,
//
//	@field:SerializedName("face_img")
//	val faceImg: Any,
//
//	@field:SerializedName("driver_license_number")
//	val driverLicenseNumber: String,
//
//	@field:SerializedName("driver_license_img")
//	val driverLicenseImg: Any,
//
//	@field:SerializedName("id_card_rejected_reason")
//	val idCardRejectedReason: Any,
//
//	@field:SerializedName("police_clearance_certificate_expired")
//	val policeClearanceCertificateExpired: String,
//
//	@field:SerializedName("police_clearance_certificate_number")
//	val policeClearanceCertificateNumber: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("full_address")
//	val fullAddress: String,
//
//	@field:SerializedName("face_with_id_img")
//	val faceWithIdImg: Any,
//
//	@field:SerializedName("police_clearance_verified")
//	val policeClearanceVerified: Int,
//
//	@field:SerializedName("driver_license_type")
//	val driverLicenseType: String,
//
//	@field:SerializedName("id_card_img")
//	val idCardImg: Any,
//
//	@field:SerializedName("police_clearance_certificate_fullname")
//	val policeClearanceCertificateFullname: String,
//
//	@field:SerializedName("balance")
//	val balance: Int,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("total_rating")
//	val totalRating: Int,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("driver_license_rejected_reason")
//	val driverLicenseRejectedReason: Any,
//
//	@field:SerializedName("id_card_fullname")
//	val idCardFullname: String,
//
//	@field:SerializedName("telephone")
//	val telephone: String,
//
//	@field:SerializedName("average_rating")
//	val averageRating: Any,
//
//	@field:SerializedName("id_card_verified")
//	val idCardVerified: Boolean,
//
//	@field:SerializedName("police_clearance_certificate_img")
//	val policeClearanceCertificateImg: Any,
//
//	@field:SerializedName("rating_count")
//	val ratingCount: Int,
//
//	@field:SerializedName("profile_img")
//	val profileImg: Any,
//
//	@field:SerializedName("full_name")
//	val fullName: String,
//
//	@field:SerializedName("user_id")
//	val userId: Int,
//
//	@field:SerializedName("driver_license_verified")
//	val driverLicenseVerified: Boolean,
//
//	@field:SerializedName("police_clearance_rejected_reason")
//	val policeClearanceRejectedReason: Any,
//
//	@field:SerializedName("id_card_number")
//	val idCardNumber: String,
//
//	@field:SerializedName("driver_license_expired")
//	val driverLicenseExpired: String,
//
//	@field:SerializedName("id_card_birthdate")
//	val idCardBirthdate: String
//)

//data class DataItem(
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
//	@field:SerializedName("driver")
//	val driver: Driver,
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
