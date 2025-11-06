package com.example.nebeng.feature_passenger_transaction.domain.usecase

import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.CreatePassengerTransactionRequest
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
import com.example.nebeng.core.common.Result

class CreatePassengerTransactionUseCaseTest {

    private lateinit var repository: PassengerTransactionRepository
    private lateinit var useCase: CreatePassengerTransactionUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerTransactionRepository::class.java)
        useCase = CreatePassengerTransactionUseCase(repository)
    }

    @Test
    fun `should return created passenger transaction`() = runTest {
        val request = CreatePassengerTransactionRequest(
            transactionDate = "2025-11-05",
            paymentMethodId = 1,
            totalAmount = 20000,
            paymentStatus = "Pending",
            passengerRideBookingId = 10,
            customerId = 2,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val fakeTransaction = PassengerTransaction(
            id = 1,
            passengerRideBookingId = 10,
            customerId = 2,
            totalAmount = 20000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-05",
            createdAt = "",
            updatedAt = ""
        )

        whenever(repository.createPassengerTransaction("token", request))
            .thenReturn(flowOf(Result.Success(fakeTransaction)))

        val result = useCase("token", request).first()
        assertEquals(fakeTransaction, (result as Result.Success).data)
    }
}
