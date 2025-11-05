package com.example.nebeng.feature_customer.presentation

import com.example.nebeng.feature_customer.domain.model.Customer

data class CustomerUiState(
    val isLoading: Boolean = false,
    val customers: List<Customer> = emptyList(),
    val currentCustomer: Customer? = null,
    val errorMessage: String? = null
)
