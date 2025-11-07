package com.example.nebeng.feature_payment_method.domain.usecase

data class PaymentMethodUseCases(
    val create: CreatePaymentMethodUseCase,
    val readAll: ReadAllPaymentMethodUseCase,
    val readById: ReadByIdPaymentMethodUseCase,
    val update: UpdatePaymentMethodUseCase,
    val delete: DeletePaymentMethodUseCase
)
