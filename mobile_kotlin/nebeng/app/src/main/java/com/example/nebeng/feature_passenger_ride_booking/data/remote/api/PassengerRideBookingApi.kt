package com.example.nebeng.feature_passenger_ride_booking.data.remote.api

import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.PatchPassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.UpdatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.CreatePassengerRideBookingResponse
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.DeletePassengerRideBookingResponse
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.PatchPassengerRideBookingResponse
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.ReadAllPassengerRideBookingResponse
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.ReadByCustomerIdPassengerRideBookingResponse
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.ReadByIdPassengerRideBookingResponse
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.ReadByPassengerRideIdPassengerRideBookingResponse
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.response.UpdatePassengerRideBookingResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PassengerRideBookingApi {
    // ====================================
    // 🔹 1. Get All Passenger Ride Bookings
    // ====================================
    @GET("api/passenger-ride-bookings")
    suspend fun getAllPassengerRideBookings(
        @Header("Authorization") token: String
    ): Response<ReadAllPassengerRideBookingResponse>


    // ====================================
    // 🔹 2. Get Passenger Ride Booking by ID
    // ====================================
    @GET("api/passenger-ride-bookings/{id}")
    suspend fun getPassengerRideBookingById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ReadByIdPassengerRideBookingResponse>


    // ====================================
    // 🔹 3. Get Passenger Ride Booking by Passenger Ride ID
    // ====================================
    @GET("api/passenger-ride-bookings/ride/{passenger_ride_id}")
    suspend fun getPassengerRideBookingsByRideId(
        @Header("Authorization") token: String,
        @Path("passenger_ride_id") rideId: Int
    ): Response<ReadByPassengerRideIdPassengerRideBookingResponse>


    // ====================================
    // 🔹 4. Get Passenger Ride Booking by Customer ID
    // ====================================
    @GET("api/passenger-ride-bookings/customer/{customer_id}")
    suspend fun getPassengerRideBookingsByCustomerId(
        @Header("Authorization") token: String,
        @Path("customer_id") customerId: Int
    ): Response<ReadByCustomerIdPassengerRideBookingResponse>


    // ====================================
    // 🔹 5. Create Passenger Ride Booking
    // ====================================
    @POST("api/passenger-ride-bookings")
    suspend fun createPassengerRideBooking(
        @Header("Authorization") token: String,
        @Body request: CreatePassengerRideBookingRequest
    ): Response<CreatePassengerRideBookingResponse>


    // ====================================
    // 🔹 6. Update Passenger Ride Booking (PUT)
    // ====================================
    @PUT("api/passenger-ride-bookings/{id}")
    suspend fun updatePassengerRideBooking(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdatePassengerRideBookingRequest
    ): Response<UpdatePassengerRideBookingResponse>


    // ====================================
    // 🔹 7. Patch Passenger Ride Booking status by ID
    // ====================================
    @PATCH("api/passenger-ride-bookings/{id}/status")
    suspend fun patchPassengerRideBookingStatus(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: PatchPassengerRideBookingRequest // { "status": "Completed" }
    ): Response<PatchPassengerRideBookingResponse>


    // ====================================
    // 🔹 8. Delete Passenger Ride Booking by ID
    // ====================================
    @DELETE("api/passenger-ride-bookings/{id}")
    suspend fun deletePassengerRideBooking(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<DeletePassengerRideBookingResponse>
}