package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.feature_driver.data.remote.model.request.CreateDriverRequest
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver.domain.model.Driver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateDriverUseCase @Inject constructor(
    private val repository: DriverRepository
) {
    suspend operator fun invoke(token: String, request: CreateDriverRequest): Flow<Result<Driver>> {
        return repository.createDriver(token, request)
    }
}