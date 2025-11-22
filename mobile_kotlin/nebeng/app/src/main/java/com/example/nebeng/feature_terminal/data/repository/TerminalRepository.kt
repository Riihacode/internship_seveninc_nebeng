package com.example.nebeng.feature_terminal.data.repository

import kotlinx.coroutines.flow.Flow
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_terminal.domain.model.Terminal
import com.example.nebeng.feature_terminal.domain.model.TerminalSummary

interface TerminalRepository {
    suspend fun getAllTerminal(token: String): Flow<Result<List<Terminal>>>

    suspend fun getTerminalById(token: String, id: Int): Flow<Result<Terminal>>


    suspend fun getAllTerminalSummary(token: String): Flow<Result<List<TerminalSummary>>>

    suspend fun getTerminalByIdSummary(token: String, id: Int): Flow<Result<TerminalSummary>>
}