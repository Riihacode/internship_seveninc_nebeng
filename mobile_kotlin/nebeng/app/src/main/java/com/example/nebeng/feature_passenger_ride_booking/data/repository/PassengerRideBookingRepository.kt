package com.example.nebeng.feature_passenger_ride_booking.data.repository

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.PatchPassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.UpdatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBookingFull
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result

interface PassengerRideBookingRepository {

    suspend fun readAll(token: String): Flow<Result<List<PassengerRideBooking>>>

    suspend fun readById(token: String, id: Int): Flow<Result<PassengerRideBooking>>

    suspend fun readByPassengerRideId(token: String, passengerRideId: Int): Flow<Result<List<PassengerRideBooking>>>

    suspend fun readByCustomerId(token: String, customerId: Int): Flow<Result<List<PassengerRideBooking>>>

    suspend fun createPassengerRideBooking(token: String, request: CreatePassengerRideBookingRequest): Flow<Result<PassengerRideBooking>>

    suspend fun updatePassengerRideBooking(token: String, id: Int, request: UpdatePassengerRideBookingRequest): Flow<Result<PassengerRideBooking>>

    suspend fun patchPassengerRideBooking(token: String, id: Int, request: PatchPassengerRideBookingRequest): Flow<Result<PassengerRideBooking>>

    suspend fun deletePassengerRideBooking(token: String, id: Int): Flow<Result<Boolean>>

    suspend fun readHistorySummaryByCustomerId(token: String, customerId: Int): Flow<Result<List<PassengerRideBookingSummary>>>

    suspend fun readByIdPassengerRideBookingSummary(token: String, id: Int): Flow<Result<PassengerRideBookingSummary>>

    suspend fun createPassengerRideBookingSummary(token: String, request: CreatePassengerRideBookingRequest): Flow<Result<PassengerRideBookingSummary>>
}
