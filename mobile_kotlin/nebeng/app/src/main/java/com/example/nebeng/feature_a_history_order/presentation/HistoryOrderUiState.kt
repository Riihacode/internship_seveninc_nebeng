package com.example.nebeng.feature_a_history_order.presentation

import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_a_history_order.presentation.support_for_present.model.HistoryItemData
import com.example.nebeng.feature_terminal.domain.model.Terminal

/**
 * ============================================================
 * 🎛️ UI State: HistoryOrderUiState
 * ------------------------------------------------------------
 * Menyimpan semua state yang dibutuhkan oleh screen History Order.
 * Bisa digunakan untuk role "customer" maupun "driver".
 * ============================================================
 */
data class HistoryOrderUiState(
    val currentUser: Auth? = null,
    val historyItems: List<HistoryOrderItem> = emptyList(),
    val terminals: List<Terminal> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)