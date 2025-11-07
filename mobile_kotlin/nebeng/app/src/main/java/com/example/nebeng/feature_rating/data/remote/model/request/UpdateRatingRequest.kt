package com.example.nebeng.feature_rating.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateRatingRequest(
	@field:SerializedName("feedback") val feedback: String,
	@field:SerializedName("rating") val rating: Int
)
