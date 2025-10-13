package com.example.nebeng.feature_auth.domain.usecase

import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.model.Result

class GetAllAuthUseCase(
    val repository: AuthRepository
) {
    operator fun invoke(): Flow<Result<List<Auth>>> = repository.getAllUser()
}