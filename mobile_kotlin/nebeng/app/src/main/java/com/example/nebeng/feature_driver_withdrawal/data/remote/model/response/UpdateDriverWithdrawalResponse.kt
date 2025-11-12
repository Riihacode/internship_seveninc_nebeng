package com.example.nebeng.feature_driver_withdrawal.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class UpdateDriverWithdrawalResponse(
	@field:SerializedName("data") val data: DataUpdate
)

data class DataUpdate(
	@field:SerializedName("account_number") val accountNumber: String,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("rejected_reason") val rejectedReason: String,
	@field:SerializedName("account_name") val accountName: String,
	@field:SerializedName("bank_name") val bankName: String,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("withdrawal_amount") val withdrawalAmount: Int,
	@field:SerializedName("status") val status: String
)
