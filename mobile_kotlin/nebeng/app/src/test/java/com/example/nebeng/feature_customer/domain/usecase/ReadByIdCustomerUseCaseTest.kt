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
class ReadByIdCustomerUseCaseTest {

    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: ReadByIdCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadByIdCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when customer found`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val customer = Customer(
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

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Success(customer))
        }

        whenever(repository.getCustomerById(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals("Riiha", (results.last() as Result.Success).data.fullName)
    }

    @Test
    fun `invoke should emit Error when repository fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Not found"))
        }

        whenever(repository.getCustomerById(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
    }
}