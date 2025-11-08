package com.example.nebeng.feature_goods_transaction.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.CreateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.data.remote.model.request.UpdateGoodsTransactionRequest
import com.example.nebeng.feature_goods_transaction.data.repository.GoodsTransactionRepository
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GoodsTransactionUseCaseTest {

    @Mock
    private lateinit var repository: GoodsTransactionRepository

    private lateinit var createUseCase: CreateGoodsTransactionUseCase
    private lateinit var readAllUseCase: ReadAllGoodsTransactionUseCase
    private lateinit var readByIdUseCase: ReadByIdGoodsTransactionUseCase
    private lateinit var updateUseCase: UpdateGoodsTransactionUseCase
    private lateinit var deleteUseCase: DeleteGoodsTransactionUseCase

    private val token = "Bearer test-token"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        createUseCase = CreateGoodsTransactionUseCase(repository)
        readAllUseCase = ReadAllGoodsTransactionUseCase(repository)
        readByIdUseCase = ReadByIdGoodsTransactionUseCase(repository)
        updateUseCase = UpdateGoodsTransactionUseCase(repository)
        deleteUseCase = DeleteGoodsTransactionUseCase(repository)
    }

    // region 🔹 CREATE TEST
    @Test
    fun `createGoodsTransaction returns Success`() = runTest {
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

        whenever(repository.createGoodsTransaction(token, request)).thenReturn(flow)

//        val result = createUseCase(token, request).first()

        val result = createUseCase(token, request).last()
        assertEquals(Result.Success(fakeTransaction), result)
    }
    // endregion


    // region 🔹 READ ALL TEST
    @Test
    fun `readAllGoodsTransaction returns list of transactions`() = runTest {
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
                createdAt = "2025-11-06T00:00:00Z",
                updatedAt = "2025-11-06T00:00:00Z",
                transactionCode = "TX001"
            )
        )

        val flow: Flow<Result<List<GoodsTransaction>>> = flow {
            emit(Result.Success(fakeList))
        }

        whenever(repository.getAllGoodsTransactions(token)).thenReturn(flow)

        val result = readAllUseCase(token).first()
        assertEquals(Result.Success(fakeList), result)
    }
    // endregion


    // region 🔹 READ BY ID TEST
    @Test
    fun `readGoodsTransactionById returns one transaction`() = runTest {
        val id = 99
        val fakeTransaction = GoodsTransaction(
            id = id,
            goodsRideBookingId = 10,
            customerId = 5,
            totalAmount = 45000,
            paymentMethodId = 1,
            paymentProofImg = null,
            paymentStatus = PaymentStatus.PENDING,
            creditUsed = 0,
            transactionDate = "2025-11-06",
            createdAt = "2025-11-06T00:00:00Z",
            updatedAt = "2025-11-06T00:00:00Z",
            transactionCode = "TX099"
        )

        val flow: Flow<Result<GoodsTransaction>> = flow {
            emit(Result.Success(fakeTransaction))
        }

        whenever(repository.getGoodsTransactionById(token, id)).thenReturn(flow)

        val result = readByIdUseCase(token, id).first()
        assertEquals(Result.Success(fakeTransaction), result)
    }
    // endregion


    // region 🔹 UPDATE TEST
    @Test
    fun `updateGoodsTransaction returns Success`() = runTest {
        val id = 1
        val request = UpdateGoodsTransactionRequest(
            totalAmount = 50000,
            paymentStatus = "DITERIMA",
            paymentProofImg = "proof_updated.png",
            creditUsed = 0
        )

        val updatedTransaction = GoodsTransaction(
            id = id,
            goodsRideBookingId = 5,
            customerId = 10,
            totalAmount = 50000,
            paymentMethodId = 2,
            paymentProofImg = "proof_updated.png",
            paymentStatus = PaymentStatus.DITERIMA,
            creditUsed = 0,
            transactionDate = "2025-11-06",
            createdAt = "2025-11-06T00:00:00Z",
            updatedAt = "2025-11-06T00:00:00Z",
            transactionCode = "TX002"
        )

        val flow: Flow<Result<GoodsTransaction>> = flow {
            emit(Result.Success(updatedTransaction))
        }

        whenever(repository.updateGoodsTransactionById(token, id, request)).thenReturn(flow)

        val result = updateUseCase(token, id, request).first()
        assertEquals(Result.Success(updatedTransaction), result)
    }
    // endregion


    // region 🔹 DELETE TEST
    @Test
    fun `deleteGoodsTransaction returns success message`() = runTest {
        val id = 10
        val message = "Transaction deleted successfully"

        val flow: Flow<Result<String>> = flow {
            emit(Result.Success(message))
        }

        whenever(repository.deleteGoodsTransactionById(token, id)).thenReturn(flow)

        val result = deleteUseCase(token, id).first()
        assertEquals(Result.Success(message), result)
    }
    // endregion
}