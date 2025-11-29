package com.example.nebeng.feature_passenger_ride_booking.data.repository

import com.example.nebeng.feature_passenger_ride_booking.data.remote.api.PassengerRideBookingApi
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.PatchPassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.UpdatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBookingFull
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.mapper.toSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PassengerRideBookingRepositoryImpl @Inject constructor(
    private val api: PassengerRideBookingApi
) : PassengerRideBookingRepository {

    override suspend fun readAll(token: String): Flow<Result<List<PassengerRideBooking>>> =
        flow {
            emit(Result.Loading)
            val response = api.getAllPassengerRideBookings("Bearer $token")
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto?.map { it.toDomain() } ?: emptyList()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun readById(token: String, id: Int): Flow<Result<PassengerRideBooking>> =
        flow {
            emit(Result.Loading)
            val response = api.getPassengerRideBookingById("Bearer $token", id)
            if (response.isSuccessful) {
                val dto = response.body()?.dataDto
                if (dto != null) emit(Result.Success(dto.toDomain()))
                else emit(Result.Error("Booking tidak ditemukan"))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun readByPassengerRideId(token: String, passengerRideId: Int): Flow<Result<List<PassengerRideBooking>>> =
        flow {
            emit(Result.Loading)
            val response = api.getPassengerRideBookingsByRideId("Bearer $token", passengerRideId)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto?.map { it.toDomain() } ?: emptyList()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun readByCustomerId(token: String, customerId: Int): Flow<Result<List<PassengerRideBooking>>> =
        flow {
            emit(Result.Loading)
            val response = api.getPassengerRideBookingsByCustomerId("Bearer $token", customerId)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto?.map { it.toDomain() } ?: emptyList()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun createPassengerRideBooking(token: String, request: CreatePassengerRideBookingRequest): Flow<Result<PassengerRideBooking>> =
        flow {
            emit(Result.Loading)
            val response = api.createPassengerRideBooking("Bearer $token", request)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto!!.toDomain()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun updatePassengerRideBooking(token: String, id: Int, request: UpdatePassengerRideBookingRequest): Flow<Result<PassengerRideBooking>> =
        flow {
            emit(Result.Loading)
            val response = api.updatePassengerRideBooking("Bearer $token", id, request)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto!!.toDomain()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun patchPassengerRideBooking(token: String, id: Int, request: PatchPassengerRideBookingRequest): Flow<Result<PassengerRideBooking>> =
        flow {
            emit(Result.Loading)
            val response = api.patchPassengerRideBookingStatus("Bearer $token", id, request)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto!!.toDomain()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun deletePassengerRideBooking(token: String, id: Int): Flow<Result<Boolean>> =
        flow {
            emit(Result.Loading)
            val response = api.deletePassengerRideBooking("Bearer $token", id)
            if (response.isSuccessful) emit(Result.Success(true))
            else emit(Result.Error("Server error: ${response.message()}"))
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun readHistorySummaryByCustomerId(token: String, customerId: Int): Flow<Result<List<PassengerRideBookingSummary>>> =
        flow {
            emit(Result.Loading)
            val response = api.getPassengerRideBookingsByCustomerId("Bearer $token", customerId)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto?.map { it.toSummary() } ?: emptyList()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)


    override suspend fun readByIdPassengerRideBookingSummary(token: String, id: Int): Flow<Result<PassengerRideBookingSummary>> =
        flow {
            emit(Result.Loading)
            val response = api.getPassengerRideBookingById("Bearer $token", id)
            if (response.isSuccessful) {
                val dto = response.body()?.dataDto
                if (dto != null) emit(Result.Success(dto.toSummary()))
                else emit(Result.Error("⚠ Data booking kosong"))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)

    override suspend fun createPassengerRideBookingSummary(token: String, request: CreatePassengerRideBookingRequest): Flow<Result<PassengerRideBookingSummary>> =
        flow {
            emit(Result.Loading)
            val response = api.createPassengerRideBooking("Bearer $token", request)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.dataDto!!.toSummary()))
            } else {
                emit(Result.Error("Server error: ${response.message()}"))
            }
        }.catch { e -> emit(Result.Error(e.message ?: "Unexpected error")) }
            .flowOn(Dispatchers.IO)
}