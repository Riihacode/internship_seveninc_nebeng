package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

import com.example.nebeng.core.utils.PaymentStatus

data class PassengerTransactionCustomer(
    // Tabel Passenger Transaction
    val idPassengerTransaction: Int,
    val transactionDate: String,
    val transactionCode: String,
    val midtransTransactionId: String,
    val paymentStatus: PaymentStatus,
    val createdAt: String,
    val paymentProofImg: String,
    val creditUsed: Int,
    val paymentMethodId: Int,
    val paymentType: String,
    val updatedAt: String,
    val totalAmount: Int,
    val midtransOrderId: String,
    val paymentExpiredAt: String,
    val passengerRideBookingId: Int,
    val vaNumber: String,
    val customerId: Int,
)