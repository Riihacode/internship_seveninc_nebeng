package com.example.nebeng.feature_credit_score_log.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.CreateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.UpdateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.domain.usecase.CreditScoreLogUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditScoreLogViewModel @Inject constructor(
    private val useCases: CreditScoreLogUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreditScoreLogUiState())
    val uiState: StateFlow<CreditScoreLogUiState> = _uiState

    // ============================================================
    // 🔹 Get All Logs
    // ============================================================
    fun getAllCreditScoreLogs(token: String) {
        viewModelScope.launch {
            useCases.readAll(token).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        creditScoreLogs = result,
                        errorMessage = null
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Get Log By ID
    // ============================================================
    fun getCreditScoreLogById(token: String, id: Int) {
        viewModelScope.launch {
            useCases.readById(token, id).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        currentLog = result,
                        errorMessage = null
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Get Logs By Driver ID
    // ============================================================
    fun getCreditScoreLogsByDriverId(token: String, driverId: Int) {
        viewModelScope.launch {
            useCases.readByDriverId(token, driverId).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        creditScoreLogs = result,
                        errorMessage = null
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Create Credit Score Log
    // ============================================================
//    fun createCreditScoreLog(token: String, request: CreateCreditScoreLogRequest) {
//        viewModelScope.launch {
//            useCases.create(token, request).collectLatest { result ->
//                when (result) {
//                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
//                    is Result.Success -> _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        currentLog = result,
//                        successMessage = "Credit score log berhasil dibuat",
//                        errorMessage = null
//                    )
//                    is Result.Error -> _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        errorMessage = result.message
//                    )
//                }
//            }
//        }
//    }
    fun createCreditScoreLog(token: String, request: CreateCreditScoreLogRequest) {
        viewModelScope.launch {
            useCases.create(token, request).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        currentLog = Result.Loading
                    )

                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        currentLog = result,
                        successMessage = "Credit score log berhasil dibuat",
                        errorMessage = null
                    )

                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        currentLog = result, // ✅ Tambahkan agar test cocok
                        errorMessage = result.message
                    )
                }
            }
        }
    }


    // ============================================================
    // 🔹 Update Credit Score Log
    // ============================================================
    fun updateCreditScoreLog(token: String, id: Int, request: UpdateCreditScoreLogRequest) {
        viewModelScope.launch {
            useCases.update(token, id, request).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        currentLog = result,
                        successMessage = "Credit score log berhasil diperbarui",
                        errorMessage = null
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Delete Credit Score Log
    // ============================================================
    fun deleteCreditScoreLog(token: String, id: Int) {
        viewModelScope.launch {
            useCases.delete(token, id).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Credit score log berhasil dihapus",
                        errorMessage = null
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    // ============================================================
    // 🔹 Reset Success / Error State
    // ============================================================
    fun resetMessages() {
        _uiState.value = _uiState.value.copy(
            successMessage = null,
            errorMessage = null
        )
    }
}