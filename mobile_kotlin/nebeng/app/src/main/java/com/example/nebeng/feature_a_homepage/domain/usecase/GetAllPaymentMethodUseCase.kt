package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import javax.inject.Inject

class GetAllPaymentMethodUseCase @Inject constructor(
    private val repository: PaymentMethodRepository
) {
}