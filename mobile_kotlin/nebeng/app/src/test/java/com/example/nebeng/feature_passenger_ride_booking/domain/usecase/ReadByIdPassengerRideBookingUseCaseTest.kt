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

class ReadByIdPassengerRideBookingUseCaseTest {

    private lateinit var repository: PassengerRideBookingRepository
    private lateinit var useCase: ReadByIdPassengerRideBookingUseCase

    @Before
    fun setup() {
        repository = mock(PassengerRideBookingRepository::class.java)
        useCase = ReadByIdPassengerRideBookingUseCase(repository)
    }

    @Test
    fun `should return booking by id`() = runTest {
        val booking = PassengerRideBooking(1, "BK001", 10, 5, 2, 20000, "Pending", "", "", null, null, null, null)
        whenever(repository.readById("token", 1)).thenReturn(flowOf(booking))
        val result = useCase("token", 1).first()
        assertEquals("BK001", result.bookingCode)
    }
}