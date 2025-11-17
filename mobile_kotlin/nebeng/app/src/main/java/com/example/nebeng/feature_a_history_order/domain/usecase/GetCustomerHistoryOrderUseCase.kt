package com.example.nebeng.feature_a_history_order.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_history_order.domain.mapper.toHistoryOrderItem
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import kotlinx.coroutines.flow.combine

/**
 * ============================================================
 * ⚙️ Use Case: GetHistoryOrdersUseCase
 * ------------------------------------------------------------
 * Mendapatkan daftar riwayat pesanan customer (atau driver)
 * dengan autentikasi token dari DataStore.
 * ============================================================
 */
//class GetPassengerRideBookingHistoryOrdersUseCase @Inject constructor(
//    private val repository: PassengerRideBookingRepository
//) {
//    /**
//     * Mengambil semua booking user (dari API) dan mapping
//     * ke HistoryOrderItem agar siap ditampilkan di UI.
//     */
//    suspend operator fun invoke(token: String): Flow<List<HistoryOrderItem>> {
//        return repository.readAllFull(token).map { fullList ->
//            fullList.map { full ->
//                HistoryOrderItemMapper.fromFull(full)
//            }
//        }
//    }
//}

class GetPassengerRideBookingHistoryOrderByCustomerIdUseCase @Inject constructor(
    private val passengerRideBookingRepository: PassengerRideBookingRepository,
    private val terminalRepository: TerminalRepository
) {

    suspend operator fun invoke(
        token: String,
        customerId: Int
    ): Flow<Result<List<HistoryOrderItem>>> {

        val bookingsFlow = passengerRideBookingRepository
            .readHistorySummaryByCustomerId(token, customerId)

        val terminalsFlow = terminalRepository
            .getAllTerminal(token)  // ⬅️ cocok dengan implementasi repo kamu

        return combine(bookingsFlow, terminalsFlow) { bookingResult, terminalResult ->

            // loading
            if (bookingResult is Result.Loading || terminalResult is Result.Loading)
                return@combine Result.Loading

            // error handling
            if (bookingResult is Result.Error)
                return@combine Result.Error(bookingResult.message)
            if (terminalResult is Result.Error)
                return@combine Result.Error(terminalResult.message)

            val bookings = (bookingResult as Result.Success).data
            val terminals = (terminalResult as Result.Success).data

            val mapped = bookings.map { summary ->
                val departure = terminals.find { it.id == summary.ride.departureTerminalId }
                val arrival = terminals.find { it.id == summary.ride.arrivalTerminalId }

                summary.toHistoryOrderItem().copy(
                    departureTerminalName = departure?.name ?: "-",
                    arrivalTerminalName   = arrival?.name ?: "-",
                    departureTerminalDetail = departure?.fullAddress ?: "-",
                    arrivalTerminalDetail   = arrival?.fullAddress ?: "-"
                )
            }

            Result.Success(mapped)
        }
    }
}
