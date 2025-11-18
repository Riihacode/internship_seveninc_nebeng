package com.example.nebeng.feature_auth.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_authentication.data.remote.model.LoginRequest
import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.feature_auth.data.remote.api.AuthApi
import com.example.nebeng.feature_auth.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_auth.data.remote.model.mapper.toSummary
import com.example.nebeng.feature_auth.data.remote.model.request.CreateLoginRequest
import com.example.nebeng.feature_auth.data.remote.model.request.CreateRegisterRequest
import com.example.nebeng.feature_auth.domain.model.AuthSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        request: CreateLoginRequest
    ): Flow<Result<Auth>> = flow {
        emit(Result.Loading)
        try {
            val response = api.login(request)

            if (!response.success) {
                emit(Result.Error(response.message))
                return@flow
            }

//            val auth = AuthMapper.fromLogin(response.data)
            val auth = response.data.toDomain()
            emit(Result.Success(auth))
        } catch (e: Exception) {
            Log.e("AuthRepository", "❌ login: ${e.message}", e)
            emit(Result.Error(e.message ?: "Unknown error while logging in"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun register(
        request: CreateRegisterRequest
    ): Flow<Result<Auth>> = flow {
        emit(Result.Loading)
        try {
            val response = api.register(request)

            if (!response.success) {
                emit(Result.Error(response.message))
                return@flow
            }

//            val auth = AuthMapper.fromRegister(response.data)
            val auth = response.data.toDomain()
            emit(Result.Success(auth))
        } catch (e: Exception) {
            Log.e("AuthRepository", "❌ register: ${e.message}", e)
            emit(Result.Error(e.message ?: "Unknown error while registering"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun logout(
        token: String
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = api.logout("Bearer $token")

            if (!response.success) {
                emit(Result.Error(response.message))
                return@flow
            }

            emit(Result.Success(response.message))
        } catch (e: Exception) {
            Log.e("AuthRepository", "❌ logout: ${e.message}", e)
            emit(Result.Error(e.message ?: "Unknown error while logging out"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loginSummary(request: CreateLoginRequest): Flow<Result<AuthSummary>> = flow {
        emit(Result.Loading)
        try {
            val response = api.login(request)

            if (!response.success) {
                emit(Result.Error(response.message))
                return@flow
            }

//            val auth = AuthMapper.fromLogin(response.data)
            val auth = response.data.toSummary()
            emit(Result.Success(auth))
        } catch (e: Exception) {
            Log.e("AuthRepository", "❌ login: ${e.message}", e)
            emit(Result.Error(e.message ?: "Unknown error while logging in"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun registerSummary(request: CreateRegisterRequest): Flow<Result<AuthSummary>> = flow {
        emit(Result.Loading)
        try {
            val response = api.register(request)

            if (!response.success) {
                emit(Result.Error(response.message))
                return@flow
            }

//            val auth = AuthMapper.fromRegister(response.data)
            val auth = response.data.toSummary()
            emit(Result.Success(auth))
        } catch (e: Exception) {
            Log.e("AuthRepository", "❌ register: ${e.message}", e)
            emit(Result.Error(e.message ?: "Unknown error while registering"))
        }
    }.flowOn(Dispatchers.IO)
}