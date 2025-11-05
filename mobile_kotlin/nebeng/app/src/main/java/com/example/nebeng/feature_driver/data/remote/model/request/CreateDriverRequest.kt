package com.example.nebeng.feature_driver.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateDriverRequest(
	@field:SerializedName("credit_score") val creditScore: Int,
	@field:SerializedName("face_img") val faceImg: String,
	@field:SerializedName("driver_license_number") val driverLicenseNumber: String,
	@field:SerializedName("driver_license_img") val driverLicenseImg: String,
	@field:SerializedName("id_card_fullname") val idCardFullname: String,
	@field:SerializedName("Police_clearance_certificate_img") val policeClearanceCertificateImg: String,
	@field:SerializedName("telephone") val telephone: String,
	@field:SerializedName("full_address") val fullAddress: String,
	@field:SerializedName("face_with_id_img") val faceWithIdImg: String,
	@field:SerializedName("id_card_verified") val idCardVerified: Boolean,
	@field:SerializedName("Police_clearance_certificate_expired") val policeClearanceCertificateExpired: String,
	@field:SerializedName("Police_clearance_certificate_number") val policeClearanceCertificateNumber: String,
	@field:SerializedName("driver_license_type") val driverLicenseType: String,
	@field:SerializedName("profile_img") val profileImg: String,
	@field:SerializedName("id_card_img") val idCardImg: String,
	@field:SerializedName("full_name") val fullName: String,
	@field:SerializedName("balance") val balance: Int,
	@field:SerializedName("Police_clearance_verified") val policeClearanceVerified: Boolean,
	@field:SerializedName("user_id") val userId: Int,
	@field:SerializedName("driver_license_verified") val driverLicenseVerified: Boolean,
	@field:SerializedName("id_card_number") val idCardNumber: String,
	@field:SerializedName("driver_license_expired") val driverLicenseExpired: String,
	@field:SerializedName("Police_clearance_certificate_fullname") val policeClearanceCertificateFullname: String,
	@field:SerializedName("id_card_birthdate") val idCardBirthdate: String
)
