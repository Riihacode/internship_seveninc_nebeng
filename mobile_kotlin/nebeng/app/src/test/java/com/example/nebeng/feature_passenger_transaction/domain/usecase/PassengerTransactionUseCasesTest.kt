package com.example.nebeng.feature_passenger_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.CreatePassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.PatchStatusByIdPassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.UpdatePassengerTransactionRequest
import com.example.nebeng.feature_passenger_transaction.data.repository.PassengerTransactionRepository
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PassengerTransactionUseCasesTest {

    @Mock
    private lateinit var repository: PassengerTransactionRepository

    private lateinit var createUseCase: CreatePassengerTransactionUseCase
    private lateinit var readAllUseCase: ReadAllPassengerTransactionUseCase
    private lateinit var readByIdUseCase: ReadByIdPassengerTransactionUseCase
    private lateinit var readByPassengerRideBookingIdUseCase: ReadByPassengerRideBookingIdPassengerTransactionUseCase
    private lateinit var updateUseCase: UpdatePassengerTransactionUseCase
    private lateinit var patchStatusUseCase: PatchStatusByIdPassengerTransactionUseCase
    private lateinit var deleteUseCase: DeletePassengerTransactionUseCase

    private val testDispatcher = StandardTestDispatcher()
    private val token = "Bearer test-token"

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        createUseCase = CreatePassengerTransactionUseCase(repository)
        readAllUseCase = ReadAllPassengerTransactionUseCase(repository)
        readByIdUseCase = ReadByIdPassengerTransactionUseCase(repository)
        readByPassengerRideBookingIdUseCase = ReadByPassengerRideBookingIdPassengerTransactionUseCase(repository)
        updateUseCase = UpdatePassengerTransactionUseCase(repository)
        patchStatusUseCase = PatchStatusByIdPassengerTransactionUseCase(repository)
        deleteUseCase = DeletePassengerTransactionUseCase(repository)
    }

    // ============================================================
    // 🔹 CREATE
    // ============================================================
    @Test
    fun `CreatePassengerTransactionUseCase returns Success`() = runTest(testDispatcher) {
        val request = CreatePassengerTransactionRequest(
            transactionDate = "2025-11-09",
            paymentMethodId = 1,
            totalAmount = 50000,
            paymentStatus = "pending",
            passengerRideBookingId = 10,
            customerId = 7,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val expected = PassengerTransaction(
            id = 1,
            passengerRideBookingId = 10,
            customerId = 7,
            totalAmount = 50000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-09",
            createdAt = "2025-11-09",
            updatedAt = "2025-11-09"
        )

        whenever(repository.createPassengerTransaction(token, request)).thenReturn(flow {
            emit(Result.Success(expected))
        })

        val result = createUseCase(token, request)
        val emitted = result.single()
        assertTrue(emitted is Result.Success)
        assertEquals(expected, (emitted as Result.Success).data)
    }

    // ============================================================
    // 🔹 READ ALL
    // ============================================================
    @Test
    fun `ReadAllPassengerTransactionUseCase returns list`() = runTest(testDispatcher) {
        val transactions = listOf(
            PassengerTransaction(
                id = 1,
                passengerRideBookingId = 2,
                customerId = 3,
                totalAmount = 10000,
                paymentMethod = 1,
                paymentProofImg = "proof.png",
                paymentStatus = PaymentStatus.DITERIMA,
                creditUsed = 0,
                transactionDate = "2025-11-09",
                createdAt = "2025-11-09",
                updatedAt = "2025-11-09"
            )
        )

        whenever(repository.getAllPassengerTransactions(token)).thenReturn(flow {
            emit(Result.Success(transactions))
        })

        val result = readAllUseCase(token).single()
        assertTrue(result is Result.Success)
        assertEquals(1, (result as Result.Success).data.size)
    }

    // ============================================================
    // 🔹 READ BY ID
    // ============================================================
    @Test
    fun `ReadByIdPassengerTransactionUseCase returns item`() = runTest(testDispatcher) {
        val expected = PassengerTransaction(
            id = 5,
            passengerRideBookingId = 10,
            customerId = 1,
            totalAmount = 30000,
            paymentMethod = 2,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-09",
            createdAt = "2025-11-09",
            updatedAt = "2025-11-09"
        )

        whenever(repository.getPassengerTransactionById(token, 5)).thenReturn(flow {
            emit(Result.Success(expected))
        })

        val result = readByIdUseCase(token, 5).single()
        assertTrue(result is Result.Success)
        assertEquals(expected.id, (result as Result.Success).data.id)
    }

    // ============================================================
    // 🔹 READ BY BOOKING ID
    // ============================================================
    @Test
    fun `ReadByPassengerRideBookingIdPassengerTransactionUseCase returns item`() = runTest(testDispatcher) {
        val expected = PassengerTransaction(
            id = 8,
            passengerRideBookingId = 77,
            customerId = 11,
            totalAmount = 15000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.DITERIMA,
            creditUsed = 0,
            transactionDate = "2025-11-09",
            createdAt = "2025-11-09",
            updatedAt = "2025-11-09"
        )

        whenever(repository.getPassengerTransactionByPassengerRideBookingId(token, 77)).thenReturn(flow {
            emit(Result.Success(expected))
        })

        val result = readByPassengerRideBookingIdUseCase(token, 77).single()
        assertTrue(result is Result.Success)
        assertEquals(expected.id, (result as Result.Success).data.id)
    }

    // ============================================================
    // 🔹 UPDATE
    // ============================================================
    @Test
    fun `UpdatePassengerTransactionUseCase returns updated item`() = runTest(testDispatcher) {
        val id = 10
        val request = UpdatePassengerTransactionRequest(
            totalAmount = 60000,
            paymentStatus = "pending",
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val updated = PassengerTransaction(
            id = id,
            passengerRideBookingId = 5,
            customerId = 4,
            totalAmount = 60000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-09",
            createdAt = "2025-11-09",
            updatedAt = "2025-11-09"
        )

        whenever(repository.updatePassengerTransactionById(token, id, request)).thenReturn(flow {
            emit(Result.Success(updated))
        })

        val result = updateUseCase(token, id, request).single()
        assertTrue(result is Result.Success)
        assertEquals(updated.totalAmount, (result as Result.Success).data.totalAmount)
    }

    // ============================================================
    // 🔹 PATCH STATUS
    // ============================================================
    @Test
    fun `PatchStatusByIdPassengerTransactionUseCase returns updated status`() = runTest(testDispatcher) {
        val id = 1
        val request = PatchStatusByIdPassengerTransactionRequest(status = "DITERIMA")

        val patched = PassengerTransaction(
            id = id,
            passengerRideBookingId = 3,
            customerId = 2,
            totalAmount = 15000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.DITERIMA,
            creditUsed = 0,
            transactionDate = "2025-11-09",
            createdAt = "2025-11-09",
            updatedAt = "2025-11-09"
        )

        whenever(repository.patchStatusPassengerTransactionById(token, id, request)).thenReturn(flow {
            emit(Result.Success(patched))
        })

        val result = patchStatusUseCase(token, id, request).single()
        assertTrue(result is Result.Success)
        assertEquals(PaymentStatus.DITERIMA, (result as Result.Success).data.paymentStatus)
    }

    // ============================================================
    // 🔹 DELETE
    // ============================================================
    @Test
    fun `DeletePassengerTransactionUseCase returns success message`() = runTest(testDispatcher) {
        val id = 9
        whenever(repository.deletePassengerTransactionById(token, id)).thenReturn(flow {
            emit(Result.Success("Deleted successfully"))
        })

        val result = deleteUseCase(token, id).single()
        assertTrue(result is Result.Success)
        assertEquals("Deleted successfully", (result as Result.Success).data)
    }
}
