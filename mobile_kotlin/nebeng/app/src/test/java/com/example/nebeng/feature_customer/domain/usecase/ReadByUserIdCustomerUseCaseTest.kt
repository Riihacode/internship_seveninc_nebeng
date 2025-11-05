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
class ReadByUserIdCustomerUseCaseTest {
    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: ReadByUserIdCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadByUserIdCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when customers retrieved by userId`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val userId = 1

        val customers = listOf(
            Customer(
                id = 1,
                userId = 1,
                fullName = "Riiha Kamado",
                telephone = "08123",
                fullAddress = "Yogyakarta",
                profileImg = "profile1.png",
                verified = true,
                faceImg = "face1.png",
                faceWithIdImg = null,
                idCardImg = "ktp1.png",
                idCardNumber = "123456789",
                idCardFullName = "Riiha Kamado",
                idCardBirthdate = "2000-01-01",
                creditAmount = 100_000
            ),
            Customer(
                id = 2,
                userId = 1,
                fullName = "Nezuko Kamado",
                telephone = "08124",
                fullAddress = "Yogyakarta",
                profileImg = "profile2.png",
                verified = false,
                faceImg = "face2.png",
                faceWithIdImg = null,
                idCardImg = "ktp2.png",
                idCardNumber = "987654321",
                idCardFullName = "Nezuko Kamado",
                idCardBirthdate = "2002-05-10",
                creditAmount = 50_000
            )
        )

        val flow: Flow<Result<List<Customer>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(customers))
        }

        whenever(repository.getCustomerByUserId(token, userId)).thenReturn(flow)

        val results = mutableListOf<Result<List<Customer>>>()
        useCase(token, userId).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        val successData = (results.last() as Result.Success).data
        assertEquals(2, successData.size)
        assertEquals("Riiha Kamado", successData[0].fullName)
    }

    @Test
    fun `invoke should emit Error when fetch by userId fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val userId = 1

        val flow: Flow<Result<List<Customer>>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to fetch by userId"))
        }

        whenever(repository.getCustomerByUserId(token, userId)).thenReturn(flow)

        val results = mutableListOf<Result<List<Customer>>>()
        useCase(token, userId).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
    }
}