package com.example.nebeng.feature_auth.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_auth.data.remote.model.request.CreateLoginRequest
import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        request: CreateLoginRequest
    ): Flow<Result<Auth>> {
        return repository.login(request)
    }
}