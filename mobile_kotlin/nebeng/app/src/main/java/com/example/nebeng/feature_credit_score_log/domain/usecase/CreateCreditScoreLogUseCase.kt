package com.example.nebeng.feature_credit_score_log.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.CreateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.repository.CreditScoreLogRepository
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateCreditScoreLogUseCase @Inject constructor(
    private val repository: CreditScoreLogRepository
) {
    suspend operator fun invoke(
        token: String,
        request: CreateCreditScoreLogRequest
    ): Flow<Result<CreditScoreLog>> {
        return repository.createCreditScoreLog(token, request)
    }
}