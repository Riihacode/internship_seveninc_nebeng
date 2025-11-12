package com.example.nebeng.feature_driver_withdrawal.presentation

import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal
import com.example.nebeng.core.common.Result

data class DriverWithdrawalUiState(
    val withdrawals: Result<List<DriverWithdrawal>> = Result.Success(emptyList()),
    val currentWithdrawal: Result<DriverWithdrawal?> = Result.Success(null),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)