package com.example.nebeng.feature_credit_score_log.data.remote.api

import com.example.nebeng.feature_credit_score_log.data.remote.model.request.CreateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.UpdateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.remote.model.response.*
import retrofit2.http.*

interface CreditScoreLogApi {
    // 🔹 Get All Credit Score Logs
    @GET("api/credit-score-logs/")
    suspend fun getAllCreditScoreLogs(
        @Header("Authorization") token: String
    ): ReadAllCreditScoreLogResponse


    // 🔹 Get Credit Score Log By Id
    @GET("api/credit-score-logs/{id}")
    suspend fun getCreditScoreLogById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdCreditScoreLogResponse


    // 🔹 Get Credit Score Log By Driver Id
    @GET("api/credit-score-logs/driver/{driver_id}")
    suspend fun getCreditScoreLogsByDriverId(
        @Header("Authorization") token: String,
        @Path("driver_id") driverId: Int
    ): ReadByDriverIdCreditScoreLogResponse


    // 🔹 Create Credit Score Log
    @POST("api/credit-score-logs/")
    suspend fun createCreditScoreLog(
        @Header("Authorization") token: String,
        @Body request: CreateCreditScoreLogRequest
    ): CreateCreditScoreLogResponse


    // 🔹 Update Credit Score Log By Id
    @PUT("api/credit-score-logs/{id}")
    suspend fun updateCreditScoreLog(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateCreditScoreLogRequest
    ): UpdateCreditScoreLogResponse


    // 🔹 Delete Credit Score Log By Id
    @DELETE("api/credit-score-logs/{id}")
    suspend fun deleteCreditScoreLogById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteCreditScoreLogResponse
}