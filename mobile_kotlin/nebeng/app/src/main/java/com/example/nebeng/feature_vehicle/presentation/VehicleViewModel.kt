package com.example.nebeng.feature_vehicle.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.remote.model.request.CreateVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.PatchVerifyFalseByIdVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.UpdateVehicleRequest
import com.example.nebeng.feature_vehicle.domain.usecase.VehicleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel utama untuk feature Vehicle.
 * Bertugas mengorkestrasi usecase dan memperbarui state untuk UI layer.
 */
@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val useCases: VehicleUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(VehicleUiState())
    val uiState: StateFlow<VehicleUiState> = _uiState

    // 🔹 READ ALL
    fun getAllVehicles(token: String) = viewModelScope.launch {
        useCases.readAll(token).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, vehicles = result.data)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }
    }

    // 🔹 READ BY ID
    fun getVehicleById(token: String, id: Int) = viewModelScope.launch {
        useCases.readById(token, id).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, selectedVehicle = result.data)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }
    }

    // 🔹 READ BY DRIVER ID
    fun getVehiclesByDriverId(token: String, driverId: Int) = viewModelScope.launch {
        useCases.readByDriverId(token, driverId).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, vehicles = result.data)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }
    }

    // 🔹 CREATE
    fun createVehicle(token: String, request: CreateVehicleRequest) = viewModelScope.launch {
        useCases.create(token, request).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, createdVehicle = result.data, isCreated = true)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }
    }

    // 🔹 UPDATE
    fun updateVehicle(token: String, id: Int, request: UpdateVehicleRequest) =
        viewModelScope.launch {
            useCases.update(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, isUpdated = true)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }

    // 🔹 PATCH VERIFY TRUE
    fun verifyVehicleTrue(token: String, id: Int) = viewModelScope.launch {
        useCases.patchVerifyTrue(token, id).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, isVerifiedTrue = true)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }
    }

    // 🔹 PATCH VERIFY FALSE
    fun verifyVehicleFalse(token: String, id: Int, request: PatchVerifyFalseByIdVehicleRequest) =
        viewModelScope.launch {
            useCases.patchVerifyFalse(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update {
                        it.copy(isLoading = false, isVerifiedFalse = true)
                    }
                    is Result.Error -> _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }

    // 🔹 DELETE
    fun deleteVehicle(token: String, id: Int) = viewModelScope.launch {
        useCases.delete(token, id).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, isDeleted = true)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }
    }
}