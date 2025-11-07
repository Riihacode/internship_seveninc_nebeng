package com.example.nebeng.feature_rating.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.remote.model.request.CreateRatingRequest
import com.example.nebeng.feature_rating.data.repository.RatingRepository
import com.example.nebeng.feature_rating.domain.model.Rating
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateRatingUseCase @Inject constructor(
    private val repository: RatingRepository
) {
    suspend operator fun invoke(token: String, request: CreateRatingRequest): Flow<Result<Rating>> {
        return repository.createRating(token, request)
    }
}