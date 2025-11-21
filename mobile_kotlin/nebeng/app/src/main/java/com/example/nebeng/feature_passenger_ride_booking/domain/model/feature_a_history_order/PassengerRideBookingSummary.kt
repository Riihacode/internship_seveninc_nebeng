package com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order

/**
 * Ringkasan satu booking penumpang beserta turunan pentingnya.
 *
 * Ini yang nantinya akan “disebar” ke feature lain (misal: history),
 * supaya mereka tidak perlu tahu domain Customer/Driver/PassengerRide penuh.
 */
data class PassengerRideBookingSummary(
    val bookingId: Int,
    val bookingCode: String?,
    val createdAt: String,
    val status: String,
    val customer: BookingCustomerSummary,
    val driver: BookingDriverSummary,
    val ride: BookingRideSummary,
    val transaction: BookingTransactionSummary
)
