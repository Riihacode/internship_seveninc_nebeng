package com.example.nebeng.feature_credit_score_log.data.remote.model.response

import com.example.nebeng.feature_credit_score_log.data.remote.model.dto.DataDto
import com.google.gson.annotations.SerializedName

data class CreateCreditScoreLogResponse(
	@field:SerializedName("data") val data: DataDto
)

//data class Data(
//
//	@field:SerializedName("score_change")
//	val scoreChange: Int,
//
//	@field:SerializedName("driver_id")
//	val driverId: Int,
//
//	@field:SerializedName("notes")
//	val notes: String,
//
//	@field:SerializedName("action_type")
//	val actionType: String,
//
//	@field:SerializedName("created_at")
//	val createdAt: String,
//
//	@field:SerializedName("id")
//	val id: Int
//)
