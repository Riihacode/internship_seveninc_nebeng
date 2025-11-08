package com.example.nebeng.feature_vehicle.data.remote.api

import com.example.nebeng.feature_vehicle.data.remote.model.request.CreateVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.PatchVerifyFalseByIdVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.UpdateVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.response.*
import retrofit2.http.*

interface VehicleApi {
    // 🔹 1. Get All Vehicle
    @GET("api/vehicles/")
    suspend fun getAllVehicles(
        @Header("Authorization") token: String
    ): ReadAllVehicleResponse


    // 🔹 2. Get Vehicle By Id
    @GET("api/vehicles/{id}")
    suspend fun getVehicleById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdVehicleResponse


    // 🔹 3. Get Vehicle By Driver Id
    @GET("api/vehicles/driver/{driver_id}")
    suspend fun getVehiclesByDriverId(
        @Header("Authorization") token: String,
        @Path("driver_id") driverId: Int
    ): ReadByDriverIdVehicleResponse


    // 🔹 4. Create Vehicle
    @POST("api/vehicles/")
    suspend fun createVehicle(
        @Header("Authorization") token: String,
        @Body request: CreateVehicleRequest
    ): CreateVehicleResponse


    // 🔹 5. Update Vehicle By Id
    @PUT("api/vehicles/{id}")
    suspend fun updateVehicleById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateVehicleRequest
    ): UpdateVehicleResponse


    // 🔹 6. Patch Verify True Vehicle By Id
    @PATCH("api/vehicles/{id}/verify")
    suspend fun patchVerifyTrueVehicleById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): PatchVerifyTrueByIdVehicleResponse


    // 🔹 7. Patch Verify False Vehicle By Id
    @PATCH("api/vehicles/{id}/reject")
    suspend fun patchVerifyFalseVehicleById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: PatchVerifyFalseByIdVehicleRequest
    ): PatchVerifyFalseByIdVehicleResponse


    // 🔹 8. Delete Vehicle By Id
    @DELETE("api/vehicles/{id}")
    suspend fun deleteVehicleById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteVehicleResponse
}