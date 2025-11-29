package com.example.nebeng.feature_passenger_pricing.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_pricing.domain.model.PassengerPricingSummary
import kotlinx.coroutines.flow.Flow

interface PassengerPricingRepository {
    suspend fun getAllPassengerPricing(token: String): Flow<Result<List<PassengerPricingSummary>>>

    suspend fun getByIdPassengerPricing(token: String, id: Int): Flow<Result<PassengerPricingSummary>>

}