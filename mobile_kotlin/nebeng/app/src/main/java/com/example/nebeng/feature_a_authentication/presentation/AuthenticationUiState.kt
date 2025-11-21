package com.example.nebeng.feature_a_authentication.presentation

import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem

data class AuthenticationUiState(
    val isLoading: Boolean = false,
    val auth: AuthenticationItem? = null,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val users: List<AuthenticationItem>? = null,   // <— baru
)