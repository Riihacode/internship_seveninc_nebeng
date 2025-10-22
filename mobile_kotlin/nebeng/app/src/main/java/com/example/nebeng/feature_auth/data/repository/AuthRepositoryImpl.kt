package com.example.nebeng.feature_auth.data.repository

import com.example.nebeng.feature_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.nebeng.core.model.Result
import com.example.nebeng.feature_auth.data.remote.api.AuthApi
import com.example.nebeng.feature_auth.data.remote.model.LoginRequest
import com.example.nebeng.feature_auth.data.remote.model.RegisterRequest
import javax.inject.Inject
import javax.inject.Singleton

//
//class AuthRepositoryImpl @Inject constructor(
//    private val dao: AuthDao
//): AuthRepository {
//    override fun getAllUser(): Flow<Result<List<Auth>>> = flow {
//        emit(Result.Loading)
//        try {
//            val data = dao.getAllUsers().map { list -> list.map { it.toDomain() } }
//            emitAll(data.map { Result.Success(it) })
//        } catch(e: Exception) {
//            emit(Result.Error(e.message ?: "Gagal ambil data user"))
//        }
//    }
//
//    override suspend fun register(user: Auth): Result<Auth> {
//        return try {
//            dao.insertUser(user.toEntity())
//            Result.Success(user)
//        } catch(e: Exception) {
//            Result.Error(e.message ?: "Gagal ambil data user")
//        }
//    }
//
//    override suspend fun updateUser(user: Auth): Result<Unit> {
//        return try {
//            dao.updateUser(user.toEntity())
//            Result.Success(Unit)
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Gagal update User")
//        }
//    }
//
//    override suspend fun deleteUser(id: Int): Result<Unit> {
//        return try {
//            dao.deleteUsers(id)
//            Result.Success(Unit)
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Gagal hapus user")
//        }
//    }
//
//    override suspend fun login(username: String, password: String): Result<Auth?> {
//        return try {
//            val user = dao.login(username, password)?.toDomain()
//            Result.Success(user)
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Gagal login user")
//        }
//    }
//}


@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override fun getAllUser(): Flow<Result<List<Auth>>> = flow {
        emit(Result.Loading)
        try {
            // backend kamu belum ada endpoint /users, jadi kosongkan dulu
            emit(Result.Success(emptyList()))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Gagal ambil data user"))
        }
    }

    override suspend fun register(user: Auth): Result<Auth> {
        return try {
            val request = RegisterRequest(
                name = user.username, // sementara pakai username sebagai name
                username = user.username,
                email = user.email ?: "${user.username}@mail.com",
                password = user.password ?: "12345678",
                password_confirmation = user.password ?: "12345678",
                user_type = user.user_type
            )

            val response = api.register(request)
            if (response.success) {
                val data = response.data?.user
                if (data != null) {
                    Result.Success(
                        Auth(
                            id = data.id,
                            name = data.name,
                            username = data.username,
                            email = data.email,
                            user_type = data.user_type,
                            token = response.data.token.orEmpty()
                        )
                    )
                } else Result.Error("User tidak ditemukan di respons register")
            } else {
                Result.Error(response.message)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Gagal register user")
        }
    }

    override suspend fun updateUser(user: Auth): Result<Unit> {
        return Result.Error("Endpoint update user belum tersedia di backend")
    }

    override suspend fun deleteUser(id: Int): Result<Unit> {
        return Result.Error("Endpoint delete user belum tersedia di backend")
    }

    override suspend fun login(username: String, password: String): Result<Auth?> {
        return try {
            val response = api.login(LoginRequest(userIdentifier = username, password = password))
            if (response.success) {
                val data = response.data?.user
                if (data != null) {
                    Result.Success(
                        Auth(
                            id = data.id,
                            name = data.name,
                            username = data.username,
                            email = data.email,
                            user_type = data.user_type,
                            token = response.data.token.orEmpty()
                        )
                    )
                } else Result.Error("User tidak ditemukan")
            } else {
                Result.Error(response.message)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Gagal login user")
        }
    }
}