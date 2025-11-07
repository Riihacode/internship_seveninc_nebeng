package com.example.nebeng.feature_goods_transaction.domain.model

import com.example.nebeng.core.utils.PaymentStatus

data class GoodsTransaction(
    val id: Int,
    val goodsRideBookingId: Int,
    val customerId: Int,
    val totalAmount: Int,
    val paymentMethodId: Int,
    val paymentProofImg: String?,
    val paymentStatus: PaymentStatus,
    val creditUsed: Int,
    val transactionDate: String,
    val createdAt: String,
    val updatedAt: String,

    val transactionCode: String,
)
