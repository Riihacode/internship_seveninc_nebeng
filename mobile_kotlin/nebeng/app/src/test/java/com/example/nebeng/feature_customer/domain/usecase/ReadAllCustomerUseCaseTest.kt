package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import com.example.nebeng.feature_customer.domain.model.Customer
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
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ReadAllCustomerUseCaseTest {

    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: ReadAllCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadAllCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when customers are retrieved`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val customers = listOf(
            Customer(
                id = 1, userId = 1, fullName = "Riiha",
                telephone = "08123", fullAddress = "Jogja",
                profileImg = "profile.png", verified = true,
                faceImg = "face.png", faceWithIdImg = "faceid.png",
                idCardImg = "id.png", idCardNumber = "123",
                idCardFullName = "Riiha", idCardBirthdate = "2000-01-01",
                creditAmount = 0,
                createdAt = "2025-08-01",
                updatedAt = "2025-08-01"
            )
        )

        val flow: Flow<Result<List<Customer>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(customers))
        }

        whenever(repository.getAllCustomers(token)).thenReturn(flow)

        val results = mutableListOf<Result<List<Customer>>>()
        useCase(token).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(1, (results.last() as Result.Success).data.size)
    }

    @Test
    fun `invoke should emit Error when repository fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val flow: Flow<Result<List<Customer>>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Network error"))
        }

        whenever(repository.getAllCustomers(token)).thenReturn(flow)

        val results = mutableListOf<Result<List<Customer>>>()
        useCase(token).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
    }
}