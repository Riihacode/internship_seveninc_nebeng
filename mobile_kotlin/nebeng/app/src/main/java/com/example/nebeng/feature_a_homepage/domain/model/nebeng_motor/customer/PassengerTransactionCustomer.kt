package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

import com.example.nebeng.core.utils.PaymentStatus

data class PassengerTransactionCustomer(
    // Tabel Passenger Transaction
    val idPassengerTransaction: Int,
    val transactionDate: String,
    val paymentStatus: PaymentStatus
)
