package com.example.nebeng.feature_vehicle.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class DeleteVehicleUseCaseTest {

    @Mock private lateinit var repository: VehicleRepository
    private lateinit var useCase: DeleteVehicleUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = DeleteVehicleUseCase(repository)
    }

    @Test
    fun `invoke emits Success`() = runTest {
        val token = "token123"
        val id = 1
        whenever(repository.deleteVehicleById(token, id)).thenReturn(flow {
            emit(Result.Success("Deleted successfully"))
        })

        val result = useCase(token, id)
        result.collect {
            assertEquals(Result.Success("Deleted successfully"), it)
        }
    }
}