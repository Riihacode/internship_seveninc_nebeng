package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.feature_driver.domain.model.Driver
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ReadByIdDriverUseCaseTest {
    @Mock
    private lateinit var repository: DriverRepository
    private lateinit var useCase: ReadByIdDriverUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadByIdDriverUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when driver found`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val driver = Driver(1, 1, "Tanjiro", "0812", "Yogya", null, null, null, null, null, null,
            90, 10000, true, true, true, "111", "DL123", "A", "2000", "2030", "2030", "", "")

        val flow: Flow<Result<Driver>> = flow {
            emit(Result.Loading)
            emit(Result.Success(driver))
        }

        whenever(repository.getDriverById(token, 1)).thenReturn(flow)

        val results = mutableListOf<Result<Driver>>()
        useCase(token, 1).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(driver.fullName, (results.last() as Result.Success).data.fullName)
    }
}