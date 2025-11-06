package com.example.nebeng.feature_rating.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.domain.model.Rating
import com.example.nebeng.feature_rating.domain.usecase.RatingUseCases
import com.example.nebeng.feature_rating.data.remote.model.request.CreateRatingRequest
import com.example.nebeng.feature_rating.data.remote.model.request.UpdateRatingRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val useCases: RatingUseCases
): ViewModel() {
    private val _uiState = MutableStateFlow(RatingUiState())
    val uiState: StateFlow<RatingUiState> = _uiState

    /* ============================================================
       🔹 Read All Ratings
       ============================================================ */
    fun getAllRatings(token: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            useCases.readAll(token).collectLatest { result ->
                when (result) {
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        ratings = Result.Success(result.data),
                        isLoading = false,
                        errorMessage = null
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        ratings = Result.Error(result.message ?: "Failed to load ratings"),
                        isLoading = false,
                        errorMessage = result.message
                    )
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    /* ============================================================
       🔹 Read Rating By Id
       ============================================================ */
    fun getRatingById(token: String, id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            useCases.readById(token, id).collectLatest { result ->
                when (result) {
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        currentRating = Result.Success(result.data),
                        isLoading = false
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        currentRating = Result.Error(result.message ?: "Failed to load rating"),
                        isLoading = false,
                        errorMessage = result.message
                    )
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    /* ============================================================
       🔹 Read Ratings By Driver Id
       ============================================================ */
    fun getRatingsByDriverId(token: String, driverId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            useCases.readByDriverId(token, driverId).collectLatest { result ->
                when (result) {
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        ratings = Result.Success(result.data),
                        isLoading = false
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        ratings = Result.Error(result.message ?: "Failed to load driver ratings"),
                        isLoading = false,
                        errorMessage = result.message
                    )
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    /* ============================================================
       🔹 Create Rating
       ============================================================ */
    fun createRating(token: String, request: CreateRatingRequest) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            useCases.create(token, request).collectLatest { result ->
                when (result) {
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        currentRating = Result.Success(result.data),
                        successMessage = "Rating berhasil dikirim",
                        isLoading = false
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        currentRating = Result.Error(result.message ?: "Gagal mengirim rating"),
                        isLoading = false,
                        errorMessage = result.message
                    )
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    /* ============================================================
       🔹 Update Rating
       ============================================================ */
    fun updateRating(token: String, id: Int, request: UpdateRatingRequest) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            useCases.update(token, id, request).collectLatest { result ->
                when (result) {
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        currentRating = Result.Success(result.data),
                        successMessage = "Rating berhasil diperbarui",
                        isLoading = false
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        currentRating = Result.Error(result.message ?: "Gagal memperbarui rating"),
                        isLoading = false,
                        errorMessage = result.message
                    )
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    /* ============================================================
       🔹 Delete Rating
       ============================================================ */
    fun deleteRating(token: String, id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            useCases.delete(token, id).collectLatest { result ->
                when (result) {
                    is Result.Success -> _uiState.value = _uiState.value.copy(
                        successMessage = "Rating berhasil dihapus",
                        isLoading = false
                    )
                    is Result.Error -> _uiState.value = _uiState.value.copy(
                        errorMessage = result.message ?: "Gagal menghapus rating",
                        isLoading = false
                    )
                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    /* ============================================================
       🔹 Utility Reset Functions
       ============================================================ */
    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            successMessage = null
        )
    }
}