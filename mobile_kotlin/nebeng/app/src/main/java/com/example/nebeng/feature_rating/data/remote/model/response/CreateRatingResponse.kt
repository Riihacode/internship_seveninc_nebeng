package com.example.nebeng.feature_rating.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CreateRatingResponse(
	@field:SerializedName("data") val data: DataAverageRating
)

data class Rating(
	@field:SerializedName("feedback") val feedback: String,
	@field:SerializedName("driver_id") val driverId: Int,
	@field:SerializedName("rating") val rating: Int,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("customer_id") val customerId: Int
)

data class DriverAverageRating(
	@field:SerializedName("average_rating") val averageRating: Int,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("rating_count") val ratingCount: Int
)

data class DataAverageRating(
	@field:SerializedName("driver") val driver: DriverAverageRating,
	@field:SerializedName("rating") val rating: Rating
)
