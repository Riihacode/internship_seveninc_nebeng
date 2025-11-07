package com.example.nebeng.feature_rating.presentation

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.remote.model.request.CreateRatingRequest
import com.example.nebeng.feature_rating.data.remote.model.request.UpdateRatingRequest
import com.example.nebeng.feature_rating.domain.model.Rating
import com.example.nebeng.feature_rating.domain.usecase.RatingUseCases
import com.example.nebeng.feature_rating.domain.usecase.*
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
class RatingViewModelTest {
    private lateinit var viewModel: RatingViewModel

    @Mock private lateinit var createUseCase: CreateRatingUseCase
    @Mock private lateinit var readAllUseCase: ReadAllRatingUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdRatingUseCase
    @Mock private lateinit var readByDriverIdUseCase: ReadByDriverIdRatingUseCase
    @Mock private lateinit var updateUseCase: UpdateRatingUseCase
    @Mock private lateinit var deleteUseCase: DeleteRatingUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        val useCases = RatingUseCases(
            create = createUseCase,
            readAll = readAllUseCase,
            readById = readByIdUseCase,
            readByDriverId = readByDriverIdUseCase,
            update = updateUseCase,
            delete = deleteUseCase
        )
        viewModel = RatingViewModel(useCases)
    }

    /* ============================================================
       🔹 Test getAllRatings()
       ============================================================ */
    @Test
    fun `getAllRatings emits Success updates state`() = runTest(testDispatcher) {
        val token = "token"
        val fakeRatings = listOf(
            Rating(1, 1, 2, 5, "Bagus", "2025-11-05T12:00:00Z")
        )
        whenever(readAllUseCase(token)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeRatings))
        })

        viewModel.getAllRatings(token)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.ratings is Result.Success)
        assertEquals(1, (uiState.ratings as Result.Success).data.size)
        assertEquals(false, uiState.isLoading)
    }

    @Test
    fun `getAllRatings emits Error updates errorMessage`() = runTest(testDispatcher) {
        val token = "token"
        whenever(readAllUseCase(token)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to fetch ratings"))
        })

        viewModel.getAllRatings(token)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.ratings is Result.Error)
        assertEquals("Failed to fetch ratings", uiState.errorMessage)
    }

    /* ============================================================
       🔹 Test createRating()
       ============================================================ */
    @Test
    fun `createRating emits Success updates currentRating`() = runTest(testDispatcher) {
        val token = "token"
        val request = CreateRatingRequest(
            feedback = "Luar biasa",
            driverId = 1,
            rating = 5,
            createdAt = "2025-11-05T12:00:00Z",
            customerId = 2
        )
        val fakeRating = Rating(1, 1, 2, 5, "Luar biasa", "2025-11-05T12:00:00Z")

        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(fakeRating))
        })

        viewModel.createRating(token, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentRating is Result.Success)
        assertEquals("Luar biasa", (uiState.currentRating as Result.Success).data?.feedback)
        assertEquals("Rating berhasil dikirim", uiState.successMessage)
    }

    @Test
    fun `createRating emits Error updates errorMessage`() = runTest(testDispatcher) {
        val token = "token"
        val request = CreateRatingRequest(
            feedback = "Buruk",
            driverId = 1,
            rating = 1,
            createdAt = "2025-11-05T12:00:00Z",
            customerId = 2
        )

        whenever(createUseCase(token, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Gagal mengirim rating"))
        })

        viewModel.createRating(token, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentRating is Result.Error)
        assertEquals("Gagal mengirim rating", uiState.errorMessage)
    }

    /* ============================================================
       🔹 Test updateRating()
       ============================================================ */
    @Test
    fun `updateRating emits Success updates state`() = runTest(testDispatcher) {
        val token = "token"
        val id = 1
        val request = UpdateRatingRequest(feedback = "Diperbaiki", rating = 4)
        val updatedRating = Rating(id, 1, 2, 4, "Diperbaiki", "2025-11-05T12:00:00Z")

        whenever(updateUseCase(token, id, request)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(updatedRating))
        })

        viewModel.updateRating(token, id, request)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState.currentRating is Result.Success)
        assertEquals("Diperbaiki", (uiState.currentRating as Result.Success).data?.feedback)
        assertEquals("Rating berhasil diperbarui", uiState.successMessage)
    }

    /* ============================================================
       🔹 Test deleteRating()
       ============================================================ */
    @Test
    fun `deleteRating emits Success updates successMessage`() = runTest(testDispatcher) {
        val token = "token"
        val id = 1

        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        })

        viewModel.deleteRating(token, id)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Rating berhasil dihapus", uiState.successMessage)
        assertEquals(false, uiState.isLoading)
    }

    @Test
    fun `deleteRating emits Error updates errorMessage`() = runTest(testDispatcher) {
        val token = "token"
        val id = 1

        whenever(deleteUseCase(token, id)).thenReturn(flow {
            emit(Result.Loading)
            emit(Result.Error("Gagal menghapus rating"))
        })

        viewModel.deleteRating(token, id)
        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Gagal menghapus rating", uiState.errorMessage)
    }
}