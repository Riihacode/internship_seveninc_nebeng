package com.example.nebeng.feature_auth.domain.usecase

import com.example.nebeng.feature_auth.domain.usecase.support_for_present.GetAllAuthUseCase

data class ProfileUseCases(
    val getAllAuth: GetAllAuthUseCase,
    val updateAuth: UpdateAuthUseCase,
    val deleteAuth: DeleteAuthUseCase,
    val logout: LogoutUseCase
)