package com.example.nebeng.feature_goods_ride.domain.usecase

import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepository
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import com.example.nebeng.core.utils.RideStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride.data.remote.model.request.UpdateGoodsRideRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateGoodsRideUseCaseTest {
    @Mock private lateinit var repository: GoodsRideRepository
    private lateinit var useCase: UpdateGoodsRideUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = UpdateGoodsRideUseCase(repository)
    }

    @Test
    fun `invoke should emit Success after update`() = runTest {
        val token = "token"
        val id = 1
        val request = UpdateGoodsRideRequest(
            departureTerminalId = 1,
            maxWeight = 200,
            pricePerKg = 7000,
            transportType = "Umum",
            arrivalTerminalId = 2,
            publicTerminalSubtype = "Terminal Bis",
            departureTime = "2025-11-06T10:00:00Z",
            rideStatus = "Selesai"
        )

        val updated = GoodsRide(
            id = id,
            driverId = 5,
            transportType = TransportType.UMUM,
            publicTerminalSubtype = PublicTerminalSubtype.TERMINAL_BIS,
            departureTerminalId = 1,
            arrivalTerminalId = 2,
            departureTime = java.time.LocalDateTime.now(),
            maxWeight = 200,
            weightReserved = 100,
            pricePerKg = 7000,
            commissionPercentage = 15,
            rideStatus = RideStatus.SELESAI,
            createdAt = java.time.LocalDateTime.now(),
            updatedAt = java.time.LocalDateTime.now()
        )

        val flow: Flow<Result<GoodsRide>> = flow {
            emit(Result.Loading)
            emit(Result.Success(updated))
        }

        whenever(repository.updateGoodsRideById(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<GoodsRide>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.last() is Result.Success)
        assertEquals(200, (results.last() as Result.Success).data.maxWeight)
    }
}