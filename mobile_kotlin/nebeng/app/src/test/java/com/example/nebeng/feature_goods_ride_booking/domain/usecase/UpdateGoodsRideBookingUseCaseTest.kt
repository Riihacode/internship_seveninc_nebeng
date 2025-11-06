package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


class UpdateGoodsRideBookingUseCaseTest {
    private lateinit var repository: GoodsRideBookingRepository
    private lateinit var useCase: UpdateGoodsRideBookingUseCase

    @Before
    fun setUp() {
        repository = mock(GoodsRideBookingRepository::class.java)
        useCase = UpdateGoodsRideBookingUseCase(repository)
    }

    @Test
    fun `should update booking successfully`() = runTest {
        val token = "fake_token"
        val id = 1
        val request = com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.UpdateGoodsRideBookingRequest(
            itemWeight = 25,
            itemDescription = "Updated barang",
            itemImg = "updated.png",
            totalPrice = 18000,
            status = "Diterima"
        )

        val updatedBooking = GoodsRideBooking(
            id = 1,
            goodsRideId = 1,
            customerId = 2,
            itemWeight = 25,
            itemDescription = "Updated barang",
            itemImg = "updated.png",
            totalPrice = 18000,
            status = BookingStatus.DITERIMA,
            createdAt = fakeDate(),
            updatedAt = fakeDate()
        )

        whenever(repository.updateGoodsRideBookingById(token, id, request))
            .thenReturn(flowOf(Result.Success(updatedBooking)))

        val result = useCase(token, id, request).first()
        assertEquals(Result.Success(updatedBooking), result)
    }
}