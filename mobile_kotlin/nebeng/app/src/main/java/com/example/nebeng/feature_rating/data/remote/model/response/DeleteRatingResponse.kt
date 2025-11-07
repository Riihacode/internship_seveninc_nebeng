package com.example.nebeng.feature_rating.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteRatingResponse(
	@field:SerializedName("message") val message: String
)
