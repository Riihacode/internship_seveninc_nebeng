package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteDriverUseCase @Inject constructor(
    private val repository: DriverRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Boolean>> {
        return repository.deleteDriver(token, id)
    }
}