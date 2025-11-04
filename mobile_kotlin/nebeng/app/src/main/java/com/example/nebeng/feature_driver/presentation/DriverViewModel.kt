package com.example.nebeng.feature_driver.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_driver.domain.usecase.DriverUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import kotlinx.coroutines.flow.update

@HiltViewModel
class DriverViewModel @Inject constructor(
    private val useCases: DriverUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(DriverUiState())
    val uiState: StateFlow<DriverUiState> = _uiState

    // ================================================================
    // 🔹 READ ALL
    // ================================================================
    fun getAllDrivers(token: String) {
        viewModelScope.launch {
            useCases.readAll(token).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, drivers = result.data, error = null)
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
    fun getDriverById(token: String, id: Int) {
        viewModelScope.launch {
            useCases.readById(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, selectedDriver = result.data, error = null)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 CREATE DRIVER
    // ================================================================
    fun createDriver(token: String, request: com.example.nebeng.feature_driver.data.remote.model.request.CreateDriverRequest) {
        viewModelScope.launch {
            useCases.create(token, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 UPDATE DRIVER
    // ================================================================
    fun updateDriver(token: String, id: Int, request: com.example.nebeng.feature_driver.data.remote.model.request.UpdateDriverRequest) {
        viewModelScope.launch {
            useCases.update(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, selectedDriver = result.data)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    // ================================================================
    // 🔹 DELETE DRIVER
    // ================================================================
    fun deleteDriver(token: String, id: Int) {
        viewModelScope.launch {
            useCases.delete(token, id).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}
