package com.example.nebeng.feature_a_authentication.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_user.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCustomerAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        token: String,
        userId: Int
    ): Flow<Result<String>> {
        // Langsung delegasi ke UserRepository
        return userRepository.deleteUserById(token, userId)
    }
}