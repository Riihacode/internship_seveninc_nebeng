package com.example.nebeng.feature_customer.domain.usecase

data class CustomerUseCases(
    val create                  : CreateCustomerUseCase,
    val readAll                 : ReadAllCustomerUseCase,
    val readById                : ReadByIdCustomerUseCase,
    val readByUserId            : ReadByUserIdCustomerUseCase,
    val update                  : UpdateCustomerUseCase,
    val patchAddCreditAmount    : PatchAddCreditAmountCustomerUseCase,
    val patchAutoVerifyTrue     : PatchAutoVerifyTrueCustomerUseCase,
    val patchDeductCreditAmount : PatchDeductCreditAmountCustomerUseCase,
    val delete                  : DeleteCustomerUseCase
)