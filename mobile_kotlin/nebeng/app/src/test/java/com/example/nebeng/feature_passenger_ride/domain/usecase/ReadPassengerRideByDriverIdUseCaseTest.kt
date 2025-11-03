package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ReadPassengerRideByDriverIdUseCaseTest {
    private lateinit var repository: PassengerRideRepository
    private lateinit var useCase: ReadPassengerRideByDriverIdUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerRideRepository::class.java)
        useCase = ReadPassengerRideByDriverIdUseCase(repository)
    }

    @Test
    fun `should return rides list when repository returns success`() = runBlocking<Unit> {
        // Arrange (siapkan data palsu)
        val fakeRides = listOf(
            PassengerRide(
                id = 1,
                driverName = "Riiha",
                vehicleType = "Mobil",
                departureTerminalName = "Terminal A",
                arrivalTerminalName = "Terminal B",
                departureTime = "2025-11-01 08:00:00",
                seatsAvailable = 4,
                seatsReserved = 1,
                pricePerSeat = 25000,
                commissionPercentage = 10,
                rideStatus = "available",
                createdAt = "2025-11-01T08:00:00.000Z",
                updatedAt = "2025-11-01T08:30:00.000Z"
            ),
            PassengerRide(
                id = 2,
                driverName = "Riiha",
                vehicleType = "Mobil",
                departureTerminalName = "Terminal C",
                arrivalTerminalName = "Terminal B",
                departureTime = "2025-11-01 08:00:00",
                seatsAvailable = 2,
                seatsReserved = 1,
                pricePerSeat = 40000,
                commissionPercentage = 10,
                rideStatus = "available",
                createdAt = "2025-11-01T08:00:00.000Z",
                updatedAt = "2025-11-01T08:30:00.000Z"
            )
        )

        // Mocking: ketika dipanggil, kembalikan data sukses
        `when`(repository.getPassengerRidesByDriverId(anyString(), anyInt()))
            .thenReturn(Result.Success(fakeRides))

        // Act (jalankan use case)
        val result = useCase("dummy-token", 1)

        // Assert
        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals(2, data.size)
        assertEquals("Riiha", data.first().driverName)
        assertEquals("Terminal A", data.first().departureTerminalName)

        // Verifikasi bahwa repository memang dipanggil 1x
        verify(repository, times(1)).getPassengerRidesByDriverId(anyString(), anyInt())
    }

    @Test
    fun `should return error when repository fails`() = runBlocking<Unit> {
        // Mock error
        `when`(repository.getPassengerRidesByDriverId(anyString(), anyInt()))
            .thenReturn(Result.Error("Gagal memuat rides dari driver"))

        val result = useCase("dummy-token", 99)

        assertTrue(result is Result.Error)
        assertEquals("Gagal memuat rides dari driver", (result as Result.Error).message)

        verify(repository, times(1)).getPassengerRidesByDriverId(anyString(), anyInt())
    }
}