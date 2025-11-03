package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide

class ReadPassengerRideUseCase @Inject constructor(
    private val repository: PassengerRideRepository
) {
    suspend operator fun invoke(token: String): Result<List<PassengerRide>> {
        return repository.getAllPassengerRides(token)
    }
}