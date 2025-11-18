package com.example.nebeng.feature_user.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_user.data.repository.UserRepository
import com.example.nebeng.feature_user.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAlUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<List<User>>> {
        return repository.getAllUsers(token)
    }
}