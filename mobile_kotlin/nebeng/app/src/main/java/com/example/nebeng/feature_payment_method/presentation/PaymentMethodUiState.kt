package com.example.nebeng.feature_payment_method.presentation

import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod

data class PaymentMethodUiState(
    val isLoading: Boolean = false,
    val paymentMethods: List<PaymentMethod> = emptyList(),
    val selectedPaymentMethod: PaymentMethod? = null,
    val error: String? = null,
    val successMessage: String? = null
)
