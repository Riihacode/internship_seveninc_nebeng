package com.example.nebeng.feature_payment_method.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.remote.model.request.UpdatePaymentMethodRequest
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import com.example.nebeng.feature_payment_method.domain.model.PaymentStatus
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
class UpdatePaymentMethodUseCaseTest {

    @Mock
    private lateinit var repository: PaymentMethodRepository
    private lateinit var useCase: UpdatePaymentMethodUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = UpdatePaymentMethodUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when payment method updated successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val request = UpdatePaymentMethodRequest(
            accountNumber = "9876543210",
            accountName = "Nezuko Kamado",
            bankName = "Bank Mandiri"
        )
        val updated = PaymentMethod(id, 0, 0, 30000, 1, PaymentStatus.DITERIMA, 0, "", "", "")
        val flow: Flow<Result<PaymentMethod>> = flow {
            emit(Result.Loading)
            emit(Result.Success(updated))
        }

        whenever(repository.updatePaymentMethod(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<PaymentMethod>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(updated.paymentStatus, (results.last() as Result.Success).data.paymentStatus)
    }
}