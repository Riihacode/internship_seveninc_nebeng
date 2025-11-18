package com.example.nebeng.feature_user.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_user.data.remote.model.request.UpdateUserRequest
import com.example.nebeng.feature_user.data.repository.UserRepository
import com.example.nebeng.feature_user.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(token: String, id: Int, request: UpdateUserRequest): Flow<Result<User>> {
        return repository.updateUserById(token, id, request)
    }
}