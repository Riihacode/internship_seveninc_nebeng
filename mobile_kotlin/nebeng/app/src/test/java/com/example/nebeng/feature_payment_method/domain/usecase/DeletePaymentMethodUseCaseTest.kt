package com.example.nebeng.feature_payment_method.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
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
class DeletePaymentMethodUseCaseTest {

    @Mock
    private lateinit var repository: PaymentMethodRepository
    private lateinit var useCase: DeletePaymentMethodUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = DeletePaymentMethodUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when payment method deleted successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }

        whenever(repository.deletePaymentMethod(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Boolean>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(true, (results.last() as Result.Success).data)
    }
}