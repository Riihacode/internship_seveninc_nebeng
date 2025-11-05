package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.feature_driver.data.remote.model.request.CreateDriverRequest
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.feature_driver.domain.model.Driver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.example.nebeng.core.common.Result
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CreateDriverUseCaseTest {
    @Mock
    private lateinit var repository: DriverRepository

    private lateinit var useCase: CreateDriverUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CreateDriverUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when driver created successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val request = CreateDriverRequest(
            creditScore = 90,
            faceImg = "face.png",
            driverLicenseNumber = "AB123",
            driverLicenseImg = "license.png",
            idCardFullname = "Tanjiro",
            policeClearanceCertificateImg = "skck.png",
            telephone = "08123",
            fullAddress = "Yogyakarta",
            faceWithIdImg = "faceid.png",
            idCardVerified = true,
            policeClearanceCertificateExpired = "2030-01-01",
            policeClearanceCertificateNumber = "POL123",
            driverLicenseType = "A",
            profileImg = "profile.png",
            idCardImg = "ktp.png",
            fullName = "Tanjiro Kamado",
            balance = 50000,
            policeClearanceVerified = true,
            userId = 1,
            driverLicenseVerified = true,
            idCardNumber = "123456789",
            driverLicenseExpired = "2030-01-01",
            policeClearanceCertificateFullname = "Tanjiro",
            idCardBirthdate = "2000-01-01"
        )

        val driver = Driver(
            id = 1, userId = 1, fullName = "Tanjiro Kamado",
            telephone = "08123", fullAddress = "Yogyakarta",
            profileImg = "profile.png", faceImg = null,
            idCardImg = null, faceWithIdImg = null,
            driverLicenseImg = null, policeClearanceCertificateImg = null,
            creditScore = 90, balance = 50000,
            idCardVerified = true, driverLicenseVerified = true, policeClearanceVerified = true,
            idCardNumber = "123456789", driverLicenseNumber = "AB123",
            driverLicenseType = "A", idCardBirthdate = "2000-01-01",
            driverLicenseExpired = "2030-01-01", policeClearanceCertificateExpired = "2030-01-01",
            createdAt = "", updatedAt = ""
        )

        val flow: Flow<Result<Driver>> = flow {
            emit(Result.Loading)
            emit(Result.Success(driver))
        }

        whenever(repository.createDriver(token, request)).thenReturn(flow)

        val results = mutableListOf<Result<Driver>>()
        useCase(token, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(driver.fullName, (results.last() as Result.Success).data.fullName)
    }
}