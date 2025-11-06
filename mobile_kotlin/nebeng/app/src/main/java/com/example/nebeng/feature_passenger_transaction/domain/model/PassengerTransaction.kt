package com.example.nebeng.feature_passenger_transaction.domain.model

import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import com.example.nebeng.core.utils.PaymentStatus

data class PassengerTransaction(
    val id: Int,
    val passengerRideBookingId: Int,
    val customerId: Int,
    val totalAmount: Int,
    val paymentMethod: Int,
    val paymentProofImg: String?,
    val paymentStatus: PaymentStatus,
    val creditUsed: Int,
    val transactionDate: String,
    val createdAt: String,
    val updatedAt: String,


    val customer: Customer? = null,
    val paymentMethodDetail: PaymentMethod? = null,
    val booking: PassengerRideBooking? = null
)
