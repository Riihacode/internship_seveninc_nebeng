package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBookingFull
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import com.example.nebeng.core.common.Result

class ReadByIdGoodsRideBookingUseCaseTest {
    private lateinit var repository: GoodsRideBookingRepository
    private lateinit var useCase: ReadByDriverIdGoodsRideBookingUseCase

    @Before
    fun setUp() {
        repository = mock(GoodsRideBookingRepository::class.java)
        useCase = ReadByDriverIdGoodsRideBookingUseCase(repository)
    }

    @Test
    fun `should return list of bookings by driver id`() = runTest {
        val token = "fake_token"
        val driverId = 10
        val bookings = listOf(mock(GoodsRideBookingFull::class.java))

        whenever(repository.getGoodsRideBookingByDriverId(token, driverId))
            .thenReturn(flowOf(Result.Success(bookings)))

        val result = useCase(token, driverId).first()
        assertEquals(Result.Success(bookings), result)
    }
}