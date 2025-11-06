package com.example.nebeng.feature_passenger_transaction.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.*
import com.example.nebeng.feature_passenger_transaction.domain.model.PassengerTransaction
import com.example.nebeng.feature_passenger_transaction.domain.usecase.*
import com.example.nebeng.feature_payment_method.domain.model.PaymentStatus
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

@OptIn(ExperimentalCoroutinesApi::class)
class PassengerTransactionViewModelTest {

    @Mock private lateinit var createUseCase: CreatePassengerTransactionUseCase
    @Mock private lateinit var readAllUseCase: ReadAllPassengerTransactionUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdPassengerTransactionUseCase
    @Mock private lateinit var readByBookingUseCase: ReadByPassengerRideBookingIdPassengerTransactionUseCase
    @Mock private lateinit var updateUseCase: UpdatePassengerTransactionUseCase
    @Mock private lateinit var patchStatusUseCase: PatchStatusByIdPassengerTransactionUseCase
    @Mock private lateinit var deleteUseCase: DeletePassengerTransactionUseCase

    private lateinit var useCases: PassengerTransactionUseCases
    private lateinit var viewModel: PassengerTransactionViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        useCases = PassengerTransactionUseCases(
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

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region 🔹 CREATE TEST
    @Test
    fun `create transaction success updates uiState with Success`() = runTest {
        val token = "token123"
        val request = CreatePassengerTransactionRequest(
            transactionDate = "2025-11-05",
            paymentMethodId = 1,
            totalAmount = 15000,
            paymentStatus = "Pending",
            passengerRideBookingId = 10,
            customerId = 99,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val transaction = PassengerTransaction(
            id = 1,
            passengerRideBookingId = 10,
            customerId = 99,
            totalAmount = 15000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-05",
            createdAt = "2025-11-05T00:00:00Z",
            updatedAt = "2025-11-05T00:00:00Z"
        )

        val flow: Flow<Result<PassengerTransaction>> = flow {
            emit(Result.Loading)
            emit(Result.Success(transaction))
        }

        whenever(createUseCase(token, request)).thenReturn(flow)

        viewModel.create(token, request)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentTransaction is Result.Success)
        assertEquals(transaction.id, (uiState.currentTransaction as Result.Success).data.id)
    }

    @Test
    fun `create transaction error updates uiState with Error`() = runTest {
        val token = "token123"
        val request = CreatePassengerTransactionRequest(
            transactionDate = "2025-11-05",
            paymentMethodId = 1,
            totalAmount = 15000,
            paymentStatus = "Pending",
            passengerRideBookingId = 10,
            customerId = 99,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val flow: Flow<Result<PassengerTransaction>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to create"))
        }

        whenever(createUseCase(token, request)).thenReturn(flow)

        viewModel.create(token, request)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentTransaction is Result.Error)
        assertEquals("Failed to create", (uiState.currentTransaction as Result.Error).message)
    }
    // endregion


    // region 🔹 READ BY ID TEST
    @Test
    fun `read by id success updates uiState`() = runTest {
        val token = "token"
        val id = 1
        val transaction = PassengerTransaction(
            id = 1,
            passengerRideBookingId = 10,
            customerId = 99,
            totalAmount = 20000,
            paymentMethod = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.DITERIMA,
            creditUsed = 0,
            transactionDate = "2025-11-05",
            createdAt = "2025-11-05",
            updatedAt = "2025-11-05"
        )

        val flow: Flow<Result<PassengerTransaction>> = flow {
            emit(Result.Loading)
            emit(Result.Success(transaction))
        }

        whenever(readByIdUseCase(token, id)).thenReturn(flow)

        viewModel.readById(token, id)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentTransaction is Result.Success)
        assertEquals(20000, (uiState.currentTransaction as Result.Success).data.totalAmount)
    }
    // endregion


    // region 🔹 DELETE TEST
    @Test
    fun `delete success updates uiState`() = runTest {
        val token = "token"
        val id = 1
        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }

        whenever(deleteUseCase(token, id)).thenReturn(flow)

        viewModel.delete(token, id)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.deleteResult is Result.Success)
        assertEquals(true, (uiState.deleteResult as Result.Success).data)
    }

    @Test
    fun `delete error updates uiState`() = runTest {
        val token = "token"
        val id = 1
        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to delete"))
        }

        whenever(deleteUseCase(token, id)).thenReturn(flow)

        viewModel.delete(token, id)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.deleteResult is Result.Error)
        assertEquals("Failed to delete", (uiState.deleteResult as Result.Error).message)
    }
    // endregion
}