package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class  CreatePassengerRideBookingUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
    suspend operator fun invoke(
        token: String,
        request: CreatePassengerRideBookingRequest
    ): Flow<Result<PassengerRideBookingSummary>> {
        return repository.createPassengerRideBookingSummary(token, request)
    }
}