package com.example.nebeng.feature_a_auth.domain.usecase.support_for_present

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_auth.data.repository.AuthRepository
import com.example.nebeng.feature_a_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAuthUseCase @Inject constructor(
    val repository: AuthRepository
) {
    operator fun invoke(): Flow<Result<List<Auth>>> = repository.getAllUser()
}