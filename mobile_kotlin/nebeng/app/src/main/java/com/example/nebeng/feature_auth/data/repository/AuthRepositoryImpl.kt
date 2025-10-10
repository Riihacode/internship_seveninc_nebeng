package com.example.nebeng.feature_auth.data.repository

import com.example.nebeng.feature_auth.data.local.dao.AuthDao
import com.example.nebeng.feature_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.nebeng.core.model.Result
import com.example.nebeng.feature_auth.data.local.entity.toDomain
import com.example.nebeng.feature_auth.data.local.entity.toEntity
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dao: AuthDao
): AuthRepository {
    override fun getAllUser(): Flow<Result<List<Auth>>> = flow {
        emit(Result.Loading)
        try {
            val data = dao.getAllUsers().map { list -> list.map { it.toDomain() } }
            emitAll(data.map { Result.Success(it) })
        } catch(e: Exception) {
            emit(Result.Error(e.message ?: "Gagal ambil data user"))
        }
    }

    override suspend fun register(user: Auth): Result<Auth> {
        return try {
            dao.insertUser(user.toEntity())
            Result.Success(user)
        } catch(e: Exception) {
            Result.Error(e.message ?: "Gagal ambil data user")
        }
    }

    override suspend fun updateUser(user: Auth): Result<Unit> {
        return try {
            dao.updateUser(user.toEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Gagal update User")
        }
    }

    override suspend fun deleteUser(id: Int): Result<Unit> {
        return try {
            dao.deleteUsers(id)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Gagal hapus user")
        }
    }

    override suspend fun login(username: String, password: String): Result<Auth?> {
        return try {
            val user = dao.login(username, password)?.toDomain()
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Gagal login user")
        }
    }
}