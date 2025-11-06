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
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PatchAutoVerifyTrueCustomerUseCaseTest {
    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: PatchAutoVerifyTrueCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = PatchAutoVerifyTrueCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when verification succeeds`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val verifiedCustomer = Customer(
            id = 1,
            userId = 1,
            fullName = "Riiha",
            telephone = "08123",
            fullAddress = "Jogja",
            profileImg = "profile.png",
            verified = true,
            faceImg = "face.png",
            faceWithIdImg = "faceid.png",
            idCardImg = "ktp.png",
            idCardNumber = "123456789",
            idCardFullName = "Riiha",
            idCardBirthdate = "2000-01-01",
            creditAmount = 100_000,
            createdAt = "2025-08-01",
            updatedAt = "2025-08-01"
        )

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Success(verifiedCustomer))
        }

        whenever(repository.patchVerifyTrue(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(true, (results.last() as Result.Success).data.verified)
    }

    @Test
    fun `invoke should emit Error when verification fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Verification failed"))
        }

        whenever(repository.patchVerifyTrue(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
    }
}