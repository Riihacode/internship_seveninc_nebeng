package com.example.nebeng.feature_passenger_ride_booking.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.PatchPassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatchPassengerRideBookingUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int,
        request: PatchPassengerRideBookingRequest
    ): Flow<PassengerRideBooking> {
        return repository.patchPassengerRideBooking(token, id, request)
    }
}