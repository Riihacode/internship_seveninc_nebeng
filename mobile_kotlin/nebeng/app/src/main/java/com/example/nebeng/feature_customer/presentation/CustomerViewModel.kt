package com.example.nebeng.feature_customer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.remote.model.request.CreateCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.PatchAddCreditAmountCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.PatchDeductCreditAmountCustomerRequest
import com.example.nebeng.feature_customer.data.remote.model.request.UpdateCustomerRequest
import com.example.nebeng.feature_customer.domain.usecase.CustomerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val useCases: CustomerUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(CustomerUiState())
    val uiState: StateFlow<CustomerUiState> = _uiState

    // ================================================================
    // 🔹 READ ALL
    // ================================================================
    fun getAllCustomers(token: String) {
        viewModelScope.launch {
            useCases.readAll(token).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, customers = result.data, errorMessage = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 READ BY ID
    // ================================================================
    fun getCustomerById(token: String, id: Int) {
        viewModelScope.launch {
            useCases.readById(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, currentCustomer = result.data, errorMessage = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 READ BY USER ID
    // ================================================================
    fun getCustomerByUserId(token: String, userId: Int) {
        viewModelScope.launch {
            useCases.readByUserId(token, userId).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
//                        it.copy(isLoading = false, customers = result.data, errorMessage = null)
                        it.copy(isLoading = false, currentCustomer = result.data, errorMessage = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 CREATE
    // ================================================================
    fun createCustomer(token: String, request: CreateCustomerRequest) {
        viewModelScope.launch {
            useCases.create(token, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, currentCustomer = result.data)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 UPDATE
    // ================================================================
    fun updateCustomer(token: String, id: Int, request: UpdateCustomerRequest) {
        viewModelScope.launch {
            useCases.update(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, currentCustomer = result.data)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 PATCH VERIFY TRUE
    // ================================================================
    fun verifyCustomer(token: String, id: Int) {
        viewModelScope.launch {
            useCases.patchAutoVerifyTrue(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, currentCustomer = result.data)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 PATCH ADD CREDIT
    // ================================================================
    fun addCredit(token: String, id: Int, amount: Int) {
        viewModelScope.launch {
            val request = PatchAddCreditAmountCustomerRequest(amount)
            useCases.patchAddCreditAmount(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, currentCustomer = result.data)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 PATCH DEDUCT CREDIT
    // ================================================================
    fun deductCredit(token: String, id: Int, amount: Int) {
        viewModelScope.launch {
            val request = PatchDeductCreditAmountCustomerRequest(amount)
            useCases.patchDeductCreditAmount(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, currentCustomer = result.data)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 DELETE
    // ================================================================
    fun deleteCustomer(token: String, id: Int) {
        viewModelScope.launch {
            useCases.delete(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                    is Result.Success -> _uiState.update { it.copy(isLoading = false) }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }
}