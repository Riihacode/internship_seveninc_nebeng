package com.example.nebeng.feature_driver_withdrawal.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.CreateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.repository.DriverWithdrawalRepository
import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal
import kotlinx.coroutines.flow.Flow

class CreateDriverWithdrawalUseCase(
    private val repository: DriverWithdrawalRepository
) {
    suspend operator fun invoke(
        token: String,
        request: CreateDriverWithdrawalRequest
    ): Flow<Result<DriverWithdrawal>> {
        return repository.createDriverWithdrawal(token, request)
    }
}