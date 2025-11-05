package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.feature_driver.data.remote.model.request.UpdateDriverRequest
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.feature_driver.domain.model.Driver
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateDriverUseCaseTest {
    @Mock
    private lateinit var repository: DriverRepository
    private lateinit var useCase: UpdateDriverUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = UpdateDriverUseCase(repository)
    }


    @Test
    fun `invoke should emit Success when driver updated successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val request = UpdateDriverRequest(
            profileImg = "profile.png",
            creditScore = 95,
            fullName = "Tanjiro Updated",
            balance = 60000,
            policeClearanceVerified = true,
            driverLicenseVerified = true,
            telephone = "081234",
            fullAddress = "Yogyakarta",
            idCardVerified = true
        )

        val updatedDriver = Driver(
            id, 1, "Tanjiro Updated", "081234", "Yogyakarta",
            "profile.png", null, null, null, null, null, 95, 60000, true, true, true,
            "111", "DL123", "A", "2000", "2030", "2030", "", ""
        )

        val flow: Flow<Result<Driver>> = flow {
            emit(Result.Loading)
            emit(Result.Success(updatedDriver))
        }

        whenever(repository.updateDriver(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<Driver>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals("Tanjiro Updated", (results.last() as Result.Success).data.fullName)
    }
}