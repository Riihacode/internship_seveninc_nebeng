package com.example.nebeng.feature_a_history_order.domain.usecase

import android.util.Log
import androidx.collection.emptyFloatObjectMap
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_terminal.domain.model.Terminal
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(token: String, driverId: Int): Flow<List<Vehicle>> = flow {
        repository.getVehiclesByDriverId(token, driverId).collect { result ->
            when(result) {
                is Result.Success   -> emit(result.data)
                is Result.Error     -> {
                    Log.e("GetTerminalUseCase", "❌ ${result.message}")
                    emit(emptyList())
                }
                else -> {} // abaikan loading
            }
        }
    }
}
