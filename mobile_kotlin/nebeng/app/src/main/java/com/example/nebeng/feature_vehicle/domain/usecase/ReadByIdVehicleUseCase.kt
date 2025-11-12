package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadByIdVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Vehicle>> {
        return repository.getVehicleById(token, id)
    }
}