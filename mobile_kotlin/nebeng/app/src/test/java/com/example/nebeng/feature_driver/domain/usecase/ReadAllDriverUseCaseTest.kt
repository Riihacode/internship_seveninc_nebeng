package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.feature_driver.domain.model.Driver
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ReadAllDriverUseCaseTest {
    @Mock
    private lateinit var repository: DriverRepository

    private lateinit var useCase: ReadAllDriverUseCase

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        useCase = ReadAllDriverUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ============================================================
    // ✅ TEST: success case
    // ============================================================
    @Test
    fun `invoke should emit Success when repository returns data`() = runTest {
        val token = "fake-token"
        val drivers = listOf(
            Driver(
                id = 1,
                userId = 10,
                fullName = "Tanjirou Kamado",
                telephone = "08123456789",
                fullAddress = "Yogyakarta",
                profileImg = null,
                faceImg = null,
                idCardImg = null,
                faceWithIdImg = null,
                driverLicenseImg = null,
                policeClearanceCertificateImg = null,
                creditScore = 90,
                balance = 50000,
                idCardVerified = true,
                driverLicenseVerified = true,
                policeClearanceVerified = true,
                idCardNumber = "1234567890",
                driverLicenseNumber = "AB123456",
                driverLicenseType = "A",
                idCardBirthdate = "2000-01-01",
                driverLicenseExpired = "2030-01-01",
                policeClearanceCertificateExpired = "2030-01-01",
                createdAt = "2025-01-01",
                updatedAt = "2025-01-01"
            )
        )

        val flow: Flow<Result<List<Driver>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(drivers))
        }

        whenever(repository.getAllDrivers(token)).thenReturn(flow)

        val results = mutableListOf<Result<List<Driver>>>()
        useCase(token).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(drivers.size, (results.last() as Result.Success).data.size)
    }

    // ============================================================
    // ✅ TEST: error case
    // ============================================================
    @Test
    fun `invoke should emit Error when repository throws exception`() = runTest {
        val token = "fake-token"

        val flow: Flow<Result<List<Driver>>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to load"))
        }

        whenever(repository.getAllDrivers(token)).thenReturn(flow)

        val results = mutableListOf<Result<List<Driver>>>()
        useCase(token).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
        assertEquals("Failed to load", (results.last() as Result.Error).message)
    }
}