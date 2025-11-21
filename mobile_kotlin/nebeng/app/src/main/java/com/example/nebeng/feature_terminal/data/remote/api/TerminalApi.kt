package com.example.nebeng.feature_terminal.data.remote.api

import com.example.nebeng.feature_terminal.data.remote.model.response.ReadAllTerminalResponse
import com.example.nebeng.feature_terminal.data.remote.model.response.ReadByIdTerminalResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TerminalApi {
    @GET("api/terminals/")
    suspend fun getAllTerminal(
        @Header("Authorization") token: String
    ): ReadAllTerminalResponse

    @GET("api/terminals/{id}")
    suspend fun getTerminalById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReadByIdTerminalResponse
}