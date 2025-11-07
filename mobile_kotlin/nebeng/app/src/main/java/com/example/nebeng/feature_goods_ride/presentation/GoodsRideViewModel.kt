package com.example.nebeng.feature_goods_ride.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride.data.remote.model.request.CreateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.data.remote.model.request.UpdateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.domain.usecase.GoodsRideUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoodsRideViewModel @Inject constructor(
    private val useCases: GoodsRideUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoodsRideUiState())
    val uiState: StateFlow<GoodsRideUiState> = _uiState

    // ==========================================
    // 🔹 GET ALL GOODS RIDES
    // ==========================================
    fun getAllGoodsRides(token: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(goodsRides = Result.Loading)
            try {
                useCases.readAll(token).collect { result ->
                    _uiState.value = _uiState.value.copy(goodsRides = result)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(goodsRides = Result.Error(e.message))
            }
        }
    }

    // ==========================================
    // 🔹 GET GOODS RIDE BY ID
    // ==========================================
    fun getGoodsRideById(token: String, id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(selectedRide = Result.Loading)
            try {
                useCases.readById(token, id).collect { result ->
                    _uiState.value = _uiState.value.copy(selectedRide = result)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(selectedRide = Result.Error(e.message))
            }
        }
    }

    // ==========================================
    // 🔹 CREATE GOODS RIDE
    // ==========================================
    fun createGoodsRide(token: String, request: CreateGoodsRideRequest) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(createResult = Result.Loading)
            try {
                useCases.create(token, request).collect { result ->
                    _uiState.value = _uiState.value.copy(createResult = result)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(createResult = Result.Error(e.message))
            }
        }
    }

    // ==========================================
    // 🔹 UPDATE GOODS RIDE
    // ==========================================
    fun updateGoodsRide(token: String, id: Int, request: UpdateGoodsRideRequest) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(updateResult = Result.Loading)
            try {
                useCases.update(token, id, request).collect { result ->
                    _uiState.value = _uiState.value.copy(updateResult = result)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(updateResult = Result.Error(e.message))
            }
        }
    }

    // ==========================================
    // 🔹 DELETE GOODS RIDE
    // ==========================================
    fun deleteGoodsRide(token: String, id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(deleteResult = Result.Loading)
            try {
                useCases.delete(token, id).collect { result ->
                    _uiState.value = _uiState.value.copy(deleteResult = result)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(deleteResult = Result.Error(e.message))
            }
        }
    }
}