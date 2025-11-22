package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import javax.inject.Inject

class GetByIdCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
}