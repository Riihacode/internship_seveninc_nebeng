package com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer

data class PaymentMethodCustomer(
    // Tabel Payment Method
    val idPaymentMethod: Int,
    val bankName: String,
    val accountName:String,
    val accountNumber: String
)
