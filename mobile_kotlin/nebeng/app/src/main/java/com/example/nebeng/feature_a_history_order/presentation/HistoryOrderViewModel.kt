package com.example.nebeng.feature_a_history_order.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_a_history_order.domain.usecase.HistoryOrderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
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
//@HiltViewModel
//class HistoryOrderViewModel @Inject constructor() : ViewModel() {
//
//    private val _uiState = MutableStateFlow(
//        HistoryOrderUiState(
//            historyItems = listOf(
//                HistoryItemData(
//                    code = "1",
//                    category = "Mobil",
//                    status = "Selesai",
//                    fromCity = "Yogyakarta",
//                    toCity = "Purwokerto",
//                    fromPos = "Pos 1",
//                    toPos = "Pos 2",
//                    dayDate = "Senin, 2 September 2024",
//                    time = "09:00",
//                    vehicle = "Avanza Veloz",
//                    plate = "R 2424 MJ",
//                    pax = 2,
//                    totalPrice = "Rp120.000"
//                ),
//                HistoryItemData(
//                    code = "2",
//                    category = "Motor",
//                    status = "Proses",
//                    fromCity = "Solo",
//                    toCity = "Semarang",
//                    fromPos = "Pos 1",
//                    toPos = "Pos 2",
//                    dayDate = "Selasa, 3 September 2024",
//                    time = "13:00",
//                    vehicle = "Beat Street",
//                    plate = "AB 2010 KA",
//                    pax = 1,
//                    totalPrice = "Rp90.000"
//                ),
//                HistoryItemData(
//                    code = "3",
//                    category = "Barang",
//                    status = "Dibatalkan",
//                    fromCity = "Yogyakarta",
//                    toCity = "Magelang",
//                    fromPos = "Pos 3",
//                    toPos = "Pos 4",
//                    dayDate = "Rabu, 4 September 2024",
//                    time = "08:00",
//                    vehicle = "Pickup L300",
//                    plate = "B 1234 XY",
//                    pax = 1,
//                    totalPrice = "Rp150.000"
//                )
//            )
//        )
//    )
//
//    val uiState = _uiState.asStateFlow()
//
//    fun onChangeSchedule(item: HistoryItemData) {
//        println("Mengubah jadwal untuk order ${item.code}")
//    }
//}

//@HiltViewModel
//class HistoryOrderViewModel @Inject constructor(
//    private val getHistoryOrdersUseCase: GetHistoryOrdersUseCase
//) : ViewModel() {
//
//    private val _uiState = MutableStateFlow(HistoryOrderUiState())
//    val uiState: StateFlow<HistoryOrderUiState> = _uiState
//
//    fun loadHistory(token: String) {
//        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
//
//            try {
//                getHistoryOrdersUseCase(token).collect { items ->
//                    _uiState.value = HistoryOrderUiState(
//                        historyItems = items,
//                        isLoading = false,
//                        errorMessage = null
//                    )
//                }
//            } catch (e: Exception) {
//                _uiState.value = HistoryOrderUiState(
//                    historyItems = emptyList(),
//                    isLoading = false,
//                    errorMessage = e.message ?: "Terjadi kesalahan saat memuat riwayat"
//                )
//            }
//        }
//    }
//
//    fun onChangeSchedule(item: HistoryOrderItem) {
//        // Bisa diarahkan ke detail screen, ubah jadwal, dsb.
//        println("🗓️ Jadwal diubah untuk: ${item.bookingCode}")
//        // atau:
//        // _uiState.value = _uiState.value.copy(selectedItem = item)
//    }
//}


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
//    private val getHistoryOrdersUseCase: GetHistoryOrdersUseCase
    private val historyOrderUseCases: HistoryOrderUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryOrderUiState())
    val uiState: StateFlow<HistoryOrderUiState> = _uiState

    /**
     * Memuat data riwayat berdasarkan token user (JWT).
     */
    fun loadHistory(token: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val bookingsFlow = historyOrderUseCases.getPassengerRideBooking(token)
            val terminalsFlow = historyOrderUseCases.getTerminal(token)

            combine(bookingsFlow, terminalsFlow) { bookings, terminals ->
                // join nama terminal berdasarkan id
                bookings.map{ booking ->
                    val departureTerminal = terminals.find { it.id == booking.departureTerminalId }
                    val arrivalTerminal = terminals.find { it.id == booking.arrivalTerminalId }

                    val departureName = departureTerminal?.name ?: "Unknown"
                    val arrivalName = arrivalTerminal?.name ?: "Unknown"

                    val departureAddress = departureTerminal?.fullAddress ?: "-"
                    val arrivalAddress = arrivalTerminal?.fullAddress ?: "-"

                    booking.copy(
                        // extend HistoryOrderItem dengan field baru
                        departureTerminalName = departureName,
                        arrivalTerminalName = arrivalName,
                        departureTerminalDetail = departureAddress,
                        arrivalTerminalDetail = arrivalAddress
                    )
                }

            }.catch { e ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Terjadi kesalahan saat memuat riwayat"
                )
            }.collectLatest { joined ->
                _uiState.value = _uiState.value.copy(
                    historyItems = joined,
                    isLoading = false
                )
            }
        }
    }

    /**
     * Memuat data riwayat berdasarkan token user (JWT).
     * Dipakai jika sudah bisa akses tabel vehicle dari tabel passenger ride
     */
//    fun loadHistory(token: String) {
//        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(isLoading = true)
//
//            val bookingsFlow = historyOrderUseCases.getPassengerRideBooking(token)
//            val terminalsFlow = historyOrderUseCases.getTerminal(token)
//
//            combine(bookingsFlow, terminalsFlow) { bookings, terminals ->
//                // join nama terminal berdasarkan id
//                bookings.map{ booking ->
//                    val departureTerminal = terminals.find { it.id == booking.departureTerminalId }
//                    val arrivalTerminal = terminals.find { it.id == booking.arrivalTerminalId }
//
//                    val departureName = departureTerminal?.name ?: "Unknown"
//                    val arrivalName = arrivalTerminal?.name ?: "Unknown"
//
//                    val departureAddress = departureTerminal?.fullAddress ?: "-"
//                    val arrivalAddress = arrivalTerminal?.fullAddress ?: "-"
//
//                    booking.copy(
//                        // extend HistoryOrderItem dengan field baru
//                        departureTerminalName = departureName,
//                        arrivalTerminalName = arrivalName,
//                        departureTerminalDetail = departureAddress,
//                        arrivalTerminalDetail = arrivalAddress
//                    )
//                }
//
//            }.catch { e ->
//                _uiState.value = _uiState.value.copy(
//                    isLoading = false,
//                    errorMessage = e.message ?: "Terjadi kesalahan saat memuat riwayat"
//                )
//            }.collectLatest { joined ->
//
//                // 🔥 Sekarang ambil kendaraan berdasarkan driverId booking pertama
//                val driverId = joined.firstOrNull()?.driverId
//
//                if (driverId != null) {
//                    historyOrderUseCases.getVehicle(token, driverId).collectLatest { vehicles ->
//                        val updated = joined.map { booking ->
//                            val vehicle = vehicles.firstOrNull() { it.driverId == booking.driverId }
//
//                            booking.copy(
//                                vehicleName = vehicle?.vehicleName ?: "-",
//                                vehicleColor = vehicle?.vehicleColor ?: "-"
//                            )
//                        }
//
//                        _uiState.value = _uiState.value.copy(
//                            historyItems = updated,
//                            isLoading = false
//                        )
//                    }
//                }
//                else {
//                    // tidak ada driverId
//                    _uiState.value = _uiState.value.copy(
//                        historyItems = joined,
//                        isLoading = false
//                    )
//                }
//            }
//        }
//    }

    /**
     * Dipanggil ketika user menekan kartu history untuk ubah jadwal, dll.
     */
    fun onChangeSchedule(item: HistoryOrderItem) {
        println("🗓️ Jadwal diubah untuk: ${item.bookingCode}")
        // Implementasi lanjutan: navigasi, dialog, dsb.
    }

    fun selectHistoryItem(bookingId: Int) {
        val selected  = _uiState.value.historyItems.firstOrNull() { it.bookingId == bookingId }
    }
}