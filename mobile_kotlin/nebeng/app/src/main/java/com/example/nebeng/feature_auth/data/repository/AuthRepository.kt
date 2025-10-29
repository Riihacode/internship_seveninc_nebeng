package com.example.nebeng.feature_auth.data.repository

import com.example.nebeng.feature_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result

interface AuthRepository {
    fun getAllUser(): Flow<Result<List<Auth>>>
    suspend fun register(user: Auth): Result<Auth>
    suspend fun updateUser(user: Auth): Result<Unit>
    suspend fun deleteUser(id: Int): Result<Unit>
    suspend fun login(username: String, password: String): Result<Auth?>
}