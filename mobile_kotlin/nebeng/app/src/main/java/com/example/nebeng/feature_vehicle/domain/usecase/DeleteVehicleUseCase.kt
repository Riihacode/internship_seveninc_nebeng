package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<String>> {
        return repository.deleteVehicleById(token, id)
    }
}