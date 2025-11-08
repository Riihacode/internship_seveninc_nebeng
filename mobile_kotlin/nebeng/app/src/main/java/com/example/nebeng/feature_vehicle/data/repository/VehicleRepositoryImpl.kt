package com.example.nebeng.feature_vehicle.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.remote.api.VehicleApi
import com.example.nebeng.feature_vehicle.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_vehicle.data.remote.model.request.*
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val api: VehicleApi
): VehicleRepository {
    override suspend fun createVehicle(token: String, request: CreateVehicleRequest): Flow<Result<Vehicle>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.createVehicle("Bearer $token", request)
                emit(Result.Success(response.data.toDomain()))
                Log.d("VehicleRepo", "✅ Created vehicle id=${response.data.id}")
            } catch (e: Exception) {
                Log.e("VehicleRepo", "❌ Create failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while creating vehicle"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getAllVehicles(token: String): Flow<Result<List<Vehicle>>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.getAllVehicles("Bearer $token")
                emit(Result.Success(response.data.map { it.toDomain() }))
            } catch (e: Exception) {
                Log.e("VehicleRepo", "❌ GetAll failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while fetching vehicles"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getVehicleById(token: String, id: Int): Flow<Result<Vehicle>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.getVehicleById("Bearer $token", id)
                emit(Result.Success(response.data.toDomain()))
            } catch (e: Exception) {
                Log.e("VehicleRepo", "❌ GetById failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while fetching vehicle by id"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getVehiclesByDriverId(token: String, driverId: Int): Flow<Result<List<Vehicle>>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.getVehiclesByDriverId("Bearer $token", driverId)
                emit(Result.Success(response.data.map { it.toDomain() }))
            } catch (e: Exception) {
                Log.e("VehicleRepo", "❌ GetByDriverId failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while fetching vehicles by driver id"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun updateVehicleById(token: String, id: Int, request: UpdateVehicleRequest): Flow<Result<Vehicle>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.updateVehicleById("Bearer $token", id, request)
                emit(Result.Success(response.data.toDomain()))
                Log.d("VehicleRepo", "✅ Updated vehicle id=$id")
            } catch (e: Exception) {
                Log.e("VehicleRepo", "❌ Update failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while updating vehicle"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun patchVerifyTrueVehicleById(token: String, id: Int): Flow<Result<Vehicle>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.patchVerifyTrueVehicleById("Bearer $token", id)
                emit(Result.Success(response.data.toDomain()))
                Log.d("VehicleRepo", "✅ Vehicle verified id=$id")
            } catch (e: Exception) {
                Log.e("VehicleRepo", "❌ VerifyTrue failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while verifying vehicle"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun patchVerifyFalseVehicleById(
        token: String,
        id: Int,
        request: PatchVerifyFalseByIdVehicleRequest
    ): Flow<Result<Vehicle>> = flow {
        emit(Result.Loading)
        try {
            val response = api.patchVerifyFalseVehicleById("Bearer $token", id, request)
            emit(Result.Success(response.data.toDomain()))
            Log.d("VehicleRepo", "🚫 Vehicle rejected id=$id, reason=${request.reason}")
        } catch (e: Exception) {
            Log.e("VehicleRepo", "❌ VerifyFalse failed: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error while rejecting vehicle"))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun deleteVehicleById(token: String, id: Int): Flow<Result<String>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.deleteVehicleById("Bearer $token", id)
                emit(Result.Success(response.message))
                Log.d("VehicleRepo", "🗑️ Deleted vehicle id=$id")
            } catch (e: Exception) {
                Log.e("VehicleRepo", "❌ Delete failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while deleting vehicle"))
            }
        }.flowOn(Dispatchers.IO)
}