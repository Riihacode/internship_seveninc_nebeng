package com.example.nebeng.feature_passenger_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.repository.PassengerTransactionRepository
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import com.example.nebeng.core.utils.PaymentStatus
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ReadByIdPassengerTransactionUseCaseTest {
    private lateinit var repository: PassengerTransactionRepository
    private lateinit var useCase: ReadByIdPassengerTransactionUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerTransactionRepository::class.java)
        useCase = ReadByIdPassengerTransactionUseCase(repository)
    }

    @Test
    fun `should return passenger transaction by id`() = runTest {
        val transaction = PassengerTransaction(
            1, 10, 2, 20000, 1, "proof.png",
            PaymentStatus.PENDING, 0, "", "", ""
        )

        whenever(repository.getPassengerTransactionById("token", 1))
            .thenReturn(flowOf(Result.Success(transaction)))

        val result = useCase("token", 1).first()
        val data = (result as Result.Success).data
        assertEquals(1, data.id)
        assertEquals(PaymentStatus.PENDING, data.paymentStatus)
    }
}