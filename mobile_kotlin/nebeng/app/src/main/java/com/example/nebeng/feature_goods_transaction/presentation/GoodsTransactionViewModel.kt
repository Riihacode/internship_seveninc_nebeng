package com.example.nebeng.feature_goods_transaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.CreateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.domain.usecase.GoodsTransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.UpdateGoodsTransactionRequest

@HiltViewModel
class GoodsTransactionViewModel @Inject constructor(
    private val useCases: GoodsTransactionUseCases
): ViewModel() {
    private val _uiState = MutableStateFlow(GoodsTransactionUiState())
    val uiState: StateFlow<GoodsTransactionUiState> = _uiState

    // 🔹 CREATE
    fun createTransaction(token: String, request: CreateGoodsTransactionRequest) {
        viewModelScope.launch {
            useCases.create(token, request).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        createResult = result,
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

    // 🔹 READ ALL
    fun getAllTransactions(token: String) {
        viewModelScope.launch {
            useCases.readAll(token).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        transactions = result.data,
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

    // 🔹 READ BY ID
    fun getTransactionById(token: String, id: Int) {
        viewModelScope.launch {
            useCases.readById(token, id).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        selectedTransaction = result.data,
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

    // 🔹 UPDATE
    fun updateTransaction(token: String, id: Int, request: UpdateGoodsTransactionRequest) {
        viewModelScope.launch {
            useCases.update(token, id, request).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        updateResult = result,
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

    // 🔹 DELETE
    fun deleteTransaction(token: String, id: Int) {
        viewModelScope.launch {
            useCases.delete(token, id).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        deleteResult = result,
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

    // 🔹 Reset error / state (opsional)
    fun resetState() {
        _uiState.value = GoodsTransactionUiState()
    }
}