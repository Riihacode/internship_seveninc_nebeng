package com.example.nebeng.feature_vehicle.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_vehicle.data.remote.model.request.CreateVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.PatchVerifyFalseByIdVehicleRequest
import com.example.nebeng.feature_vehicle.data.remote.model.request.UpdateVehicleRequest
import com.example.nebeng.feature_vehicle.domain.model.Vehicle
import com.example.nebeng.feature_vehicle.domain.usecase.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class VehicleViewModelTest {

    private lateinit var viewModel: VehicleViewModel

    @Mock private lateinit var createUseCase: CreateVehicleUseCase
    @Mock private lateinit var readAllUseCase: ReadAllVehicleUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdVehicleUseCase
    @Mock private lateinit var readByDriverIdUseCase: ReadByDriverIdVehicleUseCase
    @Mock private lateinit var updateUseCase: UpdateVehicleUseCase
    @Mock private lateinit var patchVerifyTrueUseCase: PatchVerifyTrueVehicleUseCase
    @Mock private lateinit var patchVerifyFalseUseCase: PatchVerifyFalseVehicleUseCase
    @Mock private lateinit var deleteUseCase: DeleteVehicleUseCase

    private val testDispatcher = StandardTestDispatcher()

    private val token = "token123"
    private val id = 1

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
        Dispatchers.setMain(testDispatcher)
        val useCases = VehicleUseCases(
            create = createUseCase,
            readAll = readAllUseCase,
            readById = readByIdUseCase,
            readByDriverId = readByDriverIdUseCase,
            update = updateUseCase,
            patchVerifyTrue = patchVerifyTrueUseCase,
            patchVerifyFalse = patchVerifyFalseUseCase,
            delete = deleteUseCase
        )
        viewModel = VehicleViewModel(useCases)
    }

    /* ============================================================
       🔹 Test getAllVehicles()
       ============================================================ */
    @Test
    fun `getAllVehicles emits Success updates list`() = runTest(testDispatcher) {
        val fakeList = listOf(fakeVehicle)
        whenever(readAllUseCase(token)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeList))
        })

        viewModel.getAllVehicles(token)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(fakeList, uiState.vehicles)
        assertEquals(false, uiState.isLoading)
        assertEquals(null, uiState.error)
    }

    @Test
    fun `getAllVehicles emits Error updates error`() = runTest(testDispatcher) {
        whenever(readAllUseCase(token)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to fetch vehicles"))
        })

        viewModel.getAllVehicles(token)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Failed to fetch vehicles", uiState.error)
        assertEquals(false, uiState.isLoading)
    }

    /* ============================================================
       🔹 Test createVehicle()
       ============================================================ */
    @Test
    fun `createVehicle emits Success updates state`() = runTest(testDispatcher) {
        val request = CreateVehicleRequest(
            data = com.example.nebeng.feature_vehicle.data.remote.model.dto.DataDto(
                driverId = 10,
                vehicleColor = "Hitam",
                registrationNumber = "AB1234CD",
                registrationExpired = "2025-11-06",
                vehicleType = "Motor",
                vehicleVerified = false,
                createdAt = "2025-11-06",
                vehicleImg = "pcx.png",
                registrationYear = 2021,
                updatedAt = "2025-11-06",
                registrationImg = "stnk.png",
                vehicleRejectedReason = "",
                id = 1,
                vehicleName = "Honda PCX"
            )
        )

        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeVehicle))
        })

        viewModel.createVehicle(token, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.isCreated)
        assertEquals(fakeVehicle, uiState.createdVehicle)
    }

    @Test
    fun `createVehicle emits Error updates error`() = runTest(testDispatcher) {
        val request = mock(CreateVehicleRequest::class.java)
        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to create vehicle"))
        })

        viewModel.createVehicle(token, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Failed to create vehicle", uiState.error)
        assertEquals(false, uiState.isCreated)
    }

    /* ============================================================
       🔹 Test updateVehicle()
       ============================================================ */
    @Test
    fun `updateVehicle emits Success sets isUpdated true`() = runTest(testDispatcher) {
        val request = mock(UpdateVehicleRequest::class.java)
        whenever(updateUseCase(token, id, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeVehicle))
        })

        viewModel.updateVehicle(token, id, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.isUpdated)
    }

    /* ============================================================
       🔹 Test verifyVehicleTrue()
       ============================================================ */
    @Test
    fun `verifyVehicleTrue emits Success sets isVerifiedTrue`() = runTest(testDispatcher) {
        whenever(patchVerifyTrueUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeVehicle))
        })

        viewModel.verifyVehicleTrue(token, id)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.isVerifiedTrue)
    }

    /* ============================================================
       🔹 Test verifyVehicleFalse()
       ============================================================ */
    @Test
    fun `verifyVehicleFalse emits Success sets isVerifiedFalse`() = runTest(testDispatcher) {
        val request = PatchVerifyFalseByIdVehicleRequest("STNK tidak valid")
        whenever(patchVerifyFalseUseCase(token, id, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeVehicle))
        })

        viewModel.verifyVehicleFalse(token, id, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.isVerifiedFalse)
    }

    /* ============================================================
       🔹 Test deleteVehicle()
       ============================================================ */
    @Test
    fun `deleteVehicle emits Success sets isDeleted true`() = runTest(testDispatcher) {
        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success("Deleted successfully"))
        })

        viewModel.deleteVehicle(token, id)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.isDeleted)
        assertEquals(null, uiState.error)
    }

    @Test
    fun `deleteVehicle emits Error updates error`() = runTest(testDispatcher) {
        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to delete vehicle"))
        })

        viewModel.deleteVehicle(token, id)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Failed to delete vehicle", uiState.error)
        assertEquals(false, uiState.isDeleted)
    }
}
