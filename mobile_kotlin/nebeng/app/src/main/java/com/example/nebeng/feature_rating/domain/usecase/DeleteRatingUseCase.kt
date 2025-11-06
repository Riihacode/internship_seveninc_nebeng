package com.example.nebeng.feature_rating.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.repository.RatingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteRatingUseCase @Inject constructor(
    private val repository: RatingRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Boolean>> {
        return repository.deleteRating(token, id)
    }
}