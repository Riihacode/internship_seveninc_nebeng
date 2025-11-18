package com.example.nebeng.feature_a_authentication.domain.usecase

data class AuthenticationUseCases(
    val loginCustomer: LoginCustomerUseCase,
    val registerCustomer: RegisterCustomerUseCase,
    val getCustomerProfile: GetCustomerProfileUseCase,
    val updateCustomerProfile: UpdateCustomerProfileUseCase,
    val deleteCustomerAccount: DeleteCustomerAccountUseCase
)
