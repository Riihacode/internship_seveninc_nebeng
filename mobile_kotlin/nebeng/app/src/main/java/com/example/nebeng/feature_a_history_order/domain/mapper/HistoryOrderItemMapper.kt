package com.example.nebeng.feature_a_history_order.domain.mapper

import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_customer.data.remote.model.dto.DataItemDto
import com.example.nebeng.feature_customer.domain.model.UserCustomerSummary
import com.example.nebeng.feature_passenger_ride_booking.domain.model.feature_a_history_order.PassengerRideBookingSummary

fun PassengerRideBookingSummary.toHistoryOrderItem(): HistoryOrderItem {
    return HistoryOrderItem(
        bookingId           = bookingId,
        bookingCode         = bookingCode,
        createdAt           = createdAt,

        departureTerminalId = ride.departureTerminalId,
        arrivalTerminalId   = ride.arrivalTerminalId,
        seatsReserved       = ride.seatsReserved,
        totalPrice          = transaction.totalAmount,

        averageRating       = driver.averageRating,
        customerName        = customer.fullName,
        customerId          = customer.id,

        bookingStatus       = BookingStatus.fromString(status),                 // 👈 enum 1
        vehicleType         = VehicleType.fromString(ride.vehicleType),           // 👈 enum 2
        rideStatus          = RideStatus.fromString(ride.rideStatus),              // 👈 enum 3
        driverName          = driver.fullName
    )
}

fun HistoryOrderItem.withCustomerInfo(summary: UserCustomerSummary): HistoryOrderItem {
    return this.copy(
        customerId = summary.customerId,
        customerName = summary.customerName
    )
}