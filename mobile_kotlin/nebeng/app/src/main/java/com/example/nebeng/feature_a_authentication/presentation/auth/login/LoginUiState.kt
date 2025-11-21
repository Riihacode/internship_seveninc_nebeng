package com.example.nebeng.feature_a_authentication.presentation.auth.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)
