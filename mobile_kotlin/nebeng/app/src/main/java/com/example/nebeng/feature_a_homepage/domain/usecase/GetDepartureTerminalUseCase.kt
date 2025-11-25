package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import com.example.nebeng.feature_terminal.domain.model.Terminal
import com.example.nebeng.feature_terminal.domain.model.TerminalSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDepartureTerminalUseCase @Inject constructor(
    private val repository: TerminalRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<TerminalSummary>> {
        return repository.getTerminalByIdSummary(token, id)
    }
}