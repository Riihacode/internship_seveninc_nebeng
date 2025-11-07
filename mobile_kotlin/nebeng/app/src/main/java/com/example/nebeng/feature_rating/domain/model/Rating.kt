package com.example.nebeng.feature_rating.domain.model

data class Rating(
    val id: Int,
    val driverId: Int,
    val customerId: Int,
    val rating: Int,
    val feedback: String?,
    val createdAt: String
)
