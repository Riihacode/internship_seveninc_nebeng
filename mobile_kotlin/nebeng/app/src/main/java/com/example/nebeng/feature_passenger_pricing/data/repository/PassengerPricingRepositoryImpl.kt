package com.example.nebeng.feature_passenger_pricing.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_pricing.data.remote.api.PassengerPricingApi
import com.example.nebeng.feature_passenger_pricing.data.remote.model.mapper.toSummary
import com.example.nebeng.feature_passenger_pricing.domain.model.PassengerPricingSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.collections.orEmpty

class PassengerPricingRepositoryImpl @Inject constructor(
    private val api: PassengerPricingApi
): PassengerPricingRepository {
    override suspend fun getAllPassengerPricing(token: String): Flow<Result<List<PassengerPricingSummary>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllPassengerPricing("Bearer $token")
            if (response.isSuccessful) {
                val data = response.body()?.data?.map { it.toSummary() }.orEmpty()
                emit(Result.Success(data))
                Log.d("PassengerPricingRepo", "✅ getAllPassengerPricing: ${data.size} item(s)")
            } else {
                emit(Result.Error(response.errorBody()?.string() ?: "Failed to fetch ratings"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error on getAllRatings"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getByIdPassengerPricing(token: String, id: Int): Flow<Result<PassengerPricingSummary>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getPassengerPricingById("Bearer $token", id)
            if (response.isSuccessful) {
                val data = response.body()?.data?.toSummary()
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
}