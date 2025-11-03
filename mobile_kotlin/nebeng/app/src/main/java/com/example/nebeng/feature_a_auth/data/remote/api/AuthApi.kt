package com.example.nebeng.feature_a_auth.data.remote.api

import com.example.nebeng.feature_a_auth.data.remote.model.AuthResponse
import com.example.nebeng.feature_a_auth.data.remote.model.LoginRequest
import com.example.nebeng.feature_a_auth.data.remote.model.LogoutResponse
import com.example.nebeng.feature_a_auth.data.remote.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("api/login")
    suspend fun login(
        @Body
        request: LoginRequest
    ): AuthResponse

    @POST("api/register")
    suspend fun register(
        @Body
        request: RegisterRequest
    ): AuthResponse

    @POST("api/logout")
    suspend fun logout(
        @Header("Authorization")
        token: String
    ): LogoutResponse
}