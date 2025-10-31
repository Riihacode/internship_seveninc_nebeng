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


//@HiltViewModel
//class HistoryOrderViewModel @Inject constructor() : ViewModel() {
//
//    private val _uiState = MutableStateFlow(HistoryOrderUiState())
//    val uiState: StateFlow<HistoryOrderUiState> = _uiState.asStateFlow()
//
//    init {
//        // Dummy isi sementara (sama seperti di HistoryOrderScreen)
//        viewModelScope.launch {
//            _uiState.value = HistoryOrderUiState(
//                historyItems = listOf(
//                    HistoryItemData(
//                        "XZH80BV",
//                        "TOYOTA AVANZA VELOZ",
//                        "YOG POS 2 → SOLO POS 1",
//                        "Rp 90.000",
//                        "Selesai",
//                        "Mobil"
//                    ),
//                    HistoryItemData(
//                        "GH12UW2",
//                        "NEBENG BARANG",
//                        "YOG POS 2 → SOLO POS 1",
//                        "Rp 50.000",
//                        "Selesai",
//                        "Barang"
//                    ),
//                    HistoryItemData(
//                        "ZH12M3T",
//                        "YAMAHA NMAX",
//                        "YOG POS 2 → SOLO POS 1",
//                        "Rp 110.000",
//                        "Selesai",
//                        "Motor"
//                    ),
//                )
//            )
//        }
//    }
//}
@HiltViewModel
class HistoryOrderViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        HistoryOrderUiState(
            historyItems = listOf(
                HistoryItemData(
                    code = "1",
                    category = "Mobil",
                    status = "Selesai",
                    fromCity = "Yogyakarta",
                    toCity = "Purwokerto",
                    fromPos = "Pos 1",
                    toPos = "Pos 2",
                    dayDate = "Senin, 2 September 2024",
                    time = "09:00",
                    vehicle = "Avanza Veloz",
                    plate = "R 2424 MJ",
                    pax = 2,
                    totalPrice = "Rp120.000"
                ),
                HistoryItemData(
                    code = "2",
                    category = "Motor",
                    status = "Proses",
                    fromCity = "Solo",
                    toCity = "Semarang",
                    fromPos = "Pos 1",
                    toPos = "Pos 2",
                    dayDate = "Selasa, 3 September 2024",
                    time = "13:00",
                    vehicle = "Beat Street",
                    plate = "AB 2010 KA",
                    pax = 1,
                    totalPrice = "Rp90.000"
                ),
                HistoryItemData(
                    code = "3",
                    category = "Barang",
                    status = "Dibatalkan",
                    fromCity = "Yogyakarta",
                    toCity = "Magelang",
                    fromPos = "Pos 3",
                    toPos = "Pos 4",
                    dayDate = "Rabu, 4 September 2024",
                    time = "08:00",
                    vehicle = "Pickup L300",
                    plate = "B 1234 XY",
                    pax = 1,
                    totalPrice = "Rp150.000"
                )
            )
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onChangeSchedule(item: HistoryItemData) {
        println("Mengubah jadwal untuk order ${item.code}")
    }
}
