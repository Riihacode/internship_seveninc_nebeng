package com.example.nebeng.feature_goods_ride.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.feature_goods_ride.data.remote.model.request.CreateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import com.example.nebeng.feature_goods_ride.domain.usecase.CreateGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.DeleteGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.GoodsRideUseCases
import com.example.nebeng.feature_goods_ride.domain.usecase.ReadAllGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.ReadByIdGoodsRideUseCase
import com.example.nebeng.feature_goods_ride.domain.usecase.UpdateGoodsRideUseCase
import com.example.nebeng.core.utils.RideStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class GoodsRideViewModelTest {
    @Mock private lateinit var createUseCase: CreateGoodsRideUseCase
    @Mock private lateinit var readAllUseCase: ReadAllGoodsRideUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdGoodsRideUseCase
    @Mock private lateinit var updateUseCase: UpdateGoodsRideUseCase
    @Mock private lateinit var deleteUseCase: DeleteGoodsRideUseCase

    private lateinit var useCases: GoodsRideUseCases
    private lateinit var viewModel: GoodsRideViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        useCases = GoodsRideUseCases(
            create = createUseCase,
            readAll = readAllUseCase,
            readById = readByIdUseCase,
            update = updateUseCase,
            delete = deleteUseCase
        )
        viewModel = GoodsRideViewModel(useCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region 🔹 CREATE TEST
    @Test
    fun `create goods ride success updates uiState`() = runTest {
        val token = "token"
        val request = CreateGoodsRideRequest(
            departureTerminalId = 1,
            driverId = 5,
            maxWeight = 100,
            pricePerKg = 5000,
            commissionPercentage = 10,
            transportType = "Sendiri",
            arrivalTerminalId = 2,
            weightReserved = 0,
            departureTime = "2025-11-06T10:00:00Z",
            rideStatus = "Pending"
        )

        val goodsRide = GoodsRide(
            id = 1,
            driverId = 5,
            transportType = TransportType.SENDIRI,
            publicTerminalSubtype = PublicTerminalSubtype.BANDARA,
            departureTerminalId = 1,
            arrivalTerminalId = 2,
            departureTime = LocalDateTime.now(),
            maxWeight = 100,
            weightReserved = 0,
            pricePerKg = 5000,
            commissionPercentage = 10,
            rideStatus = RideStatus.PENDING,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val flow: Flow<Result<GoodsRide>> = flow {
            emit(Result.Loading)
            emit(Result.Success(goodsRide))
        }

        whenever(createUseCase(token, request)).thenReturn(flow)

        viewModel.createGoodsRide(token, request)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.createResult is Result.Success)
        assertEquals(goodsRide.id, (uiState.createResult as Result.Success).data.id)
    }

    // region 🔹 DELETE TEST
    @Test
    fun `delete success updates uiState`() = runTest {
        val token = "token"
        val id = 1
        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }

        whenever(deleteUseCase(token, id)).thenReturn(flow)

        viewModel.deleteGoodsRide(token, id)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.deleteResult is Result.Success)
        assertEquals(true, (uiState.deleteResult as Result.Success).data)
    }
}