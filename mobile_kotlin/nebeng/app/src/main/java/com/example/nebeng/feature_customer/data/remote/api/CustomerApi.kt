package com.example.nebeng.feature_customer.data.remote.api

import com.example.nebeng.feature_customer.data.remote.model.request.CreateCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.PatchAddCreditAmountCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.PatchDeductCreditAmountCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.UpdateCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.response.CreateCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.DeleteCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.PatchAddCreditAmountCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.PatchAutoVerifyTrueCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.PatchDeductCreditAmountCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.UpdateCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.ReadAllCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.ReadByIdCustomerResponse
import com.example.nebeng.feature_customer.data.remote.model.response.ReadByUserIdCustomerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


/**
 * ============================================================
 * 🧩 CustomerApi
 * Retrofit interface untuk berinteraksi dengan endpoint Customer.
 *
 * Semua endpoint memerlukan Authorization Bearer Token.
 * ============================================================
 */
interface CustomerApi {
    /**
     * 🟢 [1] Get All Customers
     * GET /api/customers/
     */
    @GET("api/customers/")
    suspend fun getAllCustomers(
        @Header("Authorization") token: String
    ): Response<ReadAllCustomerResponse>

    /**
     * 🟢 [2] Get Customer By ID
     * GET /api/customers/{id}
     */
    @GET("api/customers/{id}")
    suspend fun getCustomerById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ReadByIdCustomerResponse>

    /**
     * 🟢 [3] Get Customer By User ID (Global User ID)
     * GET /api/customers/user/{user_id}
     */
    @GET("api/customers/user/{user_id}")
    suspend fun getCustomerByUserId(
        @Header("Authorization") token: String,
        @Path("user_id") userId: Int
    ): Response<ReadByUserIdCustomerResponse>

    /**
     * 🟢 [4] Create Customer
     * POST /api/customers/
     */
    @POST("api/customers/")
    suspend fun createCustomer(
        @Header("Authorization") token: String,
        @Body request: CreateCustomerRequest
    ): Response<CreateCustomerResponse>

    /**
     * 🟢 [5] Update Customer By ID
     * PUT /api/customers/{id}
     */
    @PUT("api/customers/{id}")
    suspend fun updateCustomer(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateCustomerRequest
    ): Response<UpdateCustomerResponse>

    /**
     * 🟡 [6] Patch: Verify Customer True by ID
     * PATCH /api/customers/{id}/verify
     */
    @PATCH("api/customers/{id}/verify")
    suspend fun patchVerifyTrue(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<PatchAutoVerifyTrueCustomerResponse>

    /**
     * 🟡 [7] Patch: Add Credit Amount
     * PATCH /api/customers/{id}/add-credit/
     */
    @PATCH("api/customers/{id}/add-credit/")
    suspend fun patchAddCredit(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: PatchAddCreditAmountCustomerRequest
    ): Response<PatchAddCreditAmountCustomerResponse>

    /**
     * 🟡 [8] Patch: Deduct Credit Amount
     * PATCH /api/customers/{id}/deduct-credit/
     */
    @PATCH("api/customers/{id}/deduct-credit/")
    suspend fun patchDeductCredit(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: PatchDeductCreditAmountCustomerRequest
    ): Response<PatchDeductCreditAmountCustomerResponse>

    /**
     * 🔴 [9] Delete Customer by ID
     * DELETE /api/customers/{id}
     */
    @DELETE("api/customers/{id}")
    suspend fun deleteCustomer(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<DeleteCustomerResponse>
}