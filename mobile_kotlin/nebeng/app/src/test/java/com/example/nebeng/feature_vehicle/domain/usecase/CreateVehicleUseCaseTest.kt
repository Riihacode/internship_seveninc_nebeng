package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_vehicle.data.remote.model.request.CreateVehicleRequest
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CreateVehicleUseCaseTest {

    @Mock private lateinit var repository: VehicleRepository
    private lateinit var useCase: CreateVehicleUseCase

    private val token = "token123"

    private val fakeVehicle = Vehicle(
        id = 1,
        driverId = 10,
        registrationNumber = "AB1234CD",
        registrationYear = "2021",
        registrationExpired = "2025-11-06",
        registrationImg = "stnk.png",
        vehicleName = "Honda PCX",
        vehicleColor = "Hitam",
        vehicleType = VehicleType.MOTOR,
        vehicleImg = "pcx.png",
        vehicleVerified = false,
        vehicleRejectedReason = null
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = CreateVehicleUseCase(repository)
    }

    @Test
    fun `invoke emits Success`() = runTest {
        val request = mock(CreateVehicleRequest::class.java)
        whenever(repository.createVehicle(token, request)).thenReturn(flow {
            emit(Result.Success(fakeVehicle))
        })

        val result = useCase(token, request)
        result.collect {
            assertEquals(Result.Success(fakeVehicle), it)
        }
    }
}