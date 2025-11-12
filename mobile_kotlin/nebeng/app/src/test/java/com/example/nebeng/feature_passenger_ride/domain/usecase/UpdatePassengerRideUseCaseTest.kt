package com.example.nebeng.feature_passenger_ride.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_ride.data.remote.model.request.UpdatePassengerRideRequest
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.domain.model.PassengerRide
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

// helper agar Mockito.any() tidak bikin NPE di Kotlin
@Suppress("UNCHECKED_CAST")
private fun <T> anyNonNull(): T = any<T>()

class UpdatePassengerRideUseCaseTest {
//    private lateinit var repository: PassengerRideRepository
//    private lateinit var useCase: UpdatePassengerRideUseCase
//
//    @Before
//    fun setUp() {
//        repository = mock(PassengerRideRepository::class.java)
//        useCase = UpdatePassengerRideUseCase(repository)
//    }
//
//    @Test
//    fun `should return success when repository updates ride`() = runBlocking {
//        val fakeRequest = UpdatePassengerRideRequest(
//            vehicleType = "Mobil",
//            departureTerminalId = 2,
//            arrivalTerminalId = 1,
//            departureTime = "2025-10-23T15:00:00.000000Z",
//            seatsAvailable = 4,
//            seatsReserved = 3,
//            pricePerSeat = 37084,
//            commissionPercentage = 11,
//            rideStatus = "selesai"
//        )
//
//        val fakeRide = PassengerRide(
//            id = 3,
//            driverName = "Riiha",
//            vehicleType = "Mobil",
//            departureTerminalName = "Terminal X",
//            arrivalTerminalName = "Terminal Y",
//            departureTime = "2025-10-23T15:00:00.000000Z",
//            pricePerSeat = 37084,
//            seatsAvailable = 4,
//            seatsReserved = 3,
//            commissionPercentage = 11,
//            rideStatus = "selesai",
//            createdAt = "",
//            updatedAt = ""
//        )
//
////        `when`(repository.updatePassengerRide(anyString(), anyInt(), any(UpdatePassengerRideRequest::class.java)))
////            .thenReturn(Result.Success(fakeRide))
//        `when`(
//            repository.updatePassengerRide(anyString(), anyInt(), anyNonNull())
//        ).thenReturn(Result.Success(fakeRide))
//
//        val result = useCase("dummy-token", 3, fakeRequest)
//
//        assertTrue(result is Result.Success)
//        val ride = (result as Result.Success).data
//        assertEquals("Mobil", ride.vehicleType)
//        assertEquals("selesai", ride.rideStatus)
//    }
//
//    @Test
//    fun `should return error when repository fails`() = runBlocking {
////        `when`(repository.updatePassengerRide(anyString(), anyInt(), any(UpdatePassengerRideRequest::class.java)))
////            .thenReturn(Result.Error("Gagal memperbarui passenger ride"))
//        `when`(
//            repository.updatePassengerRide(anyString(), anyInt(), anyNonNull())
//        ).thenReturn(Result.Error("Gagal memperbarui passenger ride"))
//
//        val result = useCase("dummy-token", 3, mock(UpdatePassengerRideRequest::class.java))
//
//        assertTrue(result is Result.Error)
//        assertEquals("Gagal memperbarui passenger ride", (result as Result.Error).message)
//    }
}