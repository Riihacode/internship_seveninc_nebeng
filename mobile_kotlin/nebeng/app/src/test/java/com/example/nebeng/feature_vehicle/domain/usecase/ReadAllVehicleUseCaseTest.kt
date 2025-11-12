package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import com.example.nebeng.core.utils.VehicleType
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals

class ReadAllVehicleUseCaseTest {

    @Mock private lateinit var repository: VehicleRepository
    private lateinit var useCase: ReadAllVehicleUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadAllVehicleUseCase(repository)
    }

    @Test
    fun `invoke emits Success`() = runTest {
        val token = "token123"
        val fakeVehicles = listOf(
            Vehicle(1, 10, "AB1234CD", "2021", "2025-11-06", "stnk.png", "Honda PCX", "Hitam", VehicleType.MOTOR, "pcx.png", false, null)
        )

        whenever(repository.getAllVehicles(token)).thenReturn(flow {
            emit(Result.Success(fakeVehicles))
        })

        val result = useCase(token)
        result.collect {
            assertEquals(Result.Success(fakeVehicles), it)
        }
    }
}