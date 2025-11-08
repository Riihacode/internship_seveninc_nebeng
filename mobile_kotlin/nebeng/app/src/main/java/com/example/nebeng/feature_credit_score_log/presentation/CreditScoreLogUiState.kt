package com.example.nebeng.feature_credit_score_log.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog

/**
 * UI State utama untuk fitur Credit Score Log.
 * Digunakan untuk menampung data dan status loading/error/success.
 */
data class CreditScoreLogUiState(
    val isLoading: Boolean = false,
    val creditScoreLogs: Result<List<CreditScoreLog>>? = null,
    val currentLog: Result<CreditScoreLog>? = null,
    val successMessage: String? = null,
    val errorMessage: String? = null
)