package com.example.nebeng.feature_passenger_ride_booking.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking

data class PassengerRideBookingUiState(
    val bookings: Result<List<PassengerRideBooking>> = Result.Loading,
    val selectedBooking: Result<PassengerRideBooking>? = null,
    val createResult: Result<PassengerRideBooking>? = null,
    val updateResult: Result<PassengerRideBooking>? = null,
    val patchResult: Result<PassengerRideBooking>? = null,
    val deleteResult: Result<String>? = null
)