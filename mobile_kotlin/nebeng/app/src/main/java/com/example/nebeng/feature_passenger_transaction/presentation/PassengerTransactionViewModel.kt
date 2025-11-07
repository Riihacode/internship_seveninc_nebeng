package com.example.nebeng.feature_passenger_transaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.*
import com.example.nebeng.feature_passenger_transaction.domain.usecase.PassengerTransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class PassengerTransactionViewModel @Inject constructor(
    private val useCases: PassengerTransactionUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(PassengerTransactionUiState())
    val uiState: StateFlow<PassengerTransactionUiState> = _uiState.asStateFlow()

    fun readAll(token: String) = viewModelScope.launch {
        useCases.readAll(token).collect { result ->
            _uiState.update {
                it.copy(
                    transactions = result,
                    isLoading = result is Result.Loading,
                    message = if (result is Result.Error) result.message else null
                )
            }
        }
    }

    fun readById(token: String, id: Int) = viewModelScope.launch {
        useCases.readById(token, id).collect { result ->
            _uiState.update {
                it.copy(
                    currentTransaction = result,
                    isLoading = result is Result.Loading,
                    message = if (result is Result.Error) result.message else null
                )
            }
        }
    }

    fun readByBookingId(token: String, bookingId: Int) = viewModelScope.launch {
        useCases.readByPassengerRideBookingId(token, bookingId).collect { result ->
            _uiState.update {
                it.copy(
                    currentTransaction = result,
                    isLoading = result is Result.Loading,
                    message = if (result is Result.Error) result.message else null
                )
            }
        }
    }

    fun create(token: String, request: CreatePassengerTransactionRequest) = viewModelScope.launch {
        useCases.create(token, request).collect { result ->
            _uiState.update {
                it.copy(
                    currentTransaction = result,
                    isLoading = result is Result.Loading,
                    message = if (result is Result.Error) result.message else null
                )
            }
        }
    }

    fun update(token: String, id: Int, request: UpdatePassengerTransactionRequest) = viewModelScope.launch {
        useCases.update(token, id, request).collect { result ->
            _uiState.update {
                it.copy(
                    currentTransaction = result,
                    isLoading = result is Result.Loading,
                    message = if (result is Result.Error) result.message else null
                )
            }
        }
    }

    fun patchStatus(token: String, id: Int, request: PatchStatusByIdPassengerTransactionRequest) = viewModelScope.launch {
        useCases.patchStatus(token, id, request).collect { result ->
            _uiState.update {
                it.copy(
                    currentTransaction = result,
                    isLoading = result is Result.Loading,
                    message = if (result is Result.Error) result.message else null
                )
            }
        }
    }

    fun delete(token: String, id: Int) = viewModelScope.launch {
        useCases.delete(token, id).collect { result ->
            _uiState.update {
                it.copy(
                    deleteResult = result,
                    isLoading = result is Result.Loading,
                    message = if (result is Result.Error) result.message else null
                )
            }
        }
    }
}
