package com.example.nebeng.feature_goods_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepository
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import com.example.nebeng.core.utils.RideStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ReadByIdGoodsRideUseCaseTest {
    @Mock private lateinit var repository: GoodsRideRepository
    private lateinit var useCase: ReadByIdGoodsRideUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadByIdGoodsRideUseCase(repository)
    }

    @Test
    fun `invoke should emit goods ride by id`() = runTest {
        val token = "token"
        val id = 1

        val ride = GoodsRide(
            id = id,
            driverId = 7,
            transportType = TransportType.SENDIRI,
            publicTerminalSubtype = PublicTerminalSubtype.TERMINAL_BIS,
            departureTerminalId = 1,
            arrivalTerminalId = 2,
            departureTime = java.time.LocalDateTime.now(),
            maxWeight = 100,
            weightReserved = 20,
            pricePerKg = 6000,
            commissionPercentage = 15,
            rideStatus = RideStatus.PENDING,
            createdAt = java.time.LocalDateTime.now(),
            updatedAt = java.time.LocalDateTime.now()
        )

        val flow: Flow<Result<GoodsRide>> = flow {
            emit(Result.Loading)
            emit(Result.Success(ride))
        }

        whenever(repository.getGoodsRideById(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<GoodsRide>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.last() is Result.Success)
        assertEquals(ride.driverId, (results.last() as Result.Success).data.driverId)
    }
}