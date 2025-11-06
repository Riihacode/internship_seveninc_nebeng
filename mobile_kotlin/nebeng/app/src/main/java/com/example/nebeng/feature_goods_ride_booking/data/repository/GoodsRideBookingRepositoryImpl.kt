package com.example.nebeng.feature_goods_ride_booking.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.feature_goods_ride_booking.data.remote.api.GoodsRideBookingApi
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.mapper.*
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.CreateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.UpdateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBookingFull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GoodsRideBookingRepositoryImpl @Inject constructor(
    private val api: GoodsRideBookingApi
): GoodsRideBookingRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createGoodsRideBooking(
        token: String,
        request: CreateGoodsRideBookingRequest
    ): Flow<Result<GoodsRideBooking>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createGoodsRideBooking("Bearer $token", request)
            val data = response.data.toDomain()
            emit(Result.Success(data))
            Log.d("GoodsRideBookingRepo", "✅ Created booking id=${data.id}")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while creating booking"))
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllGoodsRideBookings(
        token: String
    ): Flow<Result<List<GoodsRideBookingFull>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllGoodsRideBookings("Bearer $token")
            val data = response.data.map { it.toDomain() }
            emit(Result.Success(data))
            Log.d("GoodsRideBookingRepo", "✅ Loaded ${data.size} bookings (all)")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching all bookings"))
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getGoodsRideBookingById(
        token: String,
        id: Int
    ): Flow<Result<GoodsRideBookingFull>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getGoodsRideBookingById("Bearer $token", id)
            val data = response.data.toDomain()
            emit(Result.Success(data))
            Log.d("GoodsRideBookingRepo", "✅ Booking id=$id loaded successfully")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching booking by ID"))
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getGoodsRideBookingByDriverId(
        token: String,
        driverId: Int
    ): Flow<Result<List<GoodsRideBookingFull>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getGoodsRideBookingByDriverId("Bearer $token", driverId)
            val data = response.data.map { it.toDomain() }
            emit(Result.Success(data))
            Log.d("GoodsRideBookingRepo", "✅ Loaded ${data.size} bookings for driver=$driverId")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching bookings by driver ID"))
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getGoodsRideBookingByStatus(
        token: String,
        bookingStatus: BookingStatus
    ): Flow<Result<List<GoodsRideBookingFull>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getGoodsRideBookingByStatus("Bearer $token", bookingStatus.value)
            val data = response.data.map { it.toDomain() }
            emit(Result.Success(data))
            Log.d("GoodsRideBookingRepo", "✅ Loaded ${data.size} bookings with status=${bookingStatus.value}")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while fetching bookings by status"))
        }
    }.flowOn(Dispatchers.IO)


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateGoodsRideBookingById(
        token: String,
        id: Int,
        request: UpdateGoodsRideBookingRequest
    ): Flow<Result<GoodsRideBooking>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updateGoodsRideBookingById("Bearer $token", id, request)
            val data = response.data.toDomain()
            emit(Result.Success(data))
            Log.d("GoodsRideBookingRepo", "✅ Updated booking id=$id successfully")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while updating booking"))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun deleteGoodsRideBookingById(
        token: String,
        id: Int
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deleteGoodsRideBookingById("Bearer $token", id)
            emit(Result.Success(response.message))
            Log.d("GoodsRideBookingRepo", "✅ Deleted booking id=$id")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error while deleting booking"))
        }
    }.flowOn(Dispatchers.IO)
}