package com.example.nebeng.feature_homepage.presentation

import com.example.nebeng.feature_auth.domain.model.Auth

data class HomepageUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentUser: Auth? = null,
    val points: Int = 0
)