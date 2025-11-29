package com.example.nebeng.feature_passenger_ride_booking.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllPassengerRideBookingUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Result<List<PassengerRideBooking>>> {
        return repository.readAll(token)
    }
}