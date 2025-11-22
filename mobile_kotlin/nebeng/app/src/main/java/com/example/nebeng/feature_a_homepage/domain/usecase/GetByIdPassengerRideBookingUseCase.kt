package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import javax.inject.Inject

class GetByIdPassengerRideBookingUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
}