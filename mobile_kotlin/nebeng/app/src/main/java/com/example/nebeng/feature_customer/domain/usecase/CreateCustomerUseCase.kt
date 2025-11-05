package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.remote.model.request.CreateCustomerRequest
import com.example.nebeng.feature_customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow

class CreateCustomerUseCase @Inject constructor(
  private val repository: CustomerRepository
) {
    suspend operator fun invoke(token: String, request: CreateCustomerRequest): Flow<Result<Customer>> {
        return repository.createCustomer(token, request)
    }
}