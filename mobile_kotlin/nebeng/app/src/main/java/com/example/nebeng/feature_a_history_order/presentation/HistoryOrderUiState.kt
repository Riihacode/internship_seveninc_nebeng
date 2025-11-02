package com.example.nebeng.feature_a_history_order.presentation

import com.example.nebeng.feature_a_auth.domain.model.Auth
import com.example.nebeng.feature_a_history_order.presentation.support_for_present.model.HistoryItemData

data class HistoryOrderUiState(
    val currentUser: Auth? = null,
    val historyItems: List<HistoryItemData> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)