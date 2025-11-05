package com.example.nebeng.feature_passenger_ride_booking.data.repository

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.PatchPassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.UpdatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import kotlinx.coroutines.flow.Flow

interface PassengerRideBookingRepository {
    suspend fun readAll(
        token: String
    ): Flow<List<PassengerRideBooking>>

    suspend fun readById(
        token: String,
        id: Int
    ): Flow<PassengerRideBooking>

    suspend fun readByPassengerRideId(
        token: String,
        passengerRideId: Int
    ): Flow<List<PassengerRideBooking>>

    suspend fun readByCustomerId(
        token: String,
        customerId: Int
    ): Flow<List<PassengerRideBooking>>

    suspend fun createPassengerRideBooking(
        token: String,
        request: CreatePassengerRideBookingRequest
    ): Flow<PassengerRideBooking>

    suspend fun updatePassengerRideBooking(
        token: String,
        id: Int,
        request: UpdatePassengerRideBookingRequest
    ): Flow<PassengerRideBooking>

    suspend fun patchPassengerRideBooking(
        token: String,
        id: Int,
        request: PatchPassengerRideBookingRequest
    ): Flow<PassengerRideBooking>

    suspend fun deletePassengerRideBooking(
        token: String,
        id: Int
    ): Flow<Boolean>
}