package com.example.nebeng.feature_a_auth.domain.usecase

import com.example.nebeng.feature_a_auth.domain.usecase.support_for_present.GetAllAuthUseCase

data class AuthUseCases(
    val getAllAuth : GetAllAuthUseCase,
    val createAuth: CreateAuthUseCase,
    val updateAuth: UpdateAuthUseCase,
    val deleteAuth: DeleteAuthUseCase,
    val loginAuth: LoginUseCase,
    val logout: LogoutUseCase
)