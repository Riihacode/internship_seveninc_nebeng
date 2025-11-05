package com.example.nebeng.feature_passenger_ride_booking.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.UpdatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdatePassengerRideBookingUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int,
        request: UpdatePassengerRideBookingRequest
    ): Flow<PassengerRideBooking> {
        return repository.updatePassengerRideBooking(token, id, request)
    }
}