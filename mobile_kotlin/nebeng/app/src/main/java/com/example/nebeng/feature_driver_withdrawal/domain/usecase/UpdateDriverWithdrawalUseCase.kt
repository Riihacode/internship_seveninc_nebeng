package com.example.nebeng.feature_driver_withdrawal.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.UpdateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.repository.DriverWithdrawalRepository
import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateDriverWithdrawalUseCase @Inject constructor(
    private val repository: DriverWithdrawalRepository
) {
    suspend operator fun invoke(
        token: String,
        id: Int,
        request: UpdateDriverWithdrawalRequest
    ): Flow<Result<DriverWithdrawal>> {
        return repository.updateDriverWithdrawalById(token, id, request)
    }
}