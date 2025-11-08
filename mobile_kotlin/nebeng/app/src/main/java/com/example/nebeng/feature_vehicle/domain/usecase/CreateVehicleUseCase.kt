package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.remote.model.request.CreateVehicleRequest
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow

class CreateVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(token: String, request: CreateVehicleRequest): Flow<Result<Vehicle>> {
        return repository.createVehicle(token, request)
    }
}