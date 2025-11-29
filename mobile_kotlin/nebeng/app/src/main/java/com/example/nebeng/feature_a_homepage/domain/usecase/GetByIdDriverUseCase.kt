package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.feature_driver.domain.model.DriverSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetByIdDriverUseCase @Inject constructor(
    private val repository: DriverRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<DriverSummary>> {
        return repository.getDriverByIdSummary(token, id)
    }
}