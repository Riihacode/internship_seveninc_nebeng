package com.example.nebeng.feature_payment_method.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.remote.model.request.CreatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.remote.model.request.UpdatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.domain.usecase.PaymentMethodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    private val useCases: PaymentMethodUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentMethodUiState())
    val uiState: StateFlow<PaymentMethodUiState> = _uiState

    // ================================================================
    // 🔹 READ ALL
    // ================================================================
    fun getAllPaymentMethods(token: String) {
        viewModelScope.launch {
            useCases.readAll(token).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, paymentMethods = result.data, error = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 READ BY ID
    // ================================================================
    fun getPaymentMethodById(token: String, id: Int) {
        viewModelScope.launch {
            useCases.readById(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, selectedPaymentMethod = result.data, error = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 CREATE
    // ================================================================
    fun createPaymentMethod(token: String, request: CreatePaymentMethodRequest) {
        viewModelScope.launch {
            useCases.create(token, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            successMessage = "Payment method created successfully"
                        )
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 UPDATE
    // ================================================================
    fun updatePaymentMethod(token: String, id: Int, request: UpdatePaymentMethodRequest) {
        viewModelScope.launch {
            useCases.update(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            selectedPaymentMethod = result.data,
                            successMessage = "Payment method updated successfully"
                        )
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 DELETE
    // ================================================================
    fun deletePaymentMethod(token: String, id: Int) {
        viewModelScope.launch {
            useCases.delete(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            successMessage = "Payment method deleted successfully"
                        )
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}
