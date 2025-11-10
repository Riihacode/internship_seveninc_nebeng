package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ReadPassengerRideByStatusUseCaseTest {
//    private lateinit var repository: PassengerRideRepository
//    private lateinit var useCase: ReadPassengerRideByStatusUseCase
//
//    @Before
//    fun setUp() {
//        repository = mock(PassengerRideRepository::class.java)
//        useCase = ReadPassengerRideByStatusUseCase(repository)
//    }
//
//    @Test
//    fun `should return rides list by status success`() = runBlocking<Unit> {
//        val fakeRides = listOf(
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
//                rideStatus = "pending",
//                createdAt = "2025-11-01T08:00:00.000Z",
//                updatedAt = "2025-11-01T08:30:00.000Z"
//            )
//        )
//
//        `when`(repository.getPassengerRidesByStatus(anyString(), anyString()))
//            .thenReturn(Result.Success(fakeRides))
//
//        val result = useCase("dummy-token", "pending")
//
//        assertTrue(result is Result.Success)
//        assertEquals("pending", (result as Result.Success).data.first().rideStatus)
//    }
//
//    @Test
//    fun `should return error when repository fails`() = runBlocking<Unit> {
//        `when`(repository.getPassengerRidesByStatus(anyString(), anyString()))
//            .thenReturn(Result.Error("Gagal memuat rides status"))
//
//        val result = useCase("dummy-token", "selesai")
//
//        assertTrue(result is Result.Error)
//        assertEquals("Gagal memuat rides status", (result as Result.Error).message)
//    }
}