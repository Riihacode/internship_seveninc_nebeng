package com.example.nebeng.feature_credit_score_log.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.repository.CreditScoreLogRepository
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllCreditScoreLogUseCase @Inject constructor(
    private val repository: CreditScoreLogRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Result<List<CreditScoreLog>>> {
        return repository.getAllCreditScoreLogs(token)
    }
}