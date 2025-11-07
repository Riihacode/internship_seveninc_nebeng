package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.CreateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class CreateGoodsRideBookingUseCaseTest {

    private lateinit var repository: GoodsRideBookingRepository
    private lateinit var useCase: CreateGoodsRideBookingUseCase

    @Before
    fun setUp() {
        repository = mock(GoodsRideBookingRepository::class.java)
        useCase = CreateGoodsRideBookingUseCase(repository)
    }

    @Test
    fun `should return created goods ride booking`() = runTest {
        val token = "fake_token"

        val request = CreateGoodsRideBookingRequest(
            goodsRideId = 1,
            customerId = 2,
            itemWeight = 20,
            itemDescription = "Dokumen berharga",
            itemImg = "item_123.png",
            totalPrice = 15000,
            status = "pending"
        )

        val expectedBooking = GoodsRideBooking(
            id = 1,
            goodsRideId = 1,
            customerId = 2,
            itemWeight = 20,
            itemDescription = "Dokumen berharga",
            itemImg = "item_123.png",
            totalPrice = 15000,
            status = BookingStatus.PENDING,
            createdAt = java.time.LocalDateTime.parse("2025-11-06T00:00:00"),
            updatedAt = java.time.LocalDateTime.parse("2025-11-06T00:00:00")
        )

        // Mock repository return value
        whenever(repository.createGoodsRideBooking(token, request))
            .thenReturn(flowOf(Result.Success(expectedBooking)))

        // Run the use case
        val result = useCase(token, request).first()

        // Verify
        assertEquals(Result.Success(expectedBooking), result)
    }

    @Test
    fun `should return error when repository fails`() = runTest {
        val token = "fake_token"
        val request = CreateGoodsRideBookingRequest(
            goodsRideId = 1,
            customerId = 2,
            itemWeight = 10,
            itemDescription = "Barang rusak",
            itemImg = "broken_item.png",
            totalPrice = 5000,
            status = "pending"
        )

        val errorMessage = "Network error"

        whenever(repository.createGoodsRideBooking(token, request))
            .thenReturn(flowOf(Result.Error(errorMessage)))

        val result = useCase(token, request).first()

        assertEquals(Result.Error(errorMessage), result)
    }
}