package com.example.nebeng.feature_credit_score_log.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.repository.CreditScoreLogRepository
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadByIdCreditScoreLogUseCase @Inject constructor(
    private val repository: CreditScoreLogRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int
    ): Flow<Result<CreditScoreLog>> {
        return repository.getCreditScoreLogById(token, id)
    }
}