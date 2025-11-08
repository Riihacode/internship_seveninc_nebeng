package com.example.nebeng.feature_credit_score_log.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.CreateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.UpdateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog
import kotlinx.coroutines.flow.Flow

interface CreditScoreLogRepository {
    suspend fun createCreditScoreLog(
        token: String,
        request: CreateCreditScoreLogRequest
    ): Flow<Result<CreditScoreLog>>

    suspend fun getAllCreditScoreLogs(
        token: String
    ): Flow<Result<List<CreditScoreLog>>>

    suspend fun getCreditScoreLogById(
        token: String,
        id: Int
    ): Flow<Result<CreditScoreLog>>

    suspend fun getCreditScoreLogsByDriverId(
        token: String,
        driverId: Int
    ): Flow<Result<List<CreditScoreLog>>>

    suspend fun updateCreditScoreLog(
        token: String,
        id: Int,
        request: UpdateCreditScoreLogRequest
    ): Flow<Result<CreditScoreLog>>

    suspend fun deleteCreditScoreLogById(
        token: String,
        id: Int
    ): Flow<Result<String>>
}