package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Boolean>> {
        return repository.deleteCustomer(token, id)
    }
}