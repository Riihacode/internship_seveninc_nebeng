package com.example.nebeng.feature_rating.data.repository

import com.example.nebeng.feature_rating.data.remote.model.request.CreateRatingRequest
import com.example.nebeng.feature_rating.data.remote.model.request.UpdateRatingRequest
import com.example.nebeng.feature_rating.domain.model.Rating
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result

interface RatingRepository {
    suspend fun getAllRatings(token: String): Flow<Result<List<Rating>>>

    suspend fun getRatingById(token: String, id: Int): Flow<Result<Rating>>

    suspend fun getRatingsByDriverId(token: String, driverId: Int): Flow<Result<List<Rating>>>

    suspend fun createRating(token: String, request: CreateRatingRequest): Flow<Result<Rating>>

    suspend fun updateRating(token: String, id: Int, request: UpdateRatingRequest): Flow<Result<Rating>>

    suspend fun deleteRating(token: String, id: Int): Flow<Result<Boolean>>
}