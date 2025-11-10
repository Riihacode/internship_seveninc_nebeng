package com.example.nebeng.feature_driver_withdrawal.data.remote.api

import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.CreateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.UpdateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.response.*
import retrofit2.http.*

interface DriverWithdrawalApi {

    // 🔹 1. Get All Driver Withdrawals
    @GET("api/driver-withdrawals/")
    suspend fun getAllDriverWithdrawals(
        @Header("Authorization") token: String
    ): ReadAllDriverWithdrawalResponse


    // 🔹 2. Get Driver Withdrawal By Id
    @GET("api/driver-withdrawals/{id}")
    suspend fun getDriverWithdrawalById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdDriverWithdrawalResponse


    // 🔹 3. Get Driver Withdrawals By Driver Id
    @GET("api/driver-withdrawals/driver/{driver_id}")
    suspend fun getDriverWithdrawalsByDriverId(
        @Header("Authorization") token: String,
        @Path("driver_id") driverId: Int
    ): ReadByDriverIdDriverWithdrawalResponse


    // 🔹 4. Get Driver Withdrawals By Status
    @GET("api/driver-withdrawals/status/{driver_withdrawal_status}")
    suspend fun getDriverWithdrawalsByStatus(
        @Header("Authorization") token: String,
        @Path("driver_withdrawal_status") status: String
    ): ReadByStatusDriverWithdrawalResponse


    // 🔹 5. Create Driver Withdrawal
    @POST("api/driver-withdrawals/")
    suspend fun createDriverWithdrawal(
        @Header("Authorization") token: String,
        @Body request: CreateDriverWithdrawalRequest
    ): CreateDriverWithdrawalResponse


    // 🔹 6. Update Driver Withdrawal By Id
    @PUT("api/driver-withdrawals/{id}")
    suspend fun updateDriverWithdrawalById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateDriverWithdrawalRequest
    ): UpdateDriverWithdrawalResponse


    // 🔹 7. Delete Driver Withdrawal By Id
    @DELETE("api/driver-withdrawals/{id}")
    suspend fun deleteDriverWithdrawalById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteDriverWithdrawalResponse
}