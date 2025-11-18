package com.example.nebeng.feature_auth.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_authentication.data.remote.model.LoginRequest
import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.feature_auth.data.remote.model.request.CreateLoginRequest
import com.example.nebeng.feature_auth.data.remote.model.request.CreateRegisterRequest
import com.example.nebeng.feature_auth.domain.model.AuthSummary
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(
        request: CreateLoginRequest
    ): Flow<Result<Auth>>

    suspend fun register(
        request: CreateRegisterRequest
    ): Flow<Result<Auth>>

    suspend fun logout(
        token: String
    ): Flow<Result<String>>

    suspend fun loginSummary(
        request: CreateLoginRequest
    ): Flow<Result<AuthSummary>>

    suspend fun registerSummary(
        request: CreateRegisterRequest
    ): Flow<Result<AuthSummary>>
}