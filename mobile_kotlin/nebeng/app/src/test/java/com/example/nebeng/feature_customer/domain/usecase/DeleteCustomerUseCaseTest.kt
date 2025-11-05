package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteCustomerUseCaseTest {
    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: DeleteCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = DeleteCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when customer is deleted successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }

        // Mock repository response
        whenever(repository.deleteCustomer(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Boolean>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(true, (results.last() as Result.Success).data)
    }

    @Test
    fun `invoke should emit Error when deletion fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to delete customer"))
        }

        whenever(repository.deleteCustomer(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Boolean>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
    }
}