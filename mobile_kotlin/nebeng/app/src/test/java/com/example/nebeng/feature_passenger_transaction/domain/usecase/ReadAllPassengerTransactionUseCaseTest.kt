package com.example.nebeng.feature_passenger_transaction.domain.usecase

import com.example.nebeng.core.common.Result
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

class ReadAllPassengerTransactionUseCaseTest {
    private lateinit var repository: PassengerTransactionRepository
    private lateinit var useCase: ReadAllPassengerTransactionUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerTransactionRepository::class.java)
        useCase = ReadAllPassengerTransactionUseCase(repository)
    }

    @Test
    fun `should return all passenger transactions`() = runTest {
        val transactions = listOf(
            PassengerTransaction(1, 10, 2, 20000, 1, "proof.png", PaymentStatus.PENDING, 0, "", "", ""),
            PassengerTransaction(2, 11, 3, 30000, 2, "proof2.png", PaymentStatus.DITERIMA, 0, "", "", "")
        )

        whenever(repository.getAllPassengerTransactions("token"))
            .thenReturn(flowOf(Result.Success(transactions)))

        val result = useCase("token").first()
        val data = (result as Result.Success).data
        assertEquals(2, data.size)
        assertEquals(20000, data[0].totalAmount)
    }
}