package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRideSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPassengerRideUseCase @Inject constructor(
    private val repository: PassengerRideRepository
) {
    suspend operator fun invoke(token: String): Result<List<PassengerRideSummary>> {
        return repository.getAllPassengerRidesSummary(token)
    }
}