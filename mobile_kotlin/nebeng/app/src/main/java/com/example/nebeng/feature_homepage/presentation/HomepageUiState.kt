package com.example.nebeng.feature_homepage.presentation

import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.feature_history_order.presentation.support_for_present.model.HistoryItemData

data class HomepageUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentUser: Auth? = null,
    val points: Int = 0,
    val historyItems: List<HistoryItemData> = emptyList() // ✅ tambahkan ini
)