package com.example.nebeng.feature_payment_method.domain.model

data class PaymentMethod(
    val id: Int,
    val passengerRideBookingId: Int,
    val customerId: Int,
    val totalAmount: Int,
    val paymentMethod: Int,
    val paymentStatus: PaymentStatus,
    val creditUsed: Int,
    val transactionDate: String,
    val createdAt: String,
    val updatedAt: String
)
