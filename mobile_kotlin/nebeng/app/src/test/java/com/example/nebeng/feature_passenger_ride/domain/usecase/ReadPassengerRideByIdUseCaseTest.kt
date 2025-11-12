package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ReadPassengerRideByIdUseCaseTest {

//    private lateinit var repository: PassengerRideRepository
//    private lateinit var useCase: ReadPassengerRideByIdUseCase
//
//    @Before
//    fun setUp() {
//        repository = mock(PassengerRideRepository::class.java)
//        useCase = ReadPassengerRideByIdUseCase(repository)
//    }
//
//    @Test
//    fun `should return single ride success`() = runBlocking {
//        val fakeRide = PassengerRide(
//            id = 1,
//            driverName = "Riiha",
//            vehicleType = "Mobil",
//            departureTerminalName = "Terminal A",
//            arrivalTerminalName = "Terminal B",
//            departureTime = "2025-11-01 08:00:00",
//            seatsAvailable = 4,
//            seatsReserved = 1,
//            pricePerSeat = 25000,
//            commissionPercentage = 10,
//            rideStatus = "available",
//            createdAt = "2025-11-01T08:00:00.000Z",
//            updatedAt = "2025-11-01T08:30:00.000Z"
//        )
//
//        `when`(repository.getPassengerRideById(anyString(), anyInt()))
//            .thenReturn(Result.Success(fakeRide))
//
//        val result = useCase("dummy-token", 1)
//
//        assertTrue(result is Result.Success)
//        val ride = (result as Result.Success).data
//        assertEquals(1, ride.id)
//        assertEquals("Riiha", ride.driverName)
//    }
//
//    @Test
//    fun `should return error when repository fails`() = runBlocking {
//        `when`(repository.getPassengerRideById(anyString(), anyInt()))
//            .thenReturn(Result.Error("Ride not found"))
//
//        val result = useCase("dummy-token", 999)
//
//        assertTrue(result is Result.Error)
//        assertEquals("Ride not found", (result as Result.Error).message)
//    }
}
