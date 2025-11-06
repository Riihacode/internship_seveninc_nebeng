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

class ReadByPassengerRideBookingIdPassengerTransactionUseCaseTest {
    private lateinit var repository: PassengerTransactionRepository
    private lateinit var useCase: ReadByPassengerRideBookingIdPassengerTransactionUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerTransactionRepository::class.java)
        useCase = ReadByPassengerRideBookingIdPassengerTransactionUseCase(repository)
    }

    @Test
    fun `should return passenger transaction by booking id`() = runTest {
        val transaction = PassengerTransaction(
            1, 10, 2, 20000, 1, "proof.png",
            PaymentStatus.PENDING, 0, "", "", ""
        )

        whenever(repository.getByPassengerRideBookingId("token", 10))
            .thenReturn(flowOf(Result.Success(transaction)))

        val result = useCase("token", 10).first()
        val data = (result as Result.Success).data
        assertEquals(10, data.passengerRideBookingId)
    }
}