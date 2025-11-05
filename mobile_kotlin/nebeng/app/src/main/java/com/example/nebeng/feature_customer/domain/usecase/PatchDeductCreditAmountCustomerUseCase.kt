package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.remote.model.request.PatchDeductCreditAmountCustomerRequest
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import com.example.nebeng.feature_customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatchDeductCreditAmountCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(token: String, id: Int, request: PatchDeductCreditAmountCustomerRequest): Flow<Result<Customer>> {
        return repository.patchDeductCredit(token, id, request)
    }
}