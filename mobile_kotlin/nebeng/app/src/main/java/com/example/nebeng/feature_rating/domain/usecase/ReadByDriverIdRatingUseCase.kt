package com.example.nebeng.feature_rating.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.repository.RatingRepository
import com.example.nebeng.feature_rating.domain.model.Rating
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadByDriverIdRatingUseCase @Inject constructor(
    private val repository: RatingRepository
) {
    suspend operator fun invoke(token: String, driverId: Int): Flow<Result<List<Rating>>> {
        return repository.getRatingsByDriverId(token, driverId)
    }
}