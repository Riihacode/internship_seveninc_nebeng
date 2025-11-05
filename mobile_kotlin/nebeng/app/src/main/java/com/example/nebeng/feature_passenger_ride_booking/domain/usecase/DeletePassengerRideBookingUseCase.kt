package com.example.nebeng.feature_passenger_ride_booking.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeletePassengerRideBookingUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int
    ): Flow<Boolean> {
        return repository.deletePassengerRideBooking(token, id)
    }
}