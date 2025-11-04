package com.example.nebeng.feature_passenger_ride_booking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.PassengerRideBookingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.PatchPassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.UpdatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking

@HiltViewModel
class PassengerRideBookingViewModel @Inject constructor(
    private val useCases: PassengerRideBookingUseCases
) : ViewModel() {
    // =============================================
    // 🔹 State for all bookings
    // =============================================
    private val _bookingListState = MutableStateFlow<Result<List<PassengerRideBooking>>>(Result.Loading)
    val bookingListState: StateFlow<Result<List<PassengerRideBooking>>> = _bookingListState

    // =============================================
    // 🔹 State for booking detail
    // =============================================
    private val _bookingDetailState = MutableStateFlow<Result<PassengerRideBooking>>(Result.Loading)
    val bookingDetailState: StateFlow<Result<PassengerRideBooking>> = _bookingDetailState

    // =============================================
    // 🔹 State for creation / update / patch / delete
    // =============================================
    private val _createBookingState = MutableStateFlow<Result<PassengerRideBooking>>(Result.Loading)
    val createBookingState: StateFlow<Result<PassengerRideBooking>> = _createBookingState

    private val _updateBookingState = MutableStateFlow<Result<PassengerRideBooking>>(Result.Loading)
    val updateBookingState: StateFlow<Result<PassengerRideBooking>> = _updateBookingState

    private val _patchBookingState = MutableStateFlow<Result<PassengerRideBooking>>(Result.Loading)
    val patchBookingState: StateFlow<Result<PassengerRideBooking>> = _patchBookingState

    private val _deleteBookingState = MutableStateFlow<Result<String>>(Result.Loading)
    val deleteBookingState: StateFlow<Result<String>> = _deleteBookingState


    // =============================================
    // 🔹 READ ALL
    // =============================================
    fun getAllBookings(token: String) {
        viewModelScope.launch {
            _bookingListState.value = Result.Loading
            try {
                useCases.readAll(token).collect { bookings ->
                    _bookingListState.value = Result.Success(bookings)
                }
            } catch (e: Exception) {
                _bookingListState.value = Result.Error(e.message ?: "Failed to load bookings")
            }
        }
    }

    // =============================================
    // 🔹 READ BY ID
    // =============================================
    fun getBookingById(token: String, id: Int) {
        viewModelScope.launch {
            _bookingDetailState.value = Result.Loading
            try {
                useCases.readById(token, id).collect { booking ->
                    _bookingDetailState.value = Result.Success(booking)
                }
            } catch (e: Exception) {
                _bookingDetailState.value = Result.Error(e.message ?: "Failed to fetch booking")
            }
        }
    }

    // =============================================
    // 🔹 READ BY RIDE ID
    // =============================================
    fun getBookingsByRideId(token: String, rideId: Int) {
        viewModelScope.launch {
            _bookingListState.value = Result.Loading
            try {
                useCases.readByPassengerRideId(token, rideId).collect { bookings ->
                    _bookingListState.value = Result.Success(bookings)
                }
            } catch (e: Exception) {
                _bookingListState.value = Result.Error(e.message ?: "Failed to fetch by ride ID")
            }
        }
    }

    // =============================================
    // 🔹 READ BY CUSTOMER ID
    // =============================================
    fun getBookingsByCustomerId(token: String, customerId: Int) {
        viewModelScope.launch {
            _bookingListState.value = Result.Loading
            try {
                useCases.readByCustomerId(token, customerId).collect { bookings ->
                    _bookingListState.value = Result.Success(bookings)
                }
            } catch (e: Exception) {
                _bookingListState.value = Result.Error(e.message ?: "Failed to fetch by customer ID")
            }
        }
    }

    // =============================================
    // 🔹 CREATE
    // =============================================
    fun createBooking(token: String, request: CreatePassengerRideBookingRequest) {
        viewModelScope.launch {
            _createBookingState.value = Result.Loading
            try {
                useCases.create(token, request).collect { booking ->
                    _createBookingState.value = Result.Success(booking)
                }
            } catch (e: Exception) {
                _createBookingState.value = Result.Error(e.message ?: "Failed to create booking")
            }
        }
    }

    // =============================================
    // 🔹 UPDATE
    // =============================================
    fun updateBooking(token: String, id: Int, request: UpdatePassengerRideBookingRequest) {
        viewModelScope.launch {
            _updateBookingState.value = Result.Loading
            try {
                useCases.update(token, id, request).collect { booking ->
                    _updateBookingState.value = Result.Success(booking)
                }
            } catch (e: Exception) {
                _updateBookingState.value = Result.Error(e.message ?: "Failed to update booking")
            }
        }
    }

    // =============================================
    // 🔹 PATCH STATUS
    // =============================================
    fun patchBookingStatus(token: String, id: Int, request: PatchPassengerRideBookingRequest) {
        viewModelScope.launch {
            _patchBookingState.value = Result.Loading
            try {
                useCases.patch(token, id, request).collect { booking ->
                    _patchBookingState.value = Result.Success(booking)
                }
            } catch (e: Exception) {
                _patchBookingState.value = Result.Error(e.message ?: "Failed to patch status")
            }
        }
    }

    // =============================================
    // 🔹 DELETE
    // =============================================
    fun deleteBooking(token: String, id: Int) {
        viewModelScope.launch {
            _deleteBookingState.value = Result.Loading
            try {
                useCases.delete(token, id).collect { success ->
                    _deleteBookingState.value = if (success)
                        Result.Success("Booking deleted successfully")
                    else Result.Error("Failed to delete booking")
                }
            } catch (e: Exception) {
                _deleteBookingState.value = Result.Error(e.message ?: "Error deleting booking")
            }
        }
    }
}