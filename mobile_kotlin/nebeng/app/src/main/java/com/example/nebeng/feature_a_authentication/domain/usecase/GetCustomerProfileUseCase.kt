package com.example.nebeng.feature_a_authentication.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_authentication.domain.mapper.toAuthenticationItem
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import com.example.nebeng.feature_user.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCustomerProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        token: String,
        userId: Int,
        baseAuth: AuthenticationItem? = null
    ): Flow<Result<AuthenticationItem>> {
        return userRepository.getUserByIdSummary(token, userId).map { result ->
            when (result) {
                is Result.Loading -> Result.Loading
                is Result.Error -> Result.Error(result.message)
                is Result.Success -> {
                    val item = result.data.toAuthenticationItem(
                        token = token,
                        base = baseAuth
                    )
                    Result.Success(item)
                }
            }
        }
    }
}