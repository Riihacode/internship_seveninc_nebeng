package com.example.nebeng.feature_auth.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_auth.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateLogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Result<String>> {
        return repository.logout(token)
    }
}