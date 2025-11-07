package com.example.nebeng.feature_passenger_transaction.presentation

import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import com.example.nebeng.core.common.Result

data class PassengerTransactionUiState(
    val transactions: Result<List<PassengerTransaction>> = Result.Loading,
    val currentTransaction: Result<PassengerTransaction>? = null,
    val deleteResult: Result<Boolean>? = null,
    val isLoading: Boolean = false,
    val message: String? = null
)
