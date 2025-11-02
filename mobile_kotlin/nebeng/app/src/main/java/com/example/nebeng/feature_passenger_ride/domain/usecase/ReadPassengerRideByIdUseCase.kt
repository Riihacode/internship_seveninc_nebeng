package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class ReadPassengerRideByIdUseCase @Inject constructor(
    private val repository: PassengerRideRepository
) {
    suspend operator fun invoke(token: String, id: Int): Result<PassengerRide> {
        return repository.getPassengerRideById(token, id)
    }
}