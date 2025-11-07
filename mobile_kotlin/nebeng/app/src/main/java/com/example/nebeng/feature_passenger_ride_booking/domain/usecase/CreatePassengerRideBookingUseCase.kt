package com.example.nebeng.feature_passenger_ride_booking.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class  CreatePassengerRideBookingUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
    suspend operator fun invoke(
        token: String,
        request: CreatePassengerRideBookingRequest
    ): Flow<PassengerRideBooking> {
        return repository.createPassengerRideBooking(token, request)
    }
}