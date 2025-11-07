package com.example.nebeng.feature_goods_ride_booking.data.remote.api

import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.CreateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.UpdateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.response.*
import retrofit2.http.*

interface GoodsRideBookingApi {

    // 🔹 1. Create Goods Ride Booking
    @POST("api/goods-ride-bookings/")
    suspend fun createGoodsRideBooking(
        @Header("Authorization") token: String,
        @Body request: CreateGoodsRideBookingRequest
    ): CreateGoodsRideBookingResponse


    // 🔹 2. Get All Goods Ride Bookings
    @GET("api/goods-ride-bookings/")
    suspend fun getAllGoodsRideBookings(
        @Header("Authorization") token: String
    ): ReadAllGoodsRideBookingResponse


    // 🔹 3. Get Goods Ride Booking By Id
    @GET("api/goods-ride-bookings/{id}")
    suspend fun getGoodsRideBookingById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdGoodsRideBookingResponse


    // 🔹 4. Get Goods Ride Booking By Driver Id
    @GET("api/goods-ride-bookings/driver/{driver_id}")
    suspend fun getGoodsRideBookingByDriverId(
        @Header("Authorization") token: String,
        @Path("driver_id") driverId: Int
    ): ReadByDriverIdGoodsRideBookingResponse


    // 🔹 5. Get Goods Ride Booking By Status Enum
    @GET("api/goods-ride-bookings/status/{booking_statuses}")
    suspend fun getGoodsRideBookingByStatus(
        @Header("Authorization") token: String,
        @Path("booking_statuses") bookingStatus: String
    ): ReadByStatusGoodsRideBookingResponse


    // 🔹 6. Update Goods Ride Booking By Id
    @PUT("api/goods-ride-bookings/{id}")
    suspend fun updateGoodsRideBookingById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateGoodsRideBookingRequest
    ): UpdateGoodsRideBookingResponse


    // 🔹 7. Delete Goods Ride Booking By Id
    @DELETE("api/goods-ride-bookings/{id}")
    suspend fun deleteGoodsRideBookingById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteGoodsRideBookingResponse
}