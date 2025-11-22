package com.example.nebeng.feature_passenger_transaction.domain.model

import com.example.nebeng.core.utils.PaymentStatus

data class PassengerTransactionSummary(
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
) {
    companion object {
        fun getEmpty(): PassengerTransactionSummary = PassengerTransactionSummary(
            id = 0,
            passengerRideBookingId = 0,
            customerId = 0,
            paymentMethod = 0,
            transactionDate = "",
            paymentStatus = PaymentStatus.PENDING, // ✅ bukan string
            totalAmount = 0,
            creditUsed = 0,
            paymentProofImg = null,
            createdAt = "",
            updatedAt = ""
        )
    }
}