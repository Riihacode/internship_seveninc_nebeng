package com.example.nebeng.feature_a_auth.domain.usecase

import com.example.nebeng.feature_a_auth.data.repository.AuthRepository
import com.example.nebeng.feature_a_auth.domain.model.Auth
import com.example.nebeng.core.common.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Auth?> {
        if (username.isBlank() || password.isBlank()) {
            return Result.Error("Username dan password tidak boleh kosong")
        }

        return repository.login(username, password)
    }
}