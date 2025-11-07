package com.example.nebeng.feature_rating.data.repository

import com.example.nebeng.feature_rating.data.remote.api.RatingApi
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_rating.data.remote.model.request.CreateRatingRequest
import com.example.nebeng.feature_rating.data.remote.model.request.UpdateRatingRequest
import com.example.nebeng.feature_rating.domain.model.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import android.util.Log

class RatingRepositoryImpl @Inject constructor(
    private val api: RatingApi
): RatingRepository {
    override suspend fun getAllRatings(token: String): Flow<Result<List<Rating>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllRatings("Bearer $token")
            if (response.isSuccessful) {
                val data = response.body()?.data?.map { it.toDomain() }.orEmpty()
                emit(Result.Success(data))
                Log.d("RatingRepo", "✅ getAllRatings: ${data.size} item(s)")
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch ratings"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on getAllRatings"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRatingById(token: String, id: Int): Flow<Result<Rating>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getRatingById("Bearer $token", id)
            if (response.isSuccessful) {
                val data = response.body()?.data?.toDomain()
                if (data != null) {
                    emit(Result.Success(data))
                    Log.d("RatingRepo", "✅ getRatingById: id=$id")
                } else emit(Result.Error("Rating not found"))
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch rating by id"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on getRatingById"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRatingsByDriverId(token: String, driverId: Int): Flow<Result<List<Rating>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getRatingsByDriverId("Bearer $token", driverId)
            if (response.isSuccessful) {
                val data = response.body()?.data?.map { it.toDomain() }.orEmpty()
                emit(Result.Success(data))
                Log.d("RatingRepo", "✅ getRatingsByDriverId: driver=$driverId count=${data.size}")
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch ratings by driver id"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on getRatingsByDriverId"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createRating(token: String, request: CreateRatingRequest): Flow<Result<Rating>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createRating("Bearer $token", request)
            if (response.isSuccessful) {
                val data = response.body()?.toDomain()
                if (data != null) {
                    emit(Result.Success(data))
                    Log.d("RatingRepo", "✅ createRating: driver=${data.driverId}")
                } else emit(Result.Error("Create rating response empty"))
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to create rating"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on createRating"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateRating(token: String, id: Int, request: UpdateRatingRequest): Flow<Result<Rating>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updateRating("Bearer $token", id, request)
            if (response.isSuccessful) {
                val data = response.body()?.toDomain()
                if (data != null) {
                    emit(Result.Success(data))
                    Log.d("RatingRepo", "✅ updateRating: id=$id rating=${data.rating}")
                } else emit(Result.Error("Update rating response empty"))
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to update rating"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on updateRating"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteRating(token: String, id: Int): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deleteRating("Bearer $token", id)
            if (response.isSuccessful) {
                emit(Result.Success(true))
                Log.d("RatingRepo", "✅ deleteRating: id=$id")
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to delete rating"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on deleteRating"))
        }
    }.flowOn(Dispatchers.IO)
}