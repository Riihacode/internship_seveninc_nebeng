package com.example.nebeng.feature_terminal.data.repository

import android.util.Log
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_terminal.data.remote.api.TerminalApi
import com.example.nebeng.feature_terminal.data.remote.model.mapper.toDomain
import com.example.nebeng.feature_terminal.data.remote.model.mapper.toSummary
import com.example.nebeng.feature_terminal.domain.model.Terminal
import com.example.nebeng.feature_terminal.domain.model.TerminalSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TerminalRepositoryImpl @Inject constructor(
    private val api: TerminalApi
): TerminalRepository {
    override suspend fun getAllTerminal(token: String): Flow<Result<List<Terminal>>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.getAllTerminal("Bearer $token")
                val terminals = response.data.map { it.toDomain() }
                emit(Result.Success(terminals))
                Log.d("TerminalRepo", "✅ Loaded ${terminals.size} terminals")
            } catch (e: Exception) {
                Log.e("TerminalRepo", "❌ getAll failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while fetching terminal list"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getTerminalById(token: String, id: Int): Flow<Result<Terminal>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.getTerminalById("Bearer $token", id)
                val terminal = response.data.toDomain()
                emit(Result.Success(terminal))
                Log.d("TerminalRepo", "✅ Loaded terminal id=${terminal.id}")
            } catch (e: Exception) {
                Log.e("TerminalRepo", "❌ getById failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while fetching terminal details"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getAllTerminalSummary(token: String): Flow<Result<List<TerminalSummary>>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.getAllTerminal("Bearer $token")
                val terminal = response.data.map { it.toSummary() }
                emit(Result.Success(terminal))
                Log.d("TerminalRepo", "✅ Loaded ${terminal.size} terminals")
            } catch (e: Exception) {
                Log.e("TerminalRepo", "❌ getAll failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while fetching terminal list"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getTerminalByIdSummary(token: String, id: Int): Flow<Result<TerminalSummary>> =
        flow {
            emit(Result.Loading)
            try {
                val response = api.getTerminalById("Bearer $token", id)
                val terminal = response.data.toSummary()
                emit(Result.Success(terminal))
                Log.d("TerminalRepo", "✅ Loaded terminal id=${terminal.id}")
            } catch (e: Exception) {
                Log.e("TerminalRepo", "❌ getById failed: ${e.message}")
                emit(Result.Error(e.message ?: "Unknown error while fetching terminal details"))
            }
        }.flowOn(Dispatchers.IO)
}