package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_customer.domain.model.CustomerSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetByIdCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<CustomerSummary>> {
        return repository.getCustomerByIdSummary(token, id)
    }
}