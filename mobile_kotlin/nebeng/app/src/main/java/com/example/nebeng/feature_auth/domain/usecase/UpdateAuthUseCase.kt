package com.example.nebeng.feature_auth.domain.usecase

import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.core.common.Result
import javax.inject.Inject

class UpdateAuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: Auth): Result<Unit>{
        return repository.updateUser(user)
    }
}