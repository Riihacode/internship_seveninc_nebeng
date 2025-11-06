package com.example.nebeng.feature_goods_ride.domain.usecase

import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.feature_goods_ride.data.remote.model.request.CreateGoodsRideRequest
import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepository
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import com.example.nebeng.core.utils.RideStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import com.example.nebeng.core.common.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class CreateGoodsRideUseCaseTest {
    @Mock
    private lateinit var repository: GoodsRideRepository

    private lateinit var useCase: CreateGoodsRideUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CreateGoodsRideUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when goods ride created successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token123"
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
            publicTerminalSubtype = PublicTerminalSubtype.TERMINAL_BIS,
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

        whenever(repository.createGoodsRide(token, request)).thenReturn(flow)

        val results = mutableListOf<Result<GoodsRide>>()
        useCase(token, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(goodsRide.id, (results.last() as Result.Success).data.id)
    }
}