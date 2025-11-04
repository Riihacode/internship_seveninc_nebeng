package com.example.nebeng.feature_rating.domain.model

data class Rating(
    val id: Int,
    val ratingValue: Double,
    val comment: String,
    val createdAt: String
)
