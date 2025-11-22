package com.example.nebeng.feature_payment_method.domain.model

import com.example.nebeng.core.utils.PaymentStatus

data class PaymentMethodSummary(
    val id: Int,
    val bankName: String,
    val accountName: String,
    val accountNumber: String
)
