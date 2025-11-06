package com.example.nebeng.feature_passenger_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.UpdatePassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.data.repository.PassengerTransactionRepository
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import com.example.nebeng.feature_payment_method.domain.model.PaymentStatus
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class UpdatePassengerTransactionUseCaseTest {
    private lateinit var repository: PassengerTransactionRepository
    private lateinit var useCase: UpdatePassengerTransactionUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerTransactionRepository::class.java)
        useCase = UpdatePassengerTransactionUseCase(repository)
    }

    @Test
    fun `should return updated passenger transaction`() = runTest {
        val request = UpdatePassengerTransactionRequest(
            totalAmount = 25000,
            paymentStatus = "Diterima",
            paymentProofImg = "updated.png",
            creditUsed = 1
        )

        val updated = PassengerTransaction(
            1, 10, 2, 25000, 1, "updated.png",
            PaymentStatus.DITERIMA, 1, "", "", ""
        )

        whenever(repository.updatePassengerTransactionById("token", 1, request))
            .thenReturn(flowOf(Result.Success(updated)))

        val result = useCase("token", 1, request).first()
        val data = (result as Result.Success).data
        assertEquals("updated.png", data.paymentProofImg)
        assertEquals(PaymentStatus.DITERIMA, data.paymentStatus)
    }
}