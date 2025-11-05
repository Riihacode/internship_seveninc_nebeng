package com.example.nebeng.feature_driver.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_driver.data.repository.DriverRepository
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
class DeleteDriverUseCaseTest {
    @Mock
    private lateinit var repository: DriverRepository
    private lateinit var useCase: DeleteDriverUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = DeleteDriverUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when driver deleted`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }

        whenever(repository.deleteDriver(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Boolean>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertTrue((results.last() as Result.Success).data)
    }
}