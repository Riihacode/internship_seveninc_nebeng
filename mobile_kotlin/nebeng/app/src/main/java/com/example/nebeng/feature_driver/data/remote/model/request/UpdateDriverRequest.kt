package com.example.nebeng.feature_driver.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateDriverRequest(
	@field:SerializedName("profile_img") val profileImg: String,
	@field:SerializedName("credit_score") val creditScore: Int,
	@field:SerializedName("full_name") val fullName: String,
	@field:SerializedName("balance") val balance: Int,
	@field:SerializedName("Police_clearance_verified") val policeClearanceVerified: Boolean,
	@field:SerializedName("driver_license_verified") val driverLicenseVerified: Boolean,
	@field:SerializedName("telephone") val telephone: String,
	@field:SerializedName("full_address") val fullAddress: String,
	@field:SerializedName("id_card_verified") val idCardVerified: Boolean
)
