package com.example.nebeng.feature_a_authentication.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_auth.data.remote.model.request.CreateLoginRequest
import com.example.nebeng.feature_auth.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_auth.data.remote.model.request.CreateRegisterRequest
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    // ============================================================
    // 🔹 Login
    // ============================================================
    fun login(request: CreateLoginRequest) {
        viewModelScope.launch {
            useCases.login(request).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        authResult = Result.Loading,
                        errorMessage = null,
                        successMessage = null
                    )

                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        authResult = result,
                        successMessage = "Login berhasil",
                        errorMessage = null
                    )

                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        authResult = result,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Register
    // ============================================================
    fun register(request: CreateRegisterRequest) {
        viewModelScope.launch {
            useCases.register(request).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        authResult = Result.Loading,
                        errorMessage = null,
                        successMessage = null
                    )

                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        authResult = result,
                        successMessage = "Registrasi berhasil",
                        errorMessage = null
                    )

                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        authResult = result,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Logout
    // ============================================================
    fun logout(token: String) {
        viewModelScope.launch {
            useCases.logout(token).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        logoutResult = Result.Loading,
                        errorMessage = null,
                        successMessage = null
                    )

                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        logoutResult = result,
                        successMessage = result.data,
                        errorMessage = null
                    )

                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        logoutResult = result,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Reset pesan
    // ============================================================
    fun resetMessages() {
        _uiState.value = _uiState.value.copy(
            successMessage = null,
            errorMessage = null
        )
    }
}