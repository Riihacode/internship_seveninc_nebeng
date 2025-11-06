package com.example.nebeng.feature_passenger_transaction.data.remote.api

import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.*
import com.example.nebeng.feature_passenger_transaction.data.remote.model.response.*
import retrofit2.http.*

interface PassengerTransactionApi {
    /* ============================================================
      🔹 1. Get All Passenger Transaction
      ============================================================ */
    @GET("api/passenger-transactions")
    suspend fun getAllPassengerTransactions(
        @Header("Authorization") token: String
    ): ReadAllPassengerTransactionResponse


    /* ============================================================
       🔹 2. Get Passenger Transaction By Id
       ============================================================ */
    @GET("api/passenger-transactions/{id}")
    suspend fun getPassengerTransactionById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdPassengerTransactionResponse


    /* ============================================================
       🔹 3. Get Passenger Transaction by PassengerRideBookingId
       ============================================================ */
    @GET("api/passenger-transactions/booking/{passenger_ride_booking_id}")
    suspend fun getByPassengerRideBookingId(
        @Header("Authorization") token: String,
        @Path("passenger_ride_booking_id") passengerRideBookingId: Int
    ): ReadByPassengerRideBookingIdPassengerTransactionResponse


    /* ============================================================
       🔹 4. Create Passenger Transaction
       ============================================================ */
    @POST("api/passenger-transactions")
    suspend fun createPassengerTransaction(
        @Header("Authorization") token: String,
        @Body request: CreatePassengerTransactionRequest
    ): CreatePassengerTransactionResponse


    /* ============================================================
       🔹 5. Update Passenger Transaction By Id
       ============================================================ */
    @PUT("api/passenger-transactions/{id}")
    suspend fun updatePassengerTransactionById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdatePassengerTransactionRequest
    ): UpdatePassengerTransactionResponse


    /* ============================================================
       🔹 6. Patch Status Passenger Transaction By Id
       ============================================================ */
    @PATCH("api/passenger-transactions/{id}/status")
    suspend fun patchPassengerTransactionStatusById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: PatchStatusByIdPassengerTransactionRequest
    ): PatchStatusByIdPassengerTransactionResponse


    /* ============================================================
       🔹 7. Delete Passenger Transaction By Id
       ============================================================ */
    @DELETE("api/passenger-transactions/{id}")
    suspend fun deletePassengerTransactionById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeletePassengerTransactionResponse
}