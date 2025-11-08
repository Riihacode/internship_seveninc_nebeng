package com.example.nebeng.feature_credit_score_log.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.CreateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.data.remote.model.request.UpdateCreditScoreLogRequest
import com.example.nebeng.feature_credit_score_log.domain.model.CreditScoreLog
import com.example.nebeng.feature_credit_score_log.domain.usecase.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CreditScoreLogViewModelTest {

    @Mock private lateinit var createUseCase: CreateCreditScoreLogUseCase
    @Mock private lateinit var readAllUseCase: ReadAllCreditScoreLogUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdCreditScoreLogUseCase
    @Mock private lateinit var readByDriverIdUseCase: ReadByDriverIdCreditScoreLogUseCase
    @Mock private lateinit var updateUseCase: UpdateCreditScoreLogUseCase
    @Mock private lateinit var deleteUseCase: DeleteCreditScoreLogUseCase

    private lateinit var viewModel: CreditScoreLogViewModel
    private val dispatcher = StandardTestDispatcher()
    private val token = "Bearer test-token"

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        val useCases = CreditScoreLogUseCases(
            create = createUseCase,
            readAll = readAllUseCase,
            readById = readByIdUseCase,
            readByDriverId = readByDriverIdUseCase,
            update = updateUseCase,
            delete = deleteUseCase
        )
        viewModel = CreditScoreLogViewModel(useCases)
    }

    // ============================================================
    // 🔹 CREATE
    // ============================================================
    @Test
    fun `createCreditScoreLog emits Success updates currentLog`() = runTest(dispatcher) {
        val request = CreateCreditScoreLogRequest(
            scoreChange = 10,
            driverId = 1,
            notes = "Pelanggaran ringan",
            actionType = "Bonus",
            createdAt = "2025-11-08T12:00:00Z"
        )
        val fakeLog = CreditScoreLog(
            id = 1,
            driverId = 1,
            actionType = "Bonus",
            scoreChange = "+10",
            notes = "Pelanggaran ringan",
            createdAt = "2025-11-08T12:00:00Z"
        )

        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeLog))
        })

        viewModel.createCreditScoreLog(token, request)
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.currentLog is Result.Success)
        assertEquals("Bonus", (state.currentLog as Result.Success).data?.actionType)
        assertEquals("Credit score log berhasil dibuat", state.successMessage)
    }

    @Test
    fun `createCreditScoreLog emits Error updates errorMessage`() = runTest(dispatcher) {
        val request = CreateCreditScoreLogRequest(
            scoreChange = -5,
            driverId = 2,
            notes = "Telat menjemput",
            actionType = "Penalty",
            createdAt = "2025-11-08T12:00:00Z"
        )

        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Gagal membuat log"))
        })

        viewModel.createCreditScoreLog(token, request)
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.currentLog is Result.Error)
        assertEquals("Gagal membuat log", state.errorMessage)
    }

    // ============================================================
    // 🔹 READ ALL
    // ============================================================
    @Test
    fun `getAllCreditScoreLogs emits Success updates creditScoreLogs`() = runTest(dispatcher) {
        val fakeLogs = listOf(
            CreditScoreLog(1, 1, "Bonus", "+5", "On time", "2025-11-08"),
            CreditScoreLog(2, 2, "Penalty", "-3", "Telat 10 menit", "2025-11-08")
        )

        whenever(readAllUseCase(token)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeLogs))
        })

        viewModel.getAllCreditScoreLogs(token)
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.creditScoreLogs is Result.Success)
        assertEquals(2, (state.creditScoreLogs as Result.Success).data.size)
    }

    // ============================================================
    // 🔹 READ BY DRIVER ID
    // ============================================================
    @Test
    fun `getCreditScoreLogsByDriverId emits Success updates creditScoreLogs`() = runTest(dispatcher) {
        val driverId = 10
        val fakeLogs = listOf(
            CreditScoreLog(1, driverId, "Bonus", "+10", "Kinerja bagus", "2025-11-08T12:00:00Z")
        )

        whenever(readByDriverIdUseCase(token, driverId)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeLogs))
        })

        viewModel.getCreditScoreLogsByDriverId(token, driverId)
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.creditScoreLogs is Result.Success)
        assertEquals(driverId, (state.creditScoreLogs as Result.Success).data.first().driverId)
    }

    // ============================================================
    // 🔹 UPDATE
    // ============================================================
    @Test
    fun `updateCreditScoreLog emits Success updates currentLog`() = runTest(dispatcher) {
        val id = 1
        val request = UpdateCreditScoreLogRequest(
            scoreChange = 20,
            notes = "Performa meningkat",
            actionType = "Bonus"
        )
        val updatedLog = CreditScoreLog(
            id = id,
            driverId = 3,
            actionType = "Bonus",
            scoreChange = "+20",
            notes = "Performa meningkat",
            createdAt = "2025-11-08T12:00:00Z"
        )

        whenever(updateUseCase(token, id, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(updatedLog))
        })

        viewModel.updateCreditScoreLog(token, id, request)
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.currentLog is Result.Success)
        assertEquals("Bonus", (state.currentLog as Result.Success).data?.actionType)
        assertEquals("Credit score log berhasil diperbarui", state.successMessage)
    }

    // ============================================================
    // 🔹 DELETE
    // ============================================================
    @Test
    fun `deleteCreditScoreLog emits Success updates successMessage`() = runTest(dispatcher) {
        val id = 5
        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success("Log berhasil dihapus"))
        })

        viewModel.deleteCreditScoreLog(token, id)
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("Credit score log berhasil dihapus", state.successMessage)
    }

    @Test
    fun `deleteCreditScoreLog emits Error updates errorMessage`() = runTest(dispatcher) {
        val id = 5
        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Gagal menghapus log"))
        })

        viewModel.deleteCreditScoreLog(token, id)
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("Gagal menghapus log", state.errorMessage)
    }
}
