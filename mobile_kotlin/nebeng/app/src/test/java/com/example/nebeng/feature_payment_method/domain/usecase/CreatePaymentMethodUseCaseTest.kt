package com.example.nebeng.feature_payment_method.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.remote.model.request.CreatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
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
class CreatePaymentMethodUseCaseTest {

    @Mock
    private lateinit var repository: PaymentMethodRepository
    private lateinit var useCase: CreatePaymentMethodUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CreatePaymentMethodUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when payment method created successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val request = CreatePaymentMethodRequest(
            accountNumber = "1234567890",
            accountName = "Tanjiro Kamado",
            bankName = "Bank BCA"
        )
        val paymentMethod = PaymentMethod(
            id = 1,
            passengerRideBookingId = 0,
            customerId = 0,
            totalAmount = 0,
            paymentMethod = 1,
            paymentStatus = com.example.nebeng.feature_payment_method.domain.model.PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-05",
            createdAt = "2025-11-05",
            updatedAt = "2025-11-05"
        )
        val flow: Flow<Result<PaymentMethod>> = flow {
            emit(Result.Loading)
            emit(Result.Success(paymentMethod))
        }

        whenever(repository.createPaymentMethod(token, request)).thenReturn(flow)

        val results = mutableListOf<Result<PaymentMethod>>()
        useCase(token, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(paymentMethod.id, (results.last() as Result.Success).data.id)
    }
}