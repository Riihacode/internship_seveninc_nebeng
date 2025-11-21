package com.example.nebeng.feature_user.domain.usecase

data class UserUseCases(
    val readAll: ReadAlUserUseCase,
    val readById: ReadByIdUserUseCase,
    val update: UpdateUserUseCase,
    val delete: DeleteUserUseCase
)
