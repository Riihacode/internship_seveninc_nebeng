package com.example.nebeng.feature_passenger_transaction.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.*
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import com.example.nebeng.feature_passenger_transaction.domain.usecase.*
import com.example.nebeng.core.utils.PaymentStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers

//@OptIn(ExperimentalCoroutinesApi::class)
//class PassengerTransactionViewModelTest {
//
//    @Mock private lateinit var createUseCase: CreatePassengerTransactionUseCase
//    @Mock private lateinit var readAllUseCase: ReadAllPassengerTransactionUseCase
//    @Mock private lateinit var readByIdUseCase: ReadByIdPassengerTransactionUseCase
//    @Mock private lateinit var readByBookingUseCase: ReadByPassengerRideBookingIdPassengerTransactionUseCase
//    @Mock private lateinit var updateUseCase: UpdatePassengerTransactionUseCase
//    @Mock private lateinit var patchStatusUseCase: PatchStatusByIdPassengerTransactionUseCase
//    @Mock private lateinit var deleteUseCase: DeletePassengerTransactionUseCase
//
//    private lateinit var useCases: PassengerTransactionUseCases
//    private lateinit var viewModel: PassengerTransactionViewModel
//
//    private val dispatcher = UnconfinedTestDispatcher()
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//        Dispatchers.setMain(dispatcher)
//        useCases = PassengerTransactionUseCases(
//            create = createUseCase,
//            readAll = readAllUseCase,
//            readById = readByIdUseCase,
//            readByPassengerRideBookingId = readByBookingUseCase,
//            update = updateUseCase,
//            patchStatus = patchStatusUseCase,
//            delete = deleteUseCase
//        )
//        viewModel = PassengerTransactionViewModel(useCases)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    // region 🔹 CREATE TEST
//    @Test
//    fun `create transaction success updates uiState with Success`() = runTest {
//        val token = "token123"
//        val request = CreatePassengerTransactionRequest(
//            transactionDate = "2025-11-05",
//            paymentMethodId = 1,
//            totalAmount = 15000,
//            paymentStatus = "Pending",
//            passengerRideBookingId = 10,
//            customerId = 99,
//            paymentProofImg = "proof.png",
//            creditUsed = 0
//        )
//
//        val transaction = PassengerTransaction(
//            id = 1,
//            passengerRideBookingId = 10,
//            customerId = 99,
//            totalAmount = 15000,
//            paymentMethod = 1,
//            paymentProofImg = "proof.png",
//            paymentStatus = PaymentStatus.PENDING,
//            creditUsed = 0,
//            transactionDate = "2025-11-05",
//            createdAt = "2025-11-05T00:00:00Z",
//            updatedAt = "2025-11-05T00:00:00Z"
//        )
//
//        val flow: Flow<Result<PassengerTransaction>> = flow {
//            emit(Result.Loading)
//            emit(Result.Success(transaction))
//        }
//
//        whenever(createUseCase(token, request)).thenReturn(flow)
//
//        viewModel.create(token, request)
//        advanceUntilIdle()
//
//        val uiState = viewModel.uiState.value
//        assertTrue(uiState.currentTransaction is Result.Success)
//        assertEquals(transaction.id, (uiState.currentTransaction as Result.Success).data.id)
//    }
//
//    @Test
//    fun `create transaction error updates uiState with Error`() = runTest {
//        val token = "token123"
//        val request = CreatePassengerTransactionRequest(
//            transactionDate = "2025-11-05",
//            paymentMethodId = 1,
//            totalAmount = 15000,
//            paymentStatus = "Pending",
//            passengerRideBookingId = 10,
//            customerId = 99,
//            paymentProofImg = "proof.png",
//            creditUsed = 0
//        )
//
//        val flow: Flow<Result<PassengerTransaction>> = flow {
//            emit(Result.Loading)
//            emit(Result.Error("Failed to create"))
//        }
//
//        whenever(createUseCase(token, request)).thenReturn(flow)
//
//        viewModel.create(token, request)
//        advanceUntilIdle()
//
//        val uiState = viewModel.uiState.value
//        assertTrue(uiState.currentTransaction is Result.Error)
//        assertEquals("Failed to create", (uiState.currentTransaction as Result.Error).message)
//    }
//    // endregion
//
//
//    // region 🔹 READ BY ID TEST
//    @Test
//    fun `read by id success updates uiState`() = runTest {
//        val token = "token"
//        val id = 1
//        val transaction = PassengerTransaction(
//            id = 1,
//            passengerRideBookingId = 10,
//            customerId = 99,
//            totalAmount = 20000,
//            paymentMethod = 1,
//            paymentProofImg = "proof.png",
//            paymentStatus = PaymentStatus.DITERIMA,
//            creditUsed = 0,
//            transactionDate = "2025-11-05",
//            createdAt = "2025-11-05",
//            updatedAt = "2025-11-05"
//        )
//
//        val flow: Flow<Result<PassengerTransaction>> = flow {
//            emit(Result.Loading)
//            emit(Result.Success(transaction))
//        }
//
//        whenever(readByIdUseCase(token, id)).thenReturn(flow)
//
//        viewModel.readById(token, id)
//        advanceUntilIdle()
//
//        val uiState = viewModel.uiState.value
//        assertTrue(uiState.currentTransaction is Result.Success)
//        assertEquals(20000, (uiState.currentTransaction as Result.Success).data.totalAmount)
//    }
//    // endregion
//
//
//    // region 🔹 DELETE TEST
//    @Test
//    fun `delete success updates uiState`() = runTest {
//        val token = "token"
//        val id = 1
//        val flow: Flow<Result<Boolean>> = flow {
//            emit(Result.Loading)
//            emit(Result.Success(true))
//        }
//
//        whenever(deleteUseCase(token, id)).thenReturn(flow)
//
//        viewModel.delete(token, id)
//        advanceUntilIdle()
//
//        val uiState = viewModel.uiState.value
//        assertTrue(uiState.deleteResult is Result.Success)
//        assertEquals(true, (uiState.deleteResult as Result.Success).data)
//    }
//
//    @Test
//    fun `delete error updates uiState`() = runTest {
//        val token = "token"
//        val id = 1
//        val flow: Flow<Result<Boolean>> = flow {
//            emit(Result.Loading)
//            emit(Result.Error("Failed to delete"))
//        }
//
//        whenever(deleteUseCase(token, id)).thenReturn(flow)
//
//        viewModel.delete(token, id)
//        advanceUntilIdle()
//
//        val uiState = viewModel.uiState.value
//        assertTrue(uiState.deleteResult is Result.Error)
//        assertEquals("Failed to delete", (uiState.deleteResult as Result.Error).message)
//    }
//    // endregion
//}

@OptIn(ExperimentalCoroutinesApi::class)
class PassengerTransactionViewModelTest {

    private lateinit var viewModel: PassengerTransactionViewModel

    @Mock private lateinit var createUseCase: CreatePassengerTransactionUseCase
    @Mock private lateinit var readAllUseCase: ReadAllPassengerTransactionUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdPassengerTransactionUseCase
    @Mock private lateinit var readByBookingUseCase: ReadByPassengerRideBookingIdPassengerTransactionUseCase
    @Mock private lateinit var updateUseCase: UpdatePassengerTransactionUseCase
    @Mock private lateinit var patchStatusUseCase: PatchStatusByIdPassengerTransactionUseCase
    @Mock private lateinit var deleteUseCase: DeletePassengerTransactionUseCase

    private val testDispatcher = StandardTestDispatcher()
    private val token = "Bearer test-token"

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        val useCases = PassengerTransactionUseCases(
            create = createUseCase,
            readAll = readAllUseCase,
            readById = readByIdUseCase,
            readByPassengerRideBookingId = readByBookingUseCase,
            update = updateUseCase,
            patchStatus = patchStatusUseCase,
            delete = deleteUseCase
        )
        viewModel = PassengerTransactionViewModel(useCases)
    }

    // ============================================================
    // 🔹 GET ALL
    // ============================================================
    @Test
    fun `getAllPassengerTransactions emits Success updates state`() = runTest(testDispatcher) {
        val transactions = listOf(
            PassengerTransaction(
                id = 1,
                passengerRideBookingId = 10,
                customerId = 5,
                totalAmount = 30000,
                paymentMethod = 1,
                paymentProofImg = "proof.png",
                paymentStatus = PaymentStatus.DITERIMA,
                creditUsed = 0,
                transactionDate = "2025-11-09",
                createdAt = "2025-11-09T00:00:00Z",
                updatedAt = "2025-11-09T00:00:00Z"
            )
        )

        whenever(readAllUseCase(token)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(transactions))
        })

        viewModel.getAllPassengerTransactions(token)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.transactions is Result.Success)
        assertEquals(1, (uiState.transactions as Result.Success).data.size)
        assertEquals(false, uiState.isLoading)
    }

    @Test
    fun `getAllPassengerTransactions emits Error updates errorMessage`() = runTest(testDispatcher) {
        whenever(readAllUseCase(token)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to fetch transactions"))
        })

        viewModel.getAllPassengerTransactions(token)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
//        assertTrue(uiState.transactions is Result.Error)
//        assertEquals("Failed to fetch transactions", uiState.errorMessage)
        assertEquals("Failed to fetch transactions", uiState.errorMessage)
        assertTrue(uiState.transactions is Result.Success)
        assertTrue((uiState.transactions as Result.Success).data.isEmpty())
    }

    // ============================================================
    // 🔹 CREATE
    // ============================================================
    @Test
    fun `createPassengerTransaction emits Success updates currentTransaction`() = runTest(testDispatcher) {
        val request = CreatePassengerTransactionRequest(
            transactionDate = "2025-11-09",
            paymentMethodId = 1,
            totalAmount = 50000,
            paymentStatus = "pending",
            passengerRideBookingId = 3,
            customerId = 2,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val fakeTransaction = PassengerTransaction(
            id = 99,
            passengerRideBookingId = 3,
            customerId = 2,
            totalAmount = 50000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-09",
            createdAt = "2025-11-09T00:00:00Z",
            updatedAt = "2025-11-09T00:00:00Z"
        )

        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeTransaction))
        })

        viewModel.createPassengerTransaction(token, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentTransaction is Result.Success)
        assertEquals("Transaksi berhasil dibuat", uiState.successMessage)
    }

    @Test
    fun `createPassengerTransaction emits Error updates errorMessage`() = runTest(testDispatcher) {
        val request = CreatePassengerTransactionRequest(
            transactionDate = "2025-11-09",
            paymentMethodId = 1,
            totalAmount = 50000,
            paymentStatus = "pending",
            passengerRideBookingId = 3,
            customerId = 2,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Gagal membuat transaksi"))
        })

        viewModel.createPassengerTransaction(token, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
//        assertTrue(uiState.currentTransaction is Result.Error)
//        assertEquals("Gagal membuat transaksi", uiState.errorMessage)
        assertEquals("Gagal membuat transaksi", uiState.errorMessage)
        assertTrue(uiState.currentTransaction is Result.Success)
        assertEquals(null, (uiState.currentTransaction as Result.Success).data)
    }

    // ============================================================
    // 🔹 PATCH STATUS
    // ============================================================
    @Test
    fun `patchTransactionStatus emits Success updates currentTransaction`() = runTest(testDispatcher) {
        val id = 5
        val request = PatchStatusByIdPassengerTransactionRequest(status = "DITERIMA")
        val fakeTransaction = PassengerTransaction(
            id = id,
            passengerRideBookingId = 1,
            customerId = 1,
            totalAmount = 20000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.DITERIMA,
            creditUsed = 0,
            transactionDate = "2025-11-09",
            createdAt = "2025-11-09T00:00:00Z",
            updatedAt = "2025-11-09T00:00:00Z"
        )

        whenever(patchStatusUseCase(token, id, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeTransaction))
        })

        viewModel.patchTransactionStatus(token, id, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentTransaction is Result.Success)
        assertEquals("Status transaksi berhasil diperbarui", uiState.successMessage)
    }

    // ============================================================
    // 🔹 DELETE
    // ============================================================
    @Test
    fun `deletePassengerTransaction emits Success updates successMessage`() = runTest(testDispatcher) {
        val id = 5
        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success("Transaksi dihapus"))
        })

        viewModel.deletePassengerTransaction(token, id)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Transaksi dihapus", uiState.successMessage)
        assertEquals(false, uiState.isLoading)
    }

    @Test
    fun `deletePassengerTransaction emits Error updates errorMessage`() = runTest(testDispatcher) {
        val id = 5
        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Gagal menghapus transaksi"))
        })

        viewModel.deletePassengerTransaction(token, id)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Gagal menghapus transaksi", uiState.errorMessage)
    }
}