package com.example.nebeng.feature_a_history_order.domain.usecase

import com.example.nebeng.feature_a_history_order.domain.mapper.HistoryOrderItemMapper
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * ============================================================
 * ⚙️ Use Case: GetHistoryOrdersUseCase
 * ------------------------------------------------------------
 * Mendapatkan daftar riwayat pesanan customer (atau driver)
 * dengan autentikasi token dari DataStore.
 * ============================================================
 */
class GetPassengerRideBookingHistoryOrdersUseCase @Inject constructor(
    private val repository: PassengerRideBookingRepository
) {
    /**
     * Mengambil semua booking user (dari API) dan mapping
     * ke HistoryOrderItem agar siap ditampilkan di UI.
     */
    suspend operator fun invoke(token: String): Flow<List<HistoryOrderItem>> {
        return repository.readAllFull(token).map { fullList ->
            fullList.map { full ->
                HistoryOrderItemMapper.fromFull(full)
            }
        }
    }
}