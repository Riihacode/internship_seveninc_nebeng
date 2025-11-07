package com.example.nebeng.feature_goods_ride_booking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.GoodsRideBookingUseCases
import com.example.nebeng.core.utils.BookingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoodsRideBookingViewModel @Inject constructor(
    private val useCases: GoodsRideBookingUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoodsRideBookingUiState())
    val uiState: StateFlow<GoodsRideBookingUiState> = _uiState

    // 🔹 READ ALL
    fun getAllBookings(token: String) = viewModelScope.launch {
        useCases.readAll(token).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update { it.copy(isLoading = false, bookings = result.data) }
                is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
            }
        }
    }

    // 🔹 READ BY ID
    fun getBookingById(token: String, id: Int) = viewModelScope.launch {
        useCases.readById(token, id).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update { it.copy(isLoading = false, selectedBooking = result.data) }
                is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
            }
        }
    }

    // 🔹 READ BY DRIVER
    fun getBookingsByDriverId(token: String, driverId: Int) = viewModelScope.launch {
        useCases.readByDriverId(token, driverId).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update { it.copy(isLoading = false, bookings = result.data) }
                is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
            }
        }
    }

    // 🔹 READ BY STATUS
    fun getBookingsByStatus(token: String, status: BookingStatus) = viewModelScope.launch {
        useCases.readByStatus(token, status).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update { it.copy(isLoading = false, bookings = result.data) }
                is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
            }
        }
    }

    // 🔹 CREATE
    fun createBooking(token: String, request: com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.CreateGoodsRideBookingRequest) =
        viewModelScope.launch {
            useCases.create(token, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update { it.copy(isLoading = false, createdBooking = result.data, isCreated = true) }
                    is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }

    // 🔹 UPDATE
    fun updateBooking(token: String, id: Int, request: com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.UpdateGoodsRideBookingRequest) =
        viewModelScope.launch {
            useCases.update(token, id, request).collect { result ->
                when (result) {
                    is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                    is Result.Success -> _uiState.update { it.copy(isLoading = false, isUpdated = true) }
                    is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }

    // 🔹 DELETE
    fun deleteBooking(token: String, id: Int) = viewModelScope.launch {
        useCases.delete(token, id).collect { result ->
            when (result) {
                is Result.Loading -> _uiState.update { it.copy(isLoading = true, error = null) }
                is Result.Success -> _uiState.update { it.copy(isLoading = false, isDeleted = true) }
                is Result.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
            }
        }
    }
}