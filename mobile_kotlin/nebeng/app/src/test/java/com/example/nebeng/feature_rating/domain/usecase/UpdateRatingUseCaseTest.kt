package com.example.nebeng.feature_rating.domain.usecase
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.remote.model.request.UpdateRatingRequest
import com.example.nebeng.feature_rating.data.repository.RatingRepository
import com.example.nebeng.feature_rating.domain.model.Rating
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateRatingUseCaseTest {

    @Mock
    private lateinit var repository: RatingRepository

    private lateinit var useCase: UpdateRatingUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = UpdateRatingUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when rating updated`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val request = UpdateRatingRequest(feedback = "Sangat baik", rating = 5)
        val updated = Rating(id, 1, 2, 5, "Sangat baik", "2025-11-05T12:00:00Z")

        val flow: Flow<Result<Rating>> = flow {
            emit(Result.Loading)
            emit(Result.Success(updated))
        }

        whenever(repository.updateRating(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<Rating>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.last() is Result.Success)
        assertEquals("Sangat baik", (results.last() as Result.Success).data.feedback)
    }

    @Test
    fun `invoke should emit Error when update fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val request = UpdateRatingRequest(feedback = "Buruk", rating = 1)

        val flow: Flow<Result<Rating>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to update rating"))
        }

        whenever(repository.updateRating(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<Rating>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.last() is Result.Error)
        assertEquals("Failed to update rating", (results.last() as Result.Error).message)
    }
}