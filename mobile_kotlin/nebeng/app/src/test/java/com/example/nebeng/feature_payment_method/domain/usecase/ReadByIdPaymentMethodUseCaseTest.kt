package com.example.nebeng.feature_payment_method.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import com.example.nebeng.feature_payment_method.domain.model.PaymentMethod
import com.example.nebeng.core.utils.PaymentStatus
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
class ReadByIdPaymentMethodUseCaseTest {

    @Mock
    private lateinit var repository: PaymentMethodRepository
    private lateinit var useCase: ReadByIdPaymentMethodUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadByIdPaymentMethodUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when payment method found`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val method = PaymentMethod(id, 0, 0, 10000, 1, PaymentStatus.PENDING, 0, "", "", "")
        val flow: Flow<Result<PaymentMethod>> = flow {
            emit(Result.Loading)
            emit(Result.Success(method))
        }

        whenever(repository.getPaymentMethodById(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<PaymentMethod>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(id, (results.last() as Result.Success).data.id)
    }
}