package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.remote.model.request.PatchAddCreditAmountCustomerRequest
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
class PatchAddCreditAmountCustomerUseCaseTest {
    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: PatchAddCreditAmountCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = PatchAddCreditAmountCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when credit successfully added`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val request = PatchAddCreditAmountCustomerRequest(amount = 100_000)

        val updatedCustomer = Customer(
            id = 1,
            userId = 1,
            fullName = "Riiha",
            telephone = "08123",
            fullAddress = "Jogja",
            profileImg = "profile.png",
            verified = true,
            faceImg = null,
            faceWithIdImg = null,
            idCardImg = null,
            idCardNumber = "123",
            idCardFullName = "Riiha",
            idCardBirthdate = "2000-01-01",
            creditAmount = 150_000
        )

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Success(updatedCustomer))
        }

        // 🟩 Mock repository to return our fake Flow
        whenever(repository.patchAddCredit(token, id, request)).thenReturn(flow)

        // 🧪 Collect result
        val results = mutableListOf<Result<Customer>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(150_000, (results.last() as Result.Success).data.creditAmount)
    }

    @Test
    fun `invoke should emit Error when add credit fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val request = PatchAddCreditAmountCustomerRequest(amount = 50_000)

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to add credit"))
        }

        whenever(repository.patchAddCredit(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
    }
}