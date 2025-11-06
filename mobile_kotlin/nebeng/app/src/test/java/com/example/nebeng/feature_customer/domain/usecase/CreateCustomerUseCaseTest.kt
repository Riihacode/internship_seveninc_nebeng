package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.remote.model.request.CreateCustomerRequest
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import com.example.nebeng.feature_customer.domain.model.Customer
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.String

@OptIn(ExperimentalCoroutinesApi::class)
class CreateCustomerUseCaseTest {

    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: CreateCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CreateCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when customer created successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val request = CreateCustomerRequest(
            faceImg = "face.png",
            idCardFullname = "Riiha",
            verified = true,
            creditAmount = 0,
            telephone = "08123",
            fullAddress = "Jogja",
            faceWithIdImg = "faceid.png",
            profileImg = "profile.png",
            idCardImg = "id.png",
            fullName = "Riiha",
            userId = 1,
            idCardNumber = "123",
            idCardBirthdate = "2000-01-01"
        )

        val customer = Customer(
            id = 1,
            userId = 1,
            fullName = "Riiha",
            telephone = "08123",
            fullAddress = "Jogja",
            profileImg = "profile.png",
            verified = true,
            faceImg = "face.png",
            faceWithIdImg = "faceid.png",
            idCardImg = "id.png",
            idCardNumber = "123",
            idCardFullName = "Riiha",
            idCardBirthdate = "2000-01-01",
            creditAmount = 0,
            createdAt = "2025-08-01",
            updatedAt = "2025-08-01"
        )

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Success(customer))
        }

        // ✅ Tidak pakai any(), supaya tidak NPE
        whenever(repository.createCustomer(token, request)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(customer.fullName, (results.last() as Result.Success).data.fullName)
    }

    @Test
    fun `invoke should emit Error when repository returns failure`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val request = CreateCustomerRequest(
            faceImg = "face.png",
            idCardFullname = "Riiha",
            verified = true,
            creditAmount = 0,
            telephone = "08123",
            fullAddress = "Jogja",
            faceWithIdImg = "faceid.png",
            profileImg = "profile.png",
            idCardImg = "id.png",
            fullName = "Riiha",
            userId = 1,
            idCardNumber = "123",
            idCardBirthdate = "2000-01-01"
        )

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to create customer"))
        }

        whenever(repository.createCustomer(token, request)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
        assertEquals("Failed to create customer", (results.last() as Result.Error).message)
    }
}