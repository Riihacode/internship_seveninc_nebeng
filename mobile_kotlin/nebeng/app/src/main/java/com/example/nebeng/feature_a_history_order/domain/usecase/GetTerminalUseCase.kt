package com.example.nebeng.feature_a_history_order.domain.usecase

import android.util.Log
import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import com.example.nebeng.feature_terminal.domain.model.Terminal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class GetTerminalUseCase @Inject constructor(
    private val repository: TerminalRepository
) {
    suspend operator fun invoke(token: String): Flow<List<Terminal>> = flow {
        repository.getAllTerminal(token).collect { result ->
            when(result) {
                is Result.Success   -> emit(result.data)
                is Result.Error     -> {
                    Log.e("GetTerminalUseCase", "❌ ${result.message}")
                    emit(emptyList())
                }
                else -> {} // abaikan loading
            }
        }
    }
}