package com.example.nebeng.feature_driver_withdrawal.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver_withdrawal.data.remote.api.DriverWithdrawalApi
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.mapper.DriverWithdrawalMapper
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.CreateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.UpdateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DriverWithdrawalRepositoryImpl @Inject constructor(
    private val api: DriverWithdrawalApi
) : DriverWithdrawalRepository {

    override suspend fun createDriverWithdrawal(
        token: String,
        request: CreateDriverWithdrawalRequest
    ): Flow<Result<DriverWithdrawal>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createDriverWithdrawal("Bearer $token", request)
            val mapped = DriverWithdrawalMapper.fromDataDto(response.data)
            emit(Result.Success(mapped))
            Log.d("DriverWithdrawalRepo", "✅ Created: ${mapped.id}")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to create driver withdrawal"))
            Log.e("DriverWithdrawalRepo", "❌ Error creating driver withdrawal", e)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllDriverWithdrawals(
        token: String
    ): Flow<Result<List<DriverWithdrawal>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllDriverWithdrawals("Bearer $token")
            val mappedList = DriverWithdrawalMapper.fromDataDtoList(response.data)
            emit(Result.Success(mappedList))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to fetch all driver withdrawals"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDriverWithdrawalById(
        token: String,
        id: Int
    ): Flow<Result<DriverWithdrawal>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getDriverWithdrawalById("Bearer $token", id)
            val mapped = DriverWithdrawalMapper.fromDataDto(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to fetch driver withdrawal by ID"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDriverWithdrawalsByDriverId(
        token: String,
        driverId: Int
    ): Flow<Result<List<DriverWithdrawal>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getDriverWithdrawalsByDriverId("Bearer $token", driverId)
            val mappedList = DriverWithdrawalMapper.fromDataDtoList(response.data)
            emit(Result.Success(mappedList))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to fetch withdrawals by driver ID"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDriverWithdrawalsByStatus(
        token: String,
        status: String
    ): Flow<Result<List<DriverWithdrawal>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getDriverWithdrawalsByStatus("Bearer $token", status)
            val mappedList = DriverWithdrawalMapper.fromDataDtoList(response.data)
            emit(Result.Success(mappedList))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to fetch withdrawals by status"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateDriverWithdrawalById(
        token: String,
        id: Int,
        request: UpdateDriverWithdrawalRequest
    ): Flow<Result<DriverWithdrawal>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updateDriverWithdrawalById("Bearer $token", id, request)
            val mapped = DriverWithdrawalMapper.fromDataUpdate(response.data)
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to update driver withdrawal"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteDriverWithdrawalById(
        token: String,
        id: Int
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deleteDriverWithdrawalById("Bearer $token", id)
            emit(Result.Success(response.message))
            Log.d("DriverWithdrawalRepo", "✅ Deleted: $id")
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Failed to delete driver withdrawal"))
        }
    }.flowOn(Dispatchers.IO)
}