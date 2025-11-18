package com.example.nebeng.feature_user.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_user.data.remote.api.UserApi
import com.example.nebeng.feature_user.data.remote.model.mapper.toSummary
import com.example.nebeng.feature_user.data.remote.model.mapper.toUser
import com.example.nebeng.feature_user.data.remote.model.request.UpdateUserRequest
import com.example.nebeng.feature_user.domain.model.User
import com.example.nebeng.feature_user.domain.model.UserSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getAllUsers(token: String): Flow<Result<List<User>>> = flow {
        emit(Result.Loading)
        try {
            val res = api.getAllUsers("Bearer $token")
            val mapped = res.data.map { it.toUser() }
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            Log.e("UserRepository", "❌ getAllUsers: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUserById(token: String, id: Int): Flow<Result<User>> = flow {
        emit(Result.Loading)
        try {
            val res = api.getUserById("Bearer $token", id)
//            val mapped = UserMapper.fromDataDto(res.data)
            val mapped = res.data.toUser()
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            Log.e("UserRepository", "❌ getUserById: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUserById(
        token: String,
        id: Int,
        request: UpdateUserRequest
    ): Flow<Result<User>> = flow {
        emit(Result.Loading)
        try {
            val res = api.updateUserById("Bearer $token", id, request)
//            val mapped = UserMapper.fromDataDto(res.data)
            val mapped = res.data.toUser()
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            Log.e("UserRepository", "❌ updateUserById: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteUserById(token: String, id: Int): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val res = api.deleteUserById("Bearer $token", id)
            emit(Result.Success(res.message))
        } catch (e: Exception) {
            Log.e("UserRepository", "❌ deleteUserById: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUserByIdSummary(
        token: String,
        id: Int,
    ): Flow<Result<UserSummary>>  = flow {
        emit(Result.Loading)
        try {
            val res = api.getUserById("Bearer $token", id)
//            val mapped = UserMapper.fromDataDto(res.data)
            val mapped = res.data.toSummary()
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            Log.e("UserRepository", "❌ getUserById: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUserByIdSummary(
        token: String,
        id: Int,
        request: UpdateUserRequest,
    ): Flow<Result<UserSummary>>  = flow {
        emit(Result.Loading)
        try {
            val res = api.updateUserById("Bearer $token", id, request)
//            val mapped = UserMapper.fromDataDto(res.data)
            val mapped = res.data.toSummary()
            emit(Result.Success(mapped))
        } catch (e: Exception) {
            Log.e("UserRepository", "❌ updateUserById: ${e.message}")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}