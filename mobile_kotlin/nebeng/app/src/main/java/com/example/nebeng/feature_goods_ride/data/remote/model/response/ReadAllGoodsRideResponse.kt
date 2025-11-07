package com.example.nebeng.feature_goods_ride.data.remote.model.response

import com.example.nebeng.feature_goods_ride.data.remote.model.dto.ArrivalTerminalDto
import com.example.nebeng.feature_goods_ride.data.remote.model.dto.DepartureTerminalDto
import com.example.nebeng.feature_goods_ride.data.remote.model.dto.DriverDto
import com.google.gson.annotations.SerializedName

data class ReadAllGoodsRideResponse(
	@field:SerializedName("data") val data: List<DataItem>
)

data class DataItem(
	@field:SerializedName("departure_terminal_id") val departureTerminalId: Int,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("price_per_kg") val pricePerKg: Int,
	@field:SerializedName("commission_percentage") val commissionPercentage: Int,
	@field:SerializedName("arrival_terminal_id") val arrivalTerminalId: Int,
	@field:SerializedName("weight_reserved") val weightReserved: Int,
	@field:SerializedName("public_terminal_subtype") val publicTerminalSubtype: String,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("departure_terminal") val departureTerminal: DepartureTerminalDto,
	@field:SerializedName("max_weight") val maxWeight: Int,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("driver") val driver: DriverDto,
	@field:SerializedName("transport_type") val transportType: String,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("arrival_terminal") val arrivalTerminal: ArrivalTerminalDto,
	@field:SerializedName("departure_time") val departureTime: String,
	@field:SerializedName("ride_status") val rideStatus: String
)

//data class DepartureTerminal(
//
//	@field:SerializedName("terminal_type")
//	val terminalType: String,
//
//	@field:SerializedName("regency_id")
//	val regencyId: Int,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("province_id")
//	val provinceId: Int,
//
//	@field:SerializedName("latitude")
//	val latitude: Any,
//
//	@field:SerializedName("name")
//	val name: String,
//
//	@field:SerializedName("public_terminal_subtype")
//	val publicTerminalSubtype: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("district_id")
//	val districtId: Int,
//
//	@field:SerializedName("full_address")
//	val fullAddress: String,
//
//	@field:SerializedName("longitude")
//	val longitude: Any
//)



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
//	val averageRating: Int,
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

//data class ArrivalTerminal(
//
//	@field:SerializedName("terminal_type")
//	val terminalType: String,
//
//	@field:SerializedName("regency_id")
//	val regencyId: Int,
//
//	@field:SerializedName("updated_at")
//	val updatedAt: String,
//
//	@field:SerializedName("province_id")
//	val provinceId: Int,
//
//	@field:SerializedName("latitude")
//	val latitude: Any,
//
//	@field:SerializedName("name")
//	val name: String,
//
//	@field:SerializedName("public_terminal_subtype")
//	val publicTerminalSubtype: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("id")
//	val id: Int,
//
//	@field:SerializedName("district_id")
//	val districtId: Int,
//
//	@field:SerializedName("full_address")
//	val fullAddress: String,
//
//	@field:SerializedName("longitude")
//	val longitude: Any
//)
