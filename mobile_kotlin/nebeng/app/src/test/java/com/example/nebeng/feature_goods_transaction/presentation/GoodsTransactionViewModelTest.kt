package com.example.nebeng.feature_goods_transaction.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.CreateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.UpdateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction
import com.example.nebeng.feature_goods_transaction.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GoodsTransactionViewModelTest {

    // Mock semua usecase
    @Mock private lateinit var createUseCase: CreateGoodsTransactionUseCase
    @Mock private lateinit var readAllUseCase: ReadAllGoodsTransactionUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdGoodsTransactionUseCase
    @Mock private lateinit var updateUseCase: UpdateGoodsTransactionUseCase
    @Mock private lateinit var deleteUseCase: DeleteGoodsTransactionUseCase

    private lateinit var useCases: GoodsTransactionUseCases
    private lateinit var viewModel: GoodsTransactionViewModel

    private val token = "Bearer test-token"
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)

        useCases = GoodsTransactionUseCases(
            create = createUseCase,
            readAll = readAllUseCase,
            readById = readByIdUseCase,
            update = updateUseCase,
            delete = deleteUseCase
        )

        viewModel = GoodsTransactionViewModel(useCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region 🔹 CREATE
    @Test
    fun `createTransaction success updates uiState`() = runTest {
        val request = CreateGoodsTransactionRequest(
            transactionDate = "2025-11-06",
            paymentMethodId = 1,
            totalAmount = 25000,
            paymentStatus = "PENDING",
            goodsRideBookingId = 10,
            customerId = 7,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val fakeTransaction = GoodsTransaction(
            id = 1,
            goodsRideBookingId = 10,
            customerId = 7,
            totalAmount = 25000,
            paymentMethodId = 1,
            paymentProofImg = "proof.png",
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-06",
            createdAt = "2025-11-06T00:00:00Z",
            updatedAt = "2025-11-06T00:00:00Z",
            transactionCode = "TX001"
        )

        val flow: Flow<Result<GoodsTransaction>> = flow {
            emit(Result.Loading)
            emit(Result.Success(fakeTransaction))
        }

        whenever(createUseCase(token, request)).thenReturn(flow)

        viewModel.createTransaction(token, request)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(Result.Success(fakeTransaction), state.createResult)
        assertNull(state.errorMessage)
    }

    @Test
    fun `createTransaction error updates uiState`() = runTest {
        val request = CreateGoodsTransactionRequest(
            transactionDate = "2025-11-06",
            paymentMethodId = 1,
            totalAmount = 25000,
            paymentStatus = "PENDING",
            goodsRideBookingId = 10,
            customerId = 7,
            paymentProofImg = "proof.png",
            creditUsed = 0
        )

        val flow: Flow<Result<GoodsTransaction>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to create transaction"))
        }

        whenever(createUseCase(token, request)).thenReturn(flow)

        viewModel.createTransaction(token, request)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals("Failed to create transaction", state.errorMessage)
    }
    // endregion


    // region 🔹 READ ALL
    @Test
    fun `getAllTransactions success updates uiState`() = runTest {
        val fakeList = listOf(
            GoodsTransaction(
                id = 1,
                goodsRideBookingId = 5,
                customerId = 10,
                totalAmount = 30000,
                paymentMethodId = 2,
                paymentProofImg = "proof1.png",
                paymentStatus = PaymentStatus.DITERIMA,
                creditUsed = 0,
                transactionDate = "2025-11-06",
                createdAt = "2025-11-06",
                updatedAt = "2025-11-06",
                transactionCode = "TX001"
            )
        )

        whenever(readAllUseCase(token)).thenReturn(flowOf(Result.Success(fakeList)))

        viewModel.getAllTransactions(token)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(fakeList, state.transactions)
        assertNull(state.errorMessage)
    }
    // endregion


    // region 🔹 READ BY ID
    @Test
    fun `getTransactionById success updates selectedTransaction`() = runTest {
        val fakeTransaction = GoodsTransaction(
            id = 100,
            goodsRideBookingId = 10,
            customerId = 2,
            totalAmount = 40000,
            paymentMethodId = 1,
            paymentProofImg = null,
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-06",
            createdAt = "2025-11-06",
            updatedAt = "2025-11-06",
            transactionCode = "TX100"
        )

        whenever(readByIdUseCase(token, 100)).thenReturn(flowOf(Result.Success(fakeTransaction)))

        viewModel.getTransactionById(token, 100)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(fakeTransaction, state.selectedTransaction)
        assertNull(state.errorMessage)
    }
    // endregion


    // region 🔹 UPDATE
    @Test
    fun `updateTransaction success updates uiState`() = runTest {
        val request = UpdateGoodsTransactionRequest(
            totalAmount = 50000,
            paymentStatus = "DITERIMA",
            paymentProofImg = "proof_updated.png",
            creditUsed = 0
        )

        val updatedTransaction = GoodsTransaction(
            id = 1,
            goodsRideBookingId = 5,
            customerId = 10,
            totalAmount = 50000,
            paymentMethodId = 2,
            paymentProofImg = "proof_updated.png",
            paymentStatus = PaymentStatus.DITERIMA,
            creditUsed = 0,
            transactionDate = "2025-11-06",
            createdAt = "2025-11-06",
            updatedAt = "2025-11-06",
            transactionCode = "TX002"
        )

        whenever(updateUseCase(token, 1, request))
            .thenReturn(flowOf(Result.Success(updatedTransaction)))

        viewModel.updateTransaction(token, 1, request)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(Result.Success(updatedTransaction), state.updateResult)
        assertNull(state.errorMessage)
    }
    // endregion


    // region 🔹 DELETE
    @Test
    fun `deleteTransaction success updates uiState`() = runTest {
        val id = 1
        val message = "Transaction deleted successfully"

        whenever(deleteUseCase(token, id))
            .thenReturn(flowOf(Result.Success(message)))

        viewModel.deleteTransaction(token, id)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(Result.Success(message), state.deleteResult)
        assertNull(state.errorMessage)
    }

    @Test
    fun `deleteTransaction error updates uiState`() = runTest {
        val id = 1

        whenever(deleteUseCase(token, id))
            .thenReturn(flowOf(Result.Error("Failed to delete transaction")))

        viewModel.deleteTransaction(token, id)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("Failed to delete transaction", state.errorMessage)
    }
    // endregion
}