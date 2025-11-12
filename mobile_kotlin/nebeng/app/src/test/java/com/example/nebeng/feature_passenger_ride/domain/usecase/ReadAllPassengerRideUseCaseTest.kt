package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ReadAllPassengerRideUseCaseTest {

//    private lateinit var repository: PassengerRideRepository
//    private lateinit var useCase: ReadPassengerRideAllUseCase
//
//    @Before
//    fun setUp() {
//        repository = mock(PassengerRideRepository::class.java)
//        useCase = ReadPassengerRideAllUseCase(repository)
//    }
//
//    @Test
//    fun `should return success when repository returns data`() = runBlocking {
//        val fakeList = listOf(
//            PassengerRide(
//                id = 1,
//                driverName = "Riiha",
//                vehicleType = "Mobil",
//                departureTerminalName = "Terminal A",
//                arrivalTerminalName = "Terminal B",
//                departureTime = "2025-11-01 08:00:00",
//                seatsAvailable = 4,
//                seatsReserved = 1,
//                pricePerSeat = 25000,
//                commissionPercentage = 10,
//                rideStatus = "available",
//                createdAt = "2025-11-01T08:00:00.000Z",
//                updatedAt = "2025-11-01T08:30:00.000Z"
//            )
//        )
//
//        `when`(repository.getAllPassengerRides(anyString()))
//            .thenReturn(Result.Success(fakeList))
//
//        // when
//        val result = useCase("dummy-token")
//
//        // then
//        assertTrue(result is Result.Success)
//        val data = (result as Result.Success).data
//        assertEquals(1, data.size)
//        assertEquals("Riiha", data[0].driverName)
//    }
//
//    @Test
//    fun `should return error when repository throws exception`() = runBlocking {
//        // given
//        `when`(repository.getAllPassengerRides(anyString()))
//            .thenReturn(Result.Error("Network error"))
//
//        // when
//        val result = useCase("dummy-token")
//
//        // then
//        assertTrue(result is Result.Error)
//        val message = (result as Result.Error).message
//        assertEquals("Network error", message)
//    }
}