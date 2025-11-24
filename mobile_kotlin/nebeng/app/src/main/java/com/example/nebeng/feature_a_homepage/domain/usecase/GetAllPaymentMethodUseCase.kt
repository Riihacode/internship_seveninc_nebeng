package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethodSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPaymentMethodUseCase @Inject constructor(
    private val repository: PaymentMethodRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<List<PaymentMethodSummary>>> {
        return repository.getAllPaymentMethodsSummary(token)
    }
}