package com.example.nebeng.feature_credit_score_log.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateCreditScoreLogRequest(
	@field:SerializedName("score_change") val scoreChange: Int,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("notes") val notes: String,
	@field:SerializedName("action_type") val actionType: String,
	@field:SerializedName("created_at") val createdAt: String
)
