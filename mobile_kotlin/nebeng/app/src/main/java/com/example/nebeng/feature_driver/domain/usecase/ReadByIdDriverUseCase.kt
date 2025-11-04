package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.feature_driver.domain.model.Driver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.nebeng.core.common.Result

class ReadByIdDriverUseCase @Inject constructor(
    private val repository: DriverRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Driver>> {
        return repository.getDriverById(token, id)
    }
}