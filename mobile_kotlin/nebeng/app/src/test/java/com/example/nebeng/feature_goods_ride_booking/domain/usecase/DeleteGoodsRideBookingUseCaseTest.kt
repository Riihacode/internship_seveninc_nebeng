package com.example.nebeng.feature_goods_ride_booking.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride_booking.data.repository.GoodsRideBookingRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class DeleteGoodsRideBookingUseCaseTest {
    private lateinit var repository: GoodsRideBookingRepository
    private lateinit var useCase: DeleteGoodsRideBookingUseCase

    @Before
    fun setUp() {
        repository = mock(GoodsRideBookingRepository::class.java)
        useCase = DeleteGoodsRideBookingUseCase(repository)
    }

    @Test
    fun `should delete booking successfully`() = runTest {
        val token = "fake_token"
        val id = 1
        whenever(repository.deleteGoodsRideBookingById(token, id))
            .thenReturn(flowOf(Result.Success("Deleted successfully")))

        val result = useCase(token, id).first()
        assertEquals(Result.Success("Deleted successfully"), result)
    }
}