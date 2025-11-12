package com.example.nebeng.feature_driver_withdrawal.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver_withdrawal.data.repository.DriverWithdrawalRepository
import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllDriverWithdrawalUseCase @Inject constructor(
    private val repository: DriverWithdrawalRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Result<List<DriverWithdrawal>>> {
        return repository.getAllDriverWithdrawals(token)
    }
}