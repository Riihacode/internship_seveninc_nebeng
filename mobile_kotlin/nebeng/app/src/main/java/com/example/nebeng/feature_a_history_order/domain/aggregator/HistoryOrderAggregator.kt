package com.example.nebeng.feature_a_history_order.domain.aggregator

import com.example.nebeng.feature_a_history_order.domain.mapper.toHistoryOrderItem
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary
import com.example.nebeng.feature_terminal.domain.model.Terminal

/**
 * ============================================================
 * 🔄 HistoryOrderAggregator
 * ------------------------------------------------------------
 * Menggabungkan:
 *  - PassengerRideBookingSummary (booking)
 *  - Terminal list (terminalName, address)
 *
 * Output:
 *  - List<HistoryOrderItem> yang siap masuk UI
 * ============================================================
 */
object HistoryOrderAggregator {

    fun combine(
        bookings: List<PassengerRideBookingSummary>,
        terminals: List<Terminal>,
    ): List<HistoryOrderItem> {

        return bookings.map { summary ->

            val departure = terminals.find { it.id == summary.ride.departureTerminalId }
            val arrival = terminals.find { it.id == summary.ride.arrivalTerminalId }

            summary.toHistoryOrderItem().copy(
                departureTerminalName = departure?.name ?: "-",
                arrivalTerminalName = arrival?.name ?: "-",
                departureTerminalDetail = departure?.fullAddress ?: "-",
                arrivalTerminalDetail = arrival?.fullAddress ?: "-"
            )
        }
    }
}