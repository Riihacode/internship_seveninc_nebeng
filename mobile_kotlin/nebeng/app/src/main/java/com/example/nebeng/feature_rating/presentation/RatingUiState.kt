package com.example.nebeng.feature_rating.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.domain.model.Rating

data class RatingUiState(
    val ratings: Result<List<Rating>> = Result.Loading,
    val currentRating: Result<Rating?> = Result.Loading,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)