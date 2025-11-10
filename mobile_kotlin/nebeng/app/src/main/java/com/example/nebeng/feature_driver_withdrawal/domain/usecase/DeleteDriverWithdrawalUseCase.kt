package com.example.nebeng.feature_driver_withdrawal.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver_withdrawal.data.repository.DriverWithdrawalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteDriverWithdrawalUseCase @Inject constructor(
    private val repository: DriverWithdrawalRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int
    ): Flow<Result<String>> {
        return repository.deleteDriverWithdrawalById(token, id)
    }
}