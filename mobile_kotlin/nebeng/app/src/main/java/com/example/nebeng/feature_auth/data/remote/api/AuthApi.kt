package com.example.nebeng.feature_auth.data.remote.api

import com.example.nebeng.feature_auth.data.remote.model.request.CreateLoginRequest
import com.example.nebeng.feature_auth.data.remote.model.request.CreateRegisterRequest
import com.example.nebeng.feature_auth.data.remote.model.response.CreateLoginResponse
import com.example.nebeng.feature_auth.data.remote.model.response.CreateLogoutResponse
import com.example.nebeng.feature_auth.data.remote.model.response.CreateRegisterResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

//interface AuthApi {
//    @POST("api/login")
//    suspend fun login(
//        @Body
//        request: LoginRequest
//    ): AuthResponse
//
//    @POST("api/register")
//    suspend fun register(
//        @Body
//        request: RegisterRequest
//    ): AuthResponse
//
//    @POST("api/logout")
//    suspend fun logout(
//        @Header("Authorization")
//        token: String
//    ): LogoutResponse
//}
interface AuthApi {
    @POST("api/login")
    suspend fun login(
        @Body
        request: CreateLoginRequest
    ): CreateLoginResponse

    @POST("api/register")
    suspend fun register(
        @Body
        request: CreateRegisterRequest
    ): CreateRegisterResponse

    @POST("api/logout")
    suspend fun logout(
        @Header("Authorization")
        token: String
    ): CreateLogoutResponse
}