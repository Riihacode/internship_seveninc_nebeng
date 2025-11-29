package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_passenger_pricing.data.repository.PassengerPricingRepository
import com.example.nebeng.feature_passenger_pricing.domain.model.PassengerPricingSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class GetByIdPassengerPricingUseCase @Inject constructor(
    private val repository: PassengerPricingRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<PassengerPricingSummary>> {
        return  repository.getByIdPassengerPricing(token, id)
    }
}