package com.example.nebeng.feature_driver_withdrawal.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteDriverWithdrawalResponse(
	@field:SerializedName("message") val message: String
)
