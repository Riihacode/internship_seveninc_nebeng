package com.example.nebeng.feature_driver_withdrawal.domain.usecase


import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.DriverWithdrawalStatus
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.CreateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.remote.model.request.UpdateDriverWithdrawalRequest
import com.example.nebeng.feature_driver_withdrawal.data.repository.DriverWithdrawalRepository
import com.example.nebeng.feature_driver_withdrawal.domain.model.DriverWithdrawal
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
class DriverWithdrawalUseCasesTest {
    @Mock
    private lateinit var repository: DriverWithdrawalRepository

    private lateinit var createUseCase: CreateDriverWithdrawalUseCase
    private lateinit var readAllUseCase: ReadAllDriverWithdrawalUseCase
    private lateinit var readByIdUseCase: ReadByIdDriverWithdrawalUseCase
    private lateinit var readByDriverIdUseCase: ReadByDriverIdDriverWithdrawalUseCase
    private lateinit var readByStatusUseCase: ReadByStatusDriverWithdrawalUseCase
    private lateinit var updateUseCase: UpdateDriverWithdrawalUseCase
    private lateinit var deleteUseCase: DeleteDriverWithdrawalUseCase

    private val token = "Bearer test-token"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        createUseCase = CreateDriverWithdrawalUseCase(repository)
        readAllUseCase = ReadAllDriverWithdrawalUseCase(repository)
        readByIdUseCase = ReadByIdDriverWithdrawalUseCase(repository)
        readByDriverIdUseCase = ReadByDriverIdDriverWithdrawalUseCase(repository)
        readByStatusUseCase = ReadByStatusDriverWithdrawalUseCase(repository)
        updateUseCase = UpdateDriverWithdrawalUseCase(repository)
        deleteUseCase = DeleteDriverWithdrawalUseCase(repository)
    }

    // region 🔹 CREATE TEST
    @Test
    fun `createDriverWithdrawal returns Success`() = runTest {
        val request = CreateDriverWithdrawalRequest(
            accountNumber = "987654321",
            driverId = 5,
            rejectedReason = "",
            accountName = "Rengoku Kyojuro",
            bankName = "BNI",
            withdrawalAmount = 75000,
            status = "pending"
        )

        val fakeWithdrawal = DriverWithdrawal(
            id = 1,
            driverId = 5,
            withdrawalAmount = 75000,
            bankName = "BNI",
            accountName = "Rengoku Kyojuro",
            status = DriverWithdrawalStatus.PENDING,
            rejectedReason = "",
            createdAt = "2025-11-08T00:00:00Z",
            updatedAt = "2025-11-08T00:00:00Z"
        )

        val flow: Flow<Result<DriverWithdrawal>> = flow {
            emit(Result.Loading)
            emit(Result.Success(fakeWithdrawal))
        }

        whenever(repository.createDriverWithdrawal(token, request)).thenReturn(flow)

        val result = createUseCase(token, request).last()
        assertEquals(Result.Success(fakeWithdrawal), result)
    }
    // endregion

    // region 🔹 READ ALL TEST
    @Test
    fun `readAllDriverWithdrawals returns Success`() = runTest {
        val fakeList = listOf(
            DriverWithdrawal(
                id = 1,
                driverId = 2,
                withdrawalAmount = 100000,
                bankName = "BCA",
                accountName = "Inosuke",
                status = DriverWithdrawalStatus.DITERIMA,
                rejectedReason = "",
                createdAt = "2025-11-08T00:00:00Z",
                updatedAt = "2025-11-08T00:00:00Z"
            )
        )

        val flow: Flow<Result<List<DriverWithdrawal>>> = flow {
            emit(Result.Success(fakeList))
        }

        whenever(repository.getAllDriverWithdrawals(token)).thenReturn(flow)

        val result = readAllUseCase(token).first()
        assertEquals(Result.Success(fakeList), result)
    }
    // endregion

    // region 🔹 READ BY ID TEST
    @Test
    fun `readDriverWithdrawalById returns one withdrawal`() = runTest {
        val id = 1
        val fakeWithdrawal = DriverWithdrawal(
            id = id,
            driverId = 7,
            withdrawalAmount = 90000,
            bankName = "BRI",
            accountName = "Nezuko Kamado",
            status = DriverWithdrawalStatus.PENDING,
            rejectedReason = "",
            createdAt = "2025-11-08T00:00:00Z",
            updatedAt = "2025-11-08T00:00:00Z"
        )

        val flow: Flow<Result<DriverWithdrawal>> = flow {
            emit(Result.Success(fakeWithdrawal))
        }

        whenever(repository.getDriverWithdrawalById(token, id)).thenReturn(flow)

        val result = readByIdUseCase(token, id).first()
        assertEquals(Result.Success(fakeWithdrawal), result)
    }
    // endregion

    // region 🔹 READ BY DRIVER ID TEST
    @Test
    fun `readDriverWithdrawalByDriverId returns list`() = runTest {
        val driverId = 5
        val fakeList = listOf(
            DriverWithdrawal(
                id = 1,
                driverId = driverId,
                withdrawalAmount = 60000,
                bankName = "Mandiri",
                accountName = "Tomioka Giyuu",
                status = DriverWithdrawalStatus.PENDING,
                rejectedReason = "",
                createdAt = "2025-11-08T00:00:00Z",
                updatedAt = "2025-11-08T00:00:00Z"
            )
        )

        val flow: Flow<Result<List<DriverWithdrawal>>> = flow {
            emit(Result.Success(fakeList))
        }

        whenever(repository.getDriverWithdrawalsByDriverId(token, driverId)).thenReturn(flow)

        val result = readByDriverIdUseCase(token, driverId).first()
        assertEquals(Result.Success(fakeList), result)
    }
    // endregion

    // region 🔹 READ BY STATUS TEST
    @Test
    fun `readDriverWithdrawalByStatus returns filtered list`() = runTest {
        val status = "pending"
        val fakeList = listOf(
            DriverWithdrawal(
                id = 1,
                driverId = 4,
                withdrawalAmount = 40000,
                bankName = "BCA",
                accountName = "Zenitsu",
                status = DriverWithdrawalStatus.PENDING,
                rejectedReason = "",
                createdAt = "2025-11-08T00:00:00Z",
                updatedAt = "2025-11-08T00:00:00Z"
            )
        )

        val flow: Flow<Result<List<DriverWithdrawal>>> = flow {
            emit(Result.Success(fakeList))
        }

        whenever(repository.getDriverWithdrawalsByStatus(token, status)).thenReturn(flow)

        val result = readByStatusUseCase(token, status).first()
        assertEquals(Result.Success(fakeList), result)
    }
    // endregion

    // region 🔹 UPDATE TEST
    @Test
    fun `updateDriverWithdrawal returns Success`() = runTest {
        val id = 1
        val request = UpdateDriverWithdrawalRequest(
            accountNumber = "123456",
            rejectedReason = "",
            accountName = "Tomioka Giyuu",
            bankName = "Mandiri",
            withdrawalAmount = 65000,
            status = "diterima"
        )

        val updatedWithdrawal = DriverWithdrawal(
            id = id,
            driverId = 5,
            withdrawalAmount = 65000,
            bankName = "Mandiri",
            accountName = "Tomioka Giyuu",
            status = DriverWithdrawalStatus.DITERIMA,
            rejectedReason = "",
            createdAt = "2025-11-08T00:00:00Z",
            updatedAt = "2025-11-08T00:00:00Z"
        )

        val flow: Flow<Result<DriverWithdrawal>> = flow {
            emit(Result.Success(updatedWithdrawal))
        }

        whenever(repository.updateDriverWithdrawalById(token, id, request)).thenReturn(flow)

        val result = updateUseCase(token, id, request).first()
        assertEquals(Result.Success(updatedWithdrawal), result)
    }
    // endregion

    // region 🔹 DELETE TEST
    @Test
    fun `deleteDriverWithdrawal returns success message`() = runTest {
        val id = 1
        val message = "Penarikan berhasil dihapus"

        val flow: Flow<Result<String>> = flow {
            emit(Result.Success(message))
        }

        whenever(repository.deleteDriverWithdrawalById(token, id)).thenReturn(flow)

        val result = deleteUseCase(token, id).first()
        assertEquals(Result.Success(message), result)
    }
    // endregion
}