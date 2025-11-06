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

class ReadByDriverIdGoodsRideBookingUseCaseTest {
    private lateinit var repository: GoodsRideBookingRepository
    private lateinit var useCase: ReadByIdGoodsRideBookingUseCase

    @Before
    fun setUp() {
        repository = mock(GoodsRideBookingRepository::class.java)
        useCase = ReadByIdGoodsRideBookingUseCase(repository)
    }

    @Test
    fun `should return booking by id`() = runTest {
        val token = "fake_token"
        val bookingId = 1
        val expected = mock(GoodsRideBookingFull::class.java)

        whenever(repository.getGoodsRideBookingById(token, bookingId))
            .thenReturn(flowOf(Result.Success(expected)))

        val result = useCase(token, bookingId).first()
        assertEquals(Result.Success(expected), result)
    }
}