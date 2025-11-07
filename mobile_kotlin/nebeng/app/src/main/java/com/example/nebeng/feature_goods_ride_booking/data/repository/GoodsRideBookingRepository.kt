package com.example.nebeng.feature_goods_ride_booking.data.repository

import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.CreateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.UpdateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBookingFull

interface GoodsRideBookingRepository {
    suspend fun createGoodsRideBooking(
        token: String,
        request: CreateGoodsRideBookingRequest
    ): Flow<Result<GoodsRideBooking>>

    suspend fun getAllGoodsRideBookings(
        token: String
    ): Flow<Result<List<GoodsRideBookingFull>>>

    suspend fun getGoodsRideBookingById(
        token: String,
        id: Int
    ): Flow<Result<GoodsRideBookingFull>>

    suspend fun getGoodsRideBookingByDriverId(
        token: String,
        driverId: Int
    ): Flow<Result<List<GoodsRideBookingFull>>>

    suspend fun getGoodsRideBookingByStatus(
        token: String,
        bookingStatus: BookingStatus
    ): Flow<Result<List<GoodsRideBookingFull>>>

    suspend fun updateGoodsRideBookingById(
        token: String,
        id: Int,
        request: UpdateGoodsRideBookingRequest
    ): Flow<Result<GoodsRideBooking>>

    suspend fun deleteGoodsRideBookingById(
        token: String,
        id: Int
    ): Flow<Result<String>>
}