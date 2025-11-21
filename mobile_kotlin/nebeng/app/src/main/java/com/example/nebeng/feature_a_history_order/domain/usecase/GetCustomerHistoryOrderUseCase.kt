package com.example.nebeng.feature_a_history_order.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_history_order.domain.aggregator.HistoryOrderAggregator
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
class GetCustomerHistoryOrderUseCase @Inject constructor(
    private val passengerRideBookingRepository: PassengerRideBookingRepository,
    private val terminalRepository: TerminalRepository
) {

    suspend operator fun invoke(
        token: String,
        customerId: Int
    ): Flow<Result<List<HistoryOrderItem>>> {

        val bookingsFlow = passengerRideBookingRepository.readHistorySummaryByCustomerId(token, customerId)

        val terminalsFlow = terminalRepository.getAllTerminal(token)

        return combine(bookingsFlow, terminalsFlow) { bookingResult, terminalResult ->

            when {
                bookingResult is Result.Loading || terminalResult is Result.Loading ->
                    Result.Loading

                bookingResult is Result.Error ->
                    Result.Error(bookingResult.message)

                terminalResult is Result.Error ->
                    Result.Error(terminalResult.message)

                bookingResult is Result.Success && terminalResult is Result.Success -> {
                    val bookings = bookingResult.data
                    val terminals = terminalResult.data

                    val mapped = HistoryOrderAggregator.combine(bookings, terminals)
                    Result.Success(mapped)
                }

                else -> Result.Error("Unexpected state")
            }
        }
    }
}

