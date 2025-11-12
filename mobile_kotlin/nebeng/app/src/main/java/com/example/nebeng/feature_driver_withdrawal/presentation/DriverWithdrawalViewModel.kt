package com.example.nebeng.feature_driver_withdrawal.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.CreateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.UpdateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.DriverWithdrawalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverWithdrawalViewModel @Inject constructor(
    private val useCases: DriverWithdrawalUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(DriverWithdrawalUiState())
    val uiState: StateFlow<DriverWithdrawalUiState> = _uiState

    /* ============================================================
       🔹 GET ALL
       ============================================================ */
    fun getAllDriverWithdrawals(token: String) {
        viewModelScope.launch {
            useCases.readAll(token).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, withdrawals = result, errorMessage = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    /* ============================================================
       🔹 GET BY ID
       ============================================================ */
    fun getDriverWithdrawalById(token: String, id: Int) {
        viewModelScope.launch {
            useCases.readById(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, currentWithdrawal = result, errorMessage = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    /* ============================================================
       🔹 GET BY DRIVER ID
       ============================================================ */
    fun getDriverWithdrawalsByDriverId(token: String, driverId: Int) {
        viewModelScope.launch {
            useCases.readByDriverId(token, driverId).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, withdrawals = result)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    /* ============================================================
       🔹 GET BY STATUS
       ============================================================ */
    fun getDriverWithdrawalsByStatus(token: String, status: String) {
        viewModelScope.launch {
            useCases.readByStatus(token, status).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, withdrawals = result)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    /* ============================================================
       🔹 CREATE
       ============================================================ */
    fun createDriverWithdrawal(token: String, request: CreateDriverWithdrawalRequest) {
        viewModelScope.launch {
            useCases.create(token, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Result.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            currentWithdrawal = result,
                            successMessage = "Penarikan berhasil diajukan."
                        )
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    /* ============================================================
       🔹 UPDATE
       ============================================================ */
    fun updateDriverWithdrawal(token: String, id: Int, request: UpdateDriverWithdrawalRequest) {
        viewModelScope.launch {
            useCases.update(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Result.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            currentWithdrawal = result,
                            successMessage = "Data penarikan berhasil diperbarui."
                        )
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    /* ============================================================
       🔹 DELETE
       ============================================================ */
    fun deleteDriverWithdrawal(token: String, id: Int) {
        viewModelScope.launch {
            useCases.delete(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Result.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            successMessage = "Data penarikan berhasil dihapus."
                        )
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    /* ============================================================
       🔹 RESET STATE
       ============================================================ */
    fun resetMessages() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}
