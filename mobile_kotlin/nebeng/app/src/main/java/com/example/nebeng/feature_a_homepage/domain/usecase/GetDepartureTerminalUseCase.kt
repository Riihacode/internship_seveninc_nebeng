package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import javax.inject.Inject

class GetDepartureTerminalUseCase @Inject constructor(
    private val repository: TerminalRepository
) {
}