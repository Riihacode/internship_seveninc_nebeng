package com.example.nebeng.feature_driver_withdrawal.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateDriverWithdrawalRequest(
	@field:SerializedName("account_number") val accountNumber: String,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("rejected_reason") val rejectedReason: String,
	@field:SerializedName("account_name") val accountName: String,
	@field:SerializedName("bank_name") val bankName: String,
	@field:SerializedName("withdrawal_amount") val withdrawalAmount: Int,
	@field:SerializedName("status") val status: String
)
