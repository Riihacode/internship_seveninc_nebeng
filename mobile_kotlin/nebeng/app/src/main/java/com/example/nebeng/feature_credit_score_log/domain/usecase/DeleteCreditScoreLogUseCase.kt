package com.example.nebeng.feature_credit_score_log.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.repository.CreditScoreLogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCreditScoreLogUseCase @Inject constructor(
    private val repository: CreditScoreLogRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int
    ): Flow<Result<String>> {
        return repository.deleteCreditScoreLogById(token, id)
    }
}