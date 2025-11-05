package com.example.nebeng.feature_passenger_ride_booking.domain.usecase

import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.domain.model.PassengerRideBooking
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ReadAllPassengerRideBookingUseCaseTest {

    private lateinit var repository: PassengerRideBookingRepository
    private lateinit var useCase: ReadAllPassengerRideBookingUseCase

    @Before
    fun setup() {
        repository = mock(PassengerRideBookingRepository::class.java)
        useCase = ReadAllPassengerRideBookingUseCase(repository)
    }

    @Test
    fun `should return list of bookings`() = runTest {
        val bookings = listOf(
            PassengerRideBooking(1, "BK001", 10, 5, 2, 20000, "Pending", "", "", null, null, null, null),
            PassengerRideBooking(2, "BK002", 11, 6, 1, 15000, "Diterima", "", "", null, null, null, null)
        )
        whenever(repository.readAll("token")).thenReturn(flowOf(bookings))
        val result = useCase("token").first()
        assertEquals(2, result.size)
        assertEquals("BK002", result[1].bookingCode)
    }
}