package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.remote.model.request.UpdateVehicleRequest
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import com.example.nebeng.core.utils.VehicleType
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class UpdateVehicleUseCaseTest {

    @Mock private lateinit var repository: VehicleRepository
    private lateinit var useCase: UpdateVehicleUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = UpdateVehicleUseCase(repository)
    }

    @Test
    fun `invoke emits Success`() = runTest {
        val token = "token123"
        val id = 1
        val request = mock(UpdateVehicleRequest::class.java)
        val vehicle = Vehicle(1, 10, "AB1234CD", "2021", "2025-11-06", "stnk.png", "Honda PCX", "Hitam", VehicleType.MOTOR, "pcx.png", false, null)

        whenever(repository.updateVehicleById(token, id, request)).thenReturn(flow {
            emit(Result.Success(vehicle))
        })

        val result = useCase(token, id, request)
        result.collect {
            assertEquals(Result.Success(vehicle), it)
        }
    }
}