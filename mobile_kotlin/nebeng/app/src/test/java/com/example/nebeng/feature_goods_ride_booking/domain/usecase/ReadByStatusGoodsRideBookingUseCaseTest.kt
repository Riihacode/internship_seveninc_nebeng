package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.BookingStatus
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

class ReadByStatusGoodsRideBookingUseCaseTest {
    private lateinit var repository: GoodsRideBookingRepository
    private lateinit var useCase: ReadByStatusGoodsRideBookingUseCase

    @Before
    fun setUp() {
        repository = mock(GoodsRideBookingRepository::class.java)
        useCase = ReadByStatusGoodsRideBookingUseCase(repository)
    }

    @Test
    fun `should return list of bookings by status`() = runTest {
        val token = "fake_token"
        val status = BookingStatus.PENDING
        val bookings = listOf(mock(GoodsRideBookingFull::class.java))

        whenever(repository.getGoodsRideBookingByStatus(token, status))
            .thenReturn(flowOf(Result.Success(bookings)))

        val result = useCase(token, status).first()
        assertEquals(Result.Success(bookings), result)
    }
}