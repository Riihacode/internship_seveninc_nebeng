package com.example.nebeng.feature_payment_method.data.remote.api

import com.example.nebeng.feature_payment_method.data.remote.model.request.CreatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.remote.model.request.UpdatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.remote.model.response.CreatePaymentMethodResponse
import com.example.nebeng.feature_payment_method.data.remote.model.response.DeletePaymentMethodResponse
import com.example.nebeng.feature_payment_method.data.remote.model.response.ReadAllPaymentMethodResponse
import com.example.nebeng.feature_payment_method.data.remote.model.response.ReadByIdPaymentMethodResponse
import com.example.nebeng.feature_payment_method.data.remote.model.response.UpdatePaymentMethodResponse
import retrofit2.Response
import retrofit2.http.*

interface PaymentMethodApi {
    // ================================================================
    // 🔹 1. GET ALL PAYMENT METHOD
    // ================================================================
    @GET("api/payment-methods/")
    suspend fun getAllPaymentMethods(
        @Header("Authorization") token: String
    ): Response<ReadAllPaymentMethodResponse>

    // ================================================================
    // 🔹 2. GET PAYMENT METHOD BY ID
    // ================================================================
    @GET("api/payment-methods/{id}")
    suspend fun getPaymentMethodById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ReadByIdPaymentMethodResponse>

    // ================================================================
    // 🔹 3. CREATE PAYMENT METHOD
    // ================================================================
    @POST("api/payment-methods/")
    suspend fun createPaymentMethod(
        @Header("Authorization") token: String,
        @Body request: CreatePaymentMethodRequest
    ): Response<CreatePaymentMethodResponse>

    // ================================================================
    // 🔹 4. UPDATE PAYMENT METHOD BY ID
    // ================================================================
    @PUT("api/payment-methods/{id}")
    suspend fun updatePaymentMethod(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdatePaymentMethodRequest
    ): Response<UpdatePaymentMethodResponse>

    // ================================================================
    // 🔹 5. DELETE PAYMENT METHOD BY ID
    // ================================================================
    @DELETE("api/payment-methods/{id}")
    suspend fun deletePaymentMethod(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<DeletePaymentMethodResponse>
}