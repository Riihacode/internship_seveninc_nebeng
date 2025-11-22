package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import javax.inject.Inject

class GetAllPassengerRideUseCase @Inject constructor(
    private val repository: PassengerRideRepository
) {

}