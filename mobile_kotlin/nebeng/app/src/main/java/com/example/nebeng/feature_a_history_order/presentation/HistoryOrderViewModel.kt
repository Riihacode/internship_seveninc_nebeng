package com.example.nebeng.feature_a_history_order.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_a_history_order.domain.usecase.GetCustomerHistoryOrderUseCase
import com.example.nebeng.feature_a_history_order.domain.usecase.GetDriverHistoryOrderUseCase
import com.example.nebeng.feature_a_history_order.domain.usecase.HistoryOrderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.session.data.UserPreferencesRepository

/**
 * ============================================================
 * 🧭 ViewModel: HistoryOrderViewModel
 * ------------------------------------------------------------
 * Mengelola UI state untuk screen History Order.
 * Memanggil use case dan memperbarui state.
 * ============================================================
 */
@HiltViewModel
class HistoryOrderViewModel @Inject constructor(
    private val getCustomerHistoryOrderUseCase: GetCustomerHistoryOrderUseCase,
    private val getDriverHistoryOrderUseCase: GetDriverHistoryOrderUseCase,
    private val userPrefsRepo: UserPreferencesRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            combine(
                userPrefsRepo.tokenFLow,
                userPrefsRepo.customerIdFlow
            ) { token, customerId -> Pair(token, customerId) }
                .collectLatest { (token, customerId) ->
                    if (!token.isNullOrBlank() && customerId > 0) {
                        loadHistory(token, customerId)
                    }
                }
        }
    }


    private val _uiState = MutableStateFlow(HistoryOrderUiState())
    val uiState: StateFlow<HistoryOrderUiState> = _uiState

    /**
     * Memuat data riwayat berdasarkan token user (JWT).
     */
    fun loadHistory(token: String, customerId: Int) {
        viewModelScope.launch {
            getCustomerHistoryOrderUseCase(token, customerId).collect { result ->

                when (result) {
                    is Result.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }

                    is Result.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }

                    is Result.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            historyItems = result.data,
                            errorMessage = null
                        )
                    }
                }
            }
        }
    }
    /**
     * Dipanggil ketika user menekan kartu history untuk ubah jadwal, dll.
     */
    fun onChangeSchedule(item: HistoryOrderItem) {
        println("🗓️ Jadwal diubah untuk: ${item.bookingCode}")
        // Implementasi lanjutan: navigasi, dialog, dsb.
    }
}