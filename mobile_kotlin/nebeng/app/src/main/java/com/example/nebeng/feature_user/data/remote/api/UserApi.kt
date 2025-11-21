package com.example.nebeng.feature_user.data.remote.api

import com.example.nebeng.feature_user.data.remote.model.request.UpdateUserRequest
import com.example.nebeng.feature_user.data.remote.model.response.DeleteUserResponse
import com.example.nebeng.feature_user.data.remote.model.response.ReadAllUserResponse
import com.example.nebeng.feature_user.data.remote.model.response.ReadByIdUserResponse
import com.example.nebeng.feature_user.data.remote.model.response.UpdateUserResponse
import retrofit2.http.*

interface UserApi {
    @GET("api/users/")
    suspend fun getAllUsers(
        @Header("Authorization") token: String
    ): ReadAllUserResponse

    @GET("api/users/{id}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdUserResponse

    @PUT("api/users/{id}")
    suspend fun updateUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: UpdateUserRequest
    ): UpdateUserResponse

    @DELETE("api/users/{id}")
    suspend fun deleteUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DeleteUserResponse
}