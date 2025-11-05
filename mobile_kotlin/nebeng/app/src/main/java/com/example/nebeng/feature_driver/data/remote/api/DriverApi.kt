package com.example.nebeng.feature_driver.data.remote.api

import com.example.nebeng.feature_driver.data.remote.model.request.CreateDriverRequest
import com.example.nebeng.feature_driver.data.remote.model.request.UpdateDriverRequest
import com.example.nebeng.feature_driver.data.remote.model.response.CreateDriverResponse
import com.example.nebeng.feature_driver.data.remote.model.response.DeleteDriverResponse
import com.example.nebeng.feature_driver.data.remote.model.response.ReadAllDriverResponse
import com.example.nebeng.feature_driver.data.remote.model.response.ReadByIdDriverResponse
import com.example.nebeng.feature_driver.data.remote.model.response.UpdateDriverResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DriverApi {
    // ============================================================
    // 🔹 READ ALL DRIVER
    // GET /api/drivers/
    // ============================================================
    @GET("api/drivers/")
    suspend fun getAllDrivers(
        @Header("Authorization") token: String
    ): Response<ReadAllDriverResponse>

    // ============================================================
    // 🔹 READ DRIVER BY ID
    // GET /api/drivers/{id}
    // ============================================================
    @GET("api/drivers/{id}")
    suspend fun getDriverById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ReadByIdDriverResponse>

    // ============================================================
    // 🔹 CREATE DRIVER
    // POST /api/drivers/
    // ============================================================
    @POST("api/drivers/")
    suspend fun createDriver(
        @Header("Authorization") token: String,
        @Body request: CreateDriverRequest
    ): Response<CreateDriverResponse>

    // ============================================================
    // 🔹 UPDATE DRIVER BY ID
    // PUT /api/drivers/{id}
    // ============================================================
    @PUT("api/drivers/{id}")
    suspend fun updateDriver(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateDriverRequest
    ): Response<UpdateDriverResponse>

    // ============================================================
    // 🔹 DELETE DRIVER BY ID
    // DELETE /api/drivers/{id}
    // ============================================================
    @DELETE("api/drivers/{id}")
    suspend fun deleteDriver(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<DeleteDriverResponse>
}