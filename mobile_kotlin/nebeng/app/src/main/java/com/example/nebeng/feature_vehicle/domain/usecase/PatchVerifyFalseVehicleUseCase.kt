package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.remote.model.request.PatchVerifyFalseByIdVehicleRequest
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatchVerifyFalseVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(token: String, id: Int, request: PatchVerifyFalseByIdVehicleRequest): Flow<Result<Vehicle>> {
        return repository.patchVerifyFalseVehicleById(token, id, request)
    }
}