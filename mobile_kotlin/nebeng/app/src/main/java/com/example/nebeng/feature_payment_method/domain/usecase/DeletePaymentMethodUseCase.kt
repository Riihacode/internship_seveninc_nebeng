package com.example.nebeng.feature_payment_method.domain.usecase

import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class DeletePaymentMethodUseCase @Inject constructor(
    private val repository: PaymentMethodRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Boolean>> {
        return repository.deletePaymentMethod(token, id)
    }
}