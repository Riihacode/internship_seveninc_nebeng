package com.example.nebeng.feature_a_authentication.presentation.auth

import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.core.common.Result

data class AuthUiState(
    val isLoading: Boolean = false,
    val authResult: Result<Auth>? = null,
    val logoutResult: Result<String>? = null,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
