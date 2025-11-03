package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.CreatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString

// helper agar Mockito.any() tidak bikin NPE di Kotlin
@Suppress("UNCHECKED_CAST")
private fun <T> anyNonNull(): T = any<T>()

class CreatePassengerRideUseCaseTest {

    private lateinit var repository: PassengerRideRepository
    private lateinit var useCase: CreatePassengerRideUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerRideRepository::class.java)
        useCase = CreatePassengerRideUseCase(repository)
    }

    @Test
    fun `should return success when repository creates ride`() = runBlocking {
        val fakeRequest = CreatePassengerRideRequest(
            driverId = 1,
            vehicleType = "Mobil",
            departureTerminalId = 5,
            arrivalTerminalId = 2,
            departureTime = "2025-10-23 07:30:00",
            seatsAvailable = 4,
            seatsReserved = 1,
            pricePerSeat = 30000,
            commissionPercentage = 10,
            rideStatus = "dalam_perjalanan"
        )

        val fakeRide = PassengerRide(
            id = 3,
            driverName = "Riiha",
            vehicleType = "Mobil",
            departureTerminalName = "Terminal A",
            arrivalTerminalName = "Terminal B",
            departureTime = "2025-10-23 07:30:00",
            pricePerSeat = 30000,
            seatsAvailable = 4,
            seatsReserved = 1,
            commissionPercentage = 10,
            rideStatus = "pending",
            createdAt = "",
            updatedAt = ""
        )

//        `when`(repository.createPassengerRide(anyString(), any(CreatePassengerRideRequest::class.java)))
//            .thenReturn(Result.Success(fakeRide))
        `when`(repository.createPassengerRide(anyString(), anyNonNull())).thenReturn(Result.Success(fakeRide))


        val result = useCase("dummy-token", fakeRequest)

        assertTrue(result is Result.Success)
        val ride = (result as Result.Success).data
        assertEquals(3, ride.id)
        assertEquals("Mobil", ride.vehicleType)
    }

    @Test
    fun `should return error when repository fails`() = runBlocking {
        // Mock behavior dengan matcher valid
//        `when`(repository.createPassengerRide(anyString(), any(CreatePassengerRideRequest::class.java)))
//            .thenReturn(Result.Error("Gagal membuat passenger ride baru"))
        `when`(repository.createPassengerRide(anyString(), anyNonNull())).thenReturn(Result.Error("Gagal membuat passenger ride baru"))

        val fakeRequest = CreatePassengerRideRequest(
            driverId = 1,
            vehicleType = "Mobil",
            departureTerminalId = 5,
            arrivalTerminalId = 2,
            departureTime = "2025-10-23 07:30:00",
            seatsAvailable = 4,
            seatsReserved = 1,
            pricePerSeat = 30000,
            commissionPercentage = 10,
            rideStatus = "available"
        )

        val result = useCase("dummy-token", fakeRequest)

        assertTrue(result is Result.Error)
        assertEquals("Gagal membuat passenger ride baru", (result as Result.Error).message)
    }

}
