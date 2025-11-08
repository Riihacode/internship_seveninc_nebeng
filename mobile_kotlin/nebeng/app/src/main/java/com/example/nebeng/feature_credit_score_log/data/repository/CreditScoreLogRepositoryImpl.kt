package com.example.nebeng.feature_credit_score_log.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.remote.api.CreditScoreLogApi
import com.example.nebeng.feature_credit_score_log.data.remote.model.mapper.CreditScoreLogMapper
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.CreateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.UpdateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Implementasi CreditScoreLogRepository.
 * Menggunakan Retrofit API dan mengubah response menjadi domain model dengan mapper.
 */
class CreditScoreLogRepositoryImpl @Inject constructor(
    private val api: CreditScoreLogApi
) : CreditScoreLogRepository {

    override suspend fun createCreditScoreLog(
        token: String,
        request: CreateCreditScoreLogRequest
    ): Flow<Result<CreditScoreLog>> = flow {
        emit(Result.Loading)
        try {
            val response = api.createCreditScoreLog("Bearer $token", request)
            val log = CreditScoreLogMapper.fromDataDto(response.data)
            emit(Result.Success(log))
        } catch (e: Exception) {
            Log.e("CreditScoreLogRepo", "❌ createCreditScoreLog: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error while creating credit score log"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllCreditScoreLogs(
        token: String
    ): Flow<Result<List<CreditScoreLog>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getAllCreditScoreLogs("Bearer $token")
            val list = response.data.map { CreditScoreLogMapper.fromDataItemDto(it) }
            emit(Result.Success(list))
        } catch (e: Exception) {
            Log.e("CreditScoreLogRepo", "❌ getAllCreditScoreLogs: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error while fetching all credit score logs"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCreditScoreLogById(
        token: String,
        id: Int
    ): Flow<Result<CreditScoreLog>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getCreditScoreLogById("Bearer $token", id)
            val log = CreditScoreLogMapper.fromDataItemReadById(response.data.first())
            emit(Result.Success(log))
        } catch (e: Exception) {
            Log.e("CreditScoreLogRepo", "❌ getCreditScoreLogById: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error while fetching credit score log by id"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCreditScoreLogsByDriverId(
        token: String,
        driverId: Int
    ): Flow<Result<List<CreditScoreLog>>> = flow {
        emit(Result.Loading)
        try {
            val response = api.getCreditScoreLogsByDriverId("Bearer $token", driverId)
            val list = response.data.map { CreditScoreLogMapper.fromDataItemDto(it) }
            emit(Result.Success(list))
        } catch (e: Exception) {
            Log.e("CreditScoreLogRepo", "❌ getCreditScoreLogsByDriverId: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error while fetching logs by driver id"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateCreditScoreLog(
        token: String,
        id: Int,
        request: UpdateCreditScoreLogRequest
    ): Flow<Result<CreditScoreLog>> = flow {
        emit(Result.Loading)
        try {
            val response = api.updateCreditScoreLog("Bearer $token", id, request)
            val log = CreditScoreLogMapper.fromDataDto(response.data)
            emit(Result.Success(log))
        } catch (e: Exception) {
            Log.e("CreditScoreLogRepo", "❌ updateCreditScoreLog: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error while updating credit score log"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteCreditScoreLogById(
        token: String,
        id: Int
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = api.deleteCreditScoreLogById("Bearer $token", id)
            emit(Result.Success(response.message))
        } catch (e: Exception) {
            Log.e("CreditScoreLogRepo", "❌ deleteCreditScoreLogById: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error while deleting credit score log"))
        }
    }.flowOn(Dispatchers.IO)
}