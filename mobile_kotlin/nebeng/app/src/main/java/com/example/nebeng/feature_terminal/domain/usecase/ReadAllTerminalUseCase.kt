package com.example.nebeng.feature_terminal.domain.usecase

import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import com.example.nebeng.feature_terminal.domain.model.Terminal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class ReadAllTerminalUseCase @Inject constructor(
    private val repository: TerminalRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<List<Terminal>>> {
        return repository.getAllTerminal(token)
    }
}
