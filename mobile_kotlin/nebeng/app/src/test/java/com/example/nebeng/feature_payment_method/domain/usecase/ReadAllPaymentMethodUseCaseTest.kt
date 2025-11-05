package com.example.nebeng.feature_payment_method.domain.usecase

import com.example.nebeng.core.common.Result
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
class ReadAllPaymentMethodUseCaseTest {

    @Mock
    private lateinit var repository: PaymentMethodRepository
    private lateinit var useCase: ReadAllPaymentMethodUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadAllPaymentMethodUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when payment methods retrieved successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val methods = listOf(
            PaymentMethod(1, 0, 0, 10000, 1, PaymentStatus.PENDING, 0, "", "", ""),
            PaymentMethod(2, 0, 0, 20000, 1, PaymentStatus.DITERIMA, 0, "", "", "")
        )
        val flow: Flow<Result<List<PaymentMethod>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(methods))
        }

        whenever(repository.getAllPaymentMethods(token)).thenReturn(flow)

        val results = mutableListOf<Result<List<PaymentMethod>>>()
        useCase(token).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(2, (results.last() as Result.Success).data.size)
    }
}