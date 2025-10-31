package com.example.nebeng.feature_history_order.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_history_order.presentation.support_for_present.model.HistoryItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HistoryOrderViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryOrderUiState())
    val uiState: StateFlow<HistoryOrderUiState> = _uiState.asStateFlow()

    init {
        // Dummy isi sementara (sama seperti di HistoryOrderScreen)
        viewModelScope.launch {
            _uiState.value = HistoryOrderUiState(
                historyItems = listOf(
                    HistoryItemData(
                        "XZH80BV",
                        "TOYOTA AVANZA VELOZ",
                        "YOG POS 2 → SOLO POS 1",
                        "Rp 90.000",
                        "Selesai",
                        "Mobil"
                    ),
                    HistoryItemData(
                        "GH12UW2",
                        "NEBENG BARANG",
                        "YOG POS 2 → SOLO POS 1",
                        "Rp 50.000",
                        "Selesai",
                        "Barang"
                    ),
                    HistoryItemData(
                        "ZH12M3T",
                        "YAMAHA NMAX",
                        "YOG POS 2 → SOLO POS 1",
                        "Rp 110.000",
                        "Selesai",
                        "Motor"
                    ),
                )
            )
        }
    }
}