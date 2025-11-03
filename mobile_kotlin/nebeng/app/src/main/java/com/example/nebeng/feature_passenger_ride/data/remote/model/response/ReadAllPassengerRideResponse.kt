package com.example.nebeng.feature_passenger_ride.data.remote.model.response

import com.google.gson.annotations.SerializedName

// NOTE: Model ini mengikuti struktur JSON backend secara penuh (tanpa simplifikasi)
data class ReadAllPassengerRideResponse(

	@field:SerializedName("data")
	val data: List<DataItem>? = emptyList()
)

data class DataItem(

	@field:SerializedName("departure_terminal_id")
	val departureTerminalId: Int,

	@field:SerializedName("driver_id")
	val driverId: Int,

	@field:SerializedName("price_per_seat")
	val pricePerSeat: Int,

	@field:SerializedName("commission_percentage")
	val commissionPercentage: Int,

	@field:SerializedName("arrival_terminal_id")
	val arrivalTerminalId: Int,

	@field:SerializedName("vehicle_type")
	val vehicleType: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("seats_available")
	val seatsAvailable: Int,

	@field:SerializedName("departure_terminal")
	val departureTerminal: DepartureTerminal,
//	@field:SerializedName("departure_terminal")
//	val departureTerminal: DepartureTerminal? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("driver")
	val driver: Driver,
//	@field:SerializedName("driver")
//	val driver: Driver? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("arrival_terminal")
	val arrivalTerminal: ArrivalTerminal,
//	@field:SerializedName("arrival_terminal")
//	val arrivalTerminal: ArrivalTerminal? = null,

	@field:SerializedName("departure_time")
	val departureTime: String,

	@field:SerializedName("seats_reserved")
	val seatsReserved: Int,

	@field:SerializedName("ride_status")
	val rideStatus: String
)

data class ArrivalTerminal(

	@field:SerializedName("terminal_type")
	val terminalType: String,

	@field:SerializedName("regency_id")
	val regencyId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("province_id")
	val provinceId: Int,

//	@field:SerializedName("latitude")
//	val latitude: Any,
	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("public_terminal_subtype")
	val publicTerminalSubtype: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("district_id")
	val districtId: Int,

	@field:SerializedName("full_address")
	val fullAddress: String,

//	@field:SerializedName("longitude")
//	val longitude: Any
	@field:SerializedName("longitude")
	val longitude: Double
)

data class DepartureTerminal(

	@field:SerializedName("terminal_type")
	val terminalType: String,

	@field:SerializedName("regency_id")
	val regencyId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("province_id")
	val provinceId: Int,

//	@field:SerializedName("latitude")
//	val latitude: Any,
	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("public_terminal_subtype")
	val publicTerminalSubtype: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("district_id")
	val districtId: Int,

	@field:SerializedName("full_address")
	val fullAddress: String,

//	@field:SerializedName("longitude")
//	val longitude: Any
	@field:SerializedName("longitude")
	val longitude: Double
)

data class Driver(

	@field:SerializedName("credit_score")
	val creditScore: Int,

//	@field:SerializedName("face_img")
//	val faceImg: Any,
	@field:SerializedName("face_img")
	val faceImg: String? = null,

	@field:SerializedName("driver_license_number")
	val driverLicenseNumber: String,

//	@field:SerializedName("driver_license_img")
//	val driverLicenseImg: Any,
	@field:SerializedName("driver_license_img")
	val driverLicenseImg: String? = null,

//	@field:SerializedName("Police_clearance_certificate_img")
//	val policeClearanceCertificateImg: Any,
	@field:SerializedName("Police_clearance_certificate_img")
	val policeClearanceCertificateImg: String? = null,

//	@field:SerializedName("id_card_rejected_reason")
//	val idCardRejectedReason: Any,
	@field:SerializedName("id_card_rejected_reason")
	val idCardRejectedReason: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("full_address")
	val fullAddress: String,

//	@field:SerializedName("face_with_id_img")
//	val faceWithIdImg: Any,
	@field:SerializedName("face_with_id_img")
	val faceWithIdImg: String? = null,

	@field:SerializedName("Police_clearance_certificate_expired")
	val policeClearanceCertificateExpired: String,

	@field:SerializedName("Police_clearance_certificate_number")
	val policeClearanceCertificateNumber: String,

	@field:SerializedName("driver_license_type")
	val driverLicenseType: String,

//	@field:SerializedName("id_card_img")
//	val idCardImg: Any,
	@field:SerializedName("id_card_img")
	val idCardImg: String? = null,

//	@field:SerializedName("Police_clearance_rejected_reason")
//	val policeClearanceRejectedReason: Any,
	@field:SerializedName("Police_clearance_rejected_reason")
	val policeClearanceRejectedReason: String? = null,

	@field:SerializedName("balance")
	val balance: Int,

	@field:SerializedName("Police_clearance_verified")
	val policeClearanceVerified: Boolean,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("id")
	val id: Int,

//	@field:SerializedName("driver_license_rejected_reason")
//	val driverLicenseRejectedReason: Any,
	@field:SerializedName("driver_license_rejected_reason")
	val driverLicenseRejectedReason: String? = null,

	@field:SerializedName("id_card_fullname")
	val idCardFullname: String,

	@field:SerializedName("telephone")
	val telephone: String,

	@field:SerializedName("id_card_verified")
	val idCardVerified: Boolean,

//	@field:SerializedName("profile_img")
//	val profileImg: Any,
	@field:SerializedName("profile_img")
	val profileImg: String? = null,

	@field:SerializedName("full_name")
	val fullName: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("driver_license_verified")
	val driverLicenseVerified: Boolean,

	@field:SerializedName("id_card_number")
	val idCardNumber: String,

	@field:SerializedName("driver_license_expired")
	val driverLicenseExpired: String,

	@field:SerializedName("Police_clearance_certificate_fullname")
	val policeClearanceCertificateFullname: String,

	@field:SerializedName("id_card_birthdate")
	val idCardBirthdate: String
)
