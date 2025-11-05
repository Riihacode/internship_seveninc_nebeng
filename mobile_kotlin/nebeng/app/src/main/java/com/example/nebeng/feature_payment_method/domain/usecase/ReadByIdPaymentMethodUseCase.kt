package com.example.nebeng.feature_payment_method.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadByIdPaymentMethodUseCase @Inject constructor(
    private val repository: PaymentMethodRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<PaymentMethod>> {
        return repository.getPaymentMethodById(token, id)
    }
}