package com.example.nebeng.feature_credit_score_log.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.CreateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.UpdateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.repository.CreditScoreLogRepository
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog
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
class CreditScoreLogUseCaseTest {

    @Mock
    private lateinit var repository: CreditScoreLogRepository

    private lateinit var createUseCase: CreateCreditScoreLogUseCase
    private lateinit var readAllUseCase: ReadAllCreditScoreLogUseCase
    private lateinit var readByIdUseCase: ReadByIdCreditScoreLogUseCase
    private lateinit var readByDriverIdUseCase: ReadByDriverIdCreditScoreLogUseCase
    private lateinit var updateUseCase: UpdateCreditScoreLogUseCase
    private lateinit var deleteUseCase: DeleteCreditScoreLogUseCase

    private val token = "Bearer test-token"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        createUseCase = CreateCreditScoreLogUseCase(repository)
        readAllUseCase = ReadAllCreditScoreLogUseCase(repository)
        readByIdUseCase = ReadByIdCreditScoreLogUseCase(repository)
        readByDriverIdUseCase = ReadByDriverIdCreditScoreLogUseCase(repository)
        updateUseCase = UpdateCreditScoreLogUseCase(repository)
        deleteUseCase = DeleteCreditScoreLogUseCase(repository)
    }

    // ============================================================
    // 🔹 CREATE TEST
    // ============================================================
    @Test
    fun `createCreditScoreLog returns Success`() = runTest {
        val request = CreateCreditScoreLogRequest(
            scoreChange = 10,
            driverId = 1,
            notes = "Bonus kinerja",
            actionType = "Bonus",
            createdAt = "2025-11-08T10:00:00Z"
        )

        val fakeLog = CreditScoreLog(
            id = 1,
            driverId = 1,
            actionType = "Bonus",
            scoreChange = "+10",
            notes = "Bonus kinerja",
            createdAt = "2025-11-08T10:00:00Z"
        )

        val flow: Flow<Result<CreditScoreLog>> = flow {
            emit(Result.Loading)
            emit(Result.Success(fakeLog))
        }

        whenever(repository.createCreditScoreLog(token, request)).thenReturn(flow)

        val result = createUseCase(token, request).last()
        assertEquals(Result.Success(fakeLog), result)
    }

    // ============================================================
    // 🔹 READ ALL TEST
    // ============================================================
    @Test
    fun `readAllCreditScoreLogs returns list of logs`() = runTest {
        val fakeList = listOf(
            CreditScoreLog(1, 1, "Bonus", "+5", "Tepat waktu", "2025-11-08"),
            CreditScoreLog(2, 2, "Penalty", "-3", "Telat jemput", "2025-11-08")
        )

        val flow: Flow<Result<List<CreditScoreLog>>> = flow {
            emit(Result.Success(fakeList))
        }

        whenever(repository.getAllCreditScoreLogs(token)).thenReturn(flow)

        val result = readAllUseCase(token).first()
        assertEquals(Result.Success(fakeList), result)
    }

    // ============================================================
    // 🔹 READ BY ID TEST
    // ============================================================
    @Test
    fun `readCreditScoreLogById returns one log`() = runTest {
        val id = 1
        val fakeLog = CreditScoreLog(
            id = id,
            driverId = 5,
            actionType = "Bonus",
            scoreChange = "+15",
            notes = "Sangat responsif",
            createdAt = "2025-11-08"
        )

        val flow: Flow<Result<CreditScoreLog>> = flow {
            emit(Result.Success(fakeLog))
        }

        whenever(repository.getCreditScoreLogById(token, id)).thenReturn(flow)

        val result = readByIdUseCase(token, id).first()
        assertEquals(Result.Success(fakeLog), result)
    }

    // ============================================================
    // 🔹 READ BY DRIVER ID TEST
    // ============================================================
    @Test
    fun `readCreditScoreLogsByDriverId returns list of logs`() = runTest {
        val driverId = 7
        val fakeList = listOf(
            CreditScoreLog(1, driverId, "Bonus", "+10", "Kinerja bagus", "2025-11-08T11:00:00Z")
        )

        val flow: Flow<Result<List<CreditScoreLog>>> = flow {
            emit(Result.Success(fakeList))
        }

        whenever(repository.getCreditScoreLogsByDriverId(token, driverId)).thenReturn(flow)

        val result = readByDriverIdUseCase(token, driverId).first()
        assertEquals(Result.Success(fakeList), result)
    }

    // ============================================================
    // 🔹 UPDATE TEST
    // ============================================================
    @Test
    fun `updateCreditScoreLog returns Success`() = runTest {
        val id = 1
        val request = UpdateCreditScoreLogRequest(
            scoreChange = 5,
            notes = "Performa meningkat",
            actionType = "Bonus"
        )
        val updatedLog = CreditScoreLog(
            id = id,
            driverId = 5,
            actionType = "Bonus",
            scoreChange = "+5",
            notes = "Performa meningkat",
            createdAt = "2025-11-08T11:00:00Z"
        )

        val flow: Flow<Result<CreditScoreLog>> = flow {
            emit(Result.Success(updatedLog))
        }

        whenever(repository.updateCreditScoreLog(token, id, request)).thenReturn(flow)

        val result = updateUseCase(token, id, request).first()
        assertEquals(Result.Success(updatedLog), result)
    }

    // ============================================================
    // 🔹 DELETE TEST
    // ============================================================
    @Test
    fun `deleteCreditScoreLog returns success message`() = runTest {
        val id = 99
        val message = "Credit score log deleted successfully"

        val flow: Flow<Result<String>> = flow {
            emit(Result.Success(message))
        }

        whenever(repository.deleteCreditScoreLogById(token, id)).thenReturn(flow)

        val result = deleteUseCase(token, id).first()
        assertEquals(Result.Success(message), result)
    }
}