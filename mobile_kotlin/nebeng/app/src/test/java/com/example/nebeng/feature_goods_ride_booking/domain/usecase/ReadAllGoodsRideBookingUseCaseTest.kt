package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBookingFull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import com.example.nebeng.core.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first

class ReadAllGoodsRideBookingUseCaseTest {
    private lateinit var repository: GoodsRideBookingRepository
    private lateinit var useCase: ReadAllGoodsRideBookingUseCase

    @Before
    fun setUp() {
        repository = mock(GoodsRideBookingRepository::class.java)
        useCase = ReadAllGoodsRideBookingUseCase(repository)
    }

    @Test
    fun `should return list of all bookings`() = runTest {
        val token = "fake_token"
        val bookings = listOf(
            mock(GoodsRideBookingFull::class.java),
            mock(GoodsRideBookingFull::class.java)
        )

        whenever(repository.getAllGoodsRideBookings(token))
            .thenReturn(flowOf(Result.Success(bookings)))

        val result = useCase(token).first()
        assertEquals(Result.Success(bookings), result)
    }
}