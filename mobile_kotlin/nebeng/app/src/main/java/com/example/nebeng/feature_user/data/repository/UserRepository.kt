package com.example.nebeng.feature_user.data.repository

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_user.data.remote.model.request.UpdateUserRequest
import com.example.nebeng.feature_user.domain.model.User
import com.example.nebeng.feature_user.domain.model.UserSummary
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getAllUsers(
        token: String
    ): Flow<Result<List<User>>>

    suspend fun getUserById(
        token: String,
        id: Int
    ): Flow<Result<User>>

    suspend fun updateUserById(
        token: String,
        id: Int,
        request: UpdateUserRequest
    ): Flow<Result<User>>

    suspend fun deleteUserById(
        token: String,
        id: Int
    ): Flow<Result<String>>

    suspend fun getUserByIdSummary(
        token: String,
        id: Int
    ): Flow<Result<UserSummary>>

    suspend fun updateUserByIdSummary(
        token: String,
        id: Int,
        request: UpdateUserRequest
    ): Flow<Result<UserSummary>>
}