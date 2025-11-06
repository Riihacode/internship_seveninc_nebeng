package com.example.nebeng.feature_passenger_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.repository.PassengerTransactionRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class DeletePassengerTransactionUseCaseTest {
    private lateinit var repository: PassengerTransactionRepository
    private lateinit var useCase: DeletePassengerTransactionUseCase

    @Before
    fun setUp() {
        repository = mock(PassengerTransactionRepository::class.java)
        useCase = DeletePassengerTransactionUseCase(repository)
    }

    @Test
    fun `should return true when delete successful`() = runTest {
        whenever(repository.deletePassengerTransactionById("token", 1))
            .thenReturn(flowOf(Result.Success(true)))

        val result = useCase("token", 1).first()
        val data = (result as Result.Success).data
        assertTrue(data)
    }
}