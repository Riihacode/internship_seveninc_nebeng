package com.example.nebeng.feature_credit_score_log.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateCreditScoreLogRequest(
	@field:SerializedName("score_change") val scoreChange: Int,
	@field:SerializedName("notes") val notes: String,
	@field:SerializedName("action_type") val actionType: String
)
