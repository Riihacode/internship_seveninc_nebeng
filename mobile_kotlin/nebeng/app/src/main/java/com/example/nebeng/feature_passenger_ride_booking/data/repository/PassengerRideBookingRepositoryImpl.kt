package com.example.nebeng.feature_passenger_ride_booking.data.repository

import com.example.nebeng.feature_passenger_ride_booking.data.remote.api.PassengerRideBookingApi
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper.toFullDomain
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.PatchPassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.UpdatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBookingFull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PassengerRideBookingRepositoryImpl @Inject constructor(
    private val api: PassengerRideBookingApi
): PassengerRideBookingRepository {

    override suspend fun readAll(
        token: String
    ): Flow<List<PassengerRideBooking>> = flow {
        val response = api.getAllPassengerRideBookings("Bearer $token")
        if (response.isSuccessful) {
            emit(response.body()?.dataDto?.map { it.toDomain() } ?: emptyList())
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to fetch all bookings")
        }
    }

    override suspend fun readById(
        token: String,
        id: Int
    ): Flow<PassengerRideBooking> = flow {
        val response = api.getPassengerRideBookingById("Bearer $token", id)
        if (response.isSuccessful) {
            emit(response.body()?.dataDto?.toDomain()!!)
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to fetch booking by ID")
        }
    }

    override suspend fun readByPassengerRideId(
        token: String,
        passengerRideId: Int
    ): Flow<List<PassengerRideBooking>> = flow {
        val response = api.getPassengerRideBookingsByRideId("Bearer $token", passengerRideId)
        if (response.isSuccessful) {
            emit(response.body()?.dataDto?.map { it.toDomain() } ?: emptyList())
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to fetch by ride ID")
        }
    }

    override suspend fun readByCustomerId(
        token: String,
        customerId: Int
    ): Flow<List<PassengerRideBooking>> = flow {
        val response = api.getPassengerRideBookingsByCustomerId("Bearer $token", customerId)
        if (response.isSuccessful) {
            emit(response.body()?.dataDto?.map { it.toDomain() } ?: emptyList())
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to fetch by customer ID")
        }
    }

    override suspend fun createPassengerRideBooking(
        token: String,
        request: CreatePassengerRideBookingRequest
    ): Flow<PassengerRideBooking> = flow {
        val response = api.createPassengerRideBooking("Bearer $token", request)
        if (response.isSuccessful) {
            emit(response.body()?.dataDto?.toDomain()!!)
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to create booking")
        }
    }

    override suspend fun updatePassengerRideBooking(
        token: String,
        id: Int,
        request: UpdatePassengerRideBookingRequest
    ): Flow<PassengerRideBooking> = flow {
        val response = api.updatePassengerRideBooking("Bearer $token", id, request)
        if (response.isSuccessful) {
            emit(response.body()?.dataDto?.toDomain()!!)
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to update booking")
        }
    }

    override suspend fun patchPassengerRideBooking(
        token: String,
        id: Int,
        request: PatchPassengerRideBookingRequest
    ): Flow<PassengerRideBooking> = flow {
        val response = api.patchPassengerRideBookingStatus("Bearer $token", id, request)
        if (response.isSuccessful) {
            emit(response.body()?.dataDto?.toDomain()!!)
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to patch booking status")
        }
    }

    override suspend fun deletePassengerRideBooking(
        token: String,
        id: Int
    ): Flow<Boolean> = flow {
        val response = api.deletePassengerRideBooking("Bearer $token", id)
        emit(response.isSuccessful)
    }

    // 🔹 KHUSUS NESTED JSON (History)
    override suspend fun readAllFull(
        token: String
    ): Flow<List<PassengerRideBookingFull>> = flow {
        val response = api.getAllPassengerRideBookings("Bearer $token")
        if (response.isSuccessful) {
            val data = response.body()?.dataDto?.map { it.toFullDomain() } ?: emptyList()
            emit(data)
        } else {
            throw Exception(response.errorBody()?.string() ?: "Failed to fetch nested bookings")
        }
    }
}