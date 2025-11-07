package com.example.nebeng.feature_goods_ride.domain.usecase

import com.example.nebeng.core.utils.PublicTerminalSubtype
import com.example.nebeng.core.utils.TransportType
import com.example.nebeng.feature_goods_ride.data.repository.GoodsRideRepository
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
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
import java.time.LocalDateTime
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.RideStatus

class ReadAllGoodsRideUseCaseTest {
    @Mock
    private lateinit var repository: GoodsRideRepository

    private lateinit var useCase: ReadAllGoodsRideUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadAllGoodsRideUseCase(repository)
    }

    @Test
    fun `invoke should emit list of goods rides`() = runTest {
        val token = "token"
        val rides = listOf(
            GoodsRide(
                1, 5, TransportType.SENDIRI, PublicTerminalSubtype.BANDARA, 1, 2,
                LocalDateTime.now(), 100, 0, 5000, 10, RideStatus.PENDING,
                LocalDateTime.now(), LocalDateTime.now()
            ),
            GoodsRide(2, 6, TransportType.UMUM, PublicTerminalSubtype.STASIUN_KERETA, 3, 4,
                java.time.LocalDateTime.now(), 200, 10, 7000, 15, RideStatus.DALAM_PERJALANAN,
                java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
            )
        )

        val flow: Flow<Result<List<GoodsRide>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(rides))
        }

        whenever(repository.getAllGoodsRides(token)).thenReturn(flow)

        val results = mutableListOf<Result<List<GoodsRide>>>()
        useCase(token).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(2, (results.last() as Result.Success).data.size)
    }
}