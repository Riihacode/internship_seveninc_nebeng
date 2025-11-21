package com.example.nebeng.feature_a_history_order.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import javax.inject.Inject

class GetDriverHistoryOrderUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
){
}