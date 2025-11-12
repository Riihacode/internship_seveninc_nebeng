package com.example.nebeng.feature_vehicle.data.repository

import com.example.nebeng.feature_vehicle.data.remote.model.request.CreateVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.PatchVerifyFalseByIdVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.UpdateVehicleRequest
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result

interface VehicleRepository {
    suspend fun createVehicle(token: String, request: CreateVehicleRequest): Flow<Result<Vehicle>>
    suspend fun getAllVehicles(token: String): Flow<Result<List<Vehicle>>>
    suspend fun getVehicleById(token: String, id: Int): Flow<Result<Vehicle>>
    suspend fun getVehiclesByDriverId(token: String, driverId: Int): Flow<Result<List<Vehicle>>>
    suspend fun updateVehicleById(token: String, id: Int, request: UpdateVehicleRequest): Flow<Result<Vehicle>>
    suspend fun patchVerifyTrueVehicleById(token: String, id: Int): Flow<Result<Vehicle>>
    suspend fun patchVerifyFalseVehicleById(token: String, id: Int, request: PatchVerifyFalseByIdVehicleRequest): Flow<Result<Vehicle>>
    suspend fun deleteVehicleById(token: String, id: Int): Flow<Result<String>>
}