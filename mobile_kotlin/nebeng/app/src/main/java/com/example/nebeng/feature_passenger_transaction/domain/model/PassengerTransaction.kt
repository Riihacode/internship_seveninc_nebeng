package com.example.nebeng.feature_passenger_transaction.domain.model

import com.example.nebeng.core.utils.PaymentStatus

data class PassengerTransaction(
    val id: Int,
    val passengerRideBookingId: Int,
    val customerId: Int,
    val totalAmount: Int,
    val paymentMethod: Int,
    val paymentProofImg: String?,
    val paymentStatus: PaymentStatus?,
    val creditUsed: Int,
    val transactionDate: String,
    val createdAt: String,
    val updatedAt: String,
)
