package com.example.nebeng.feature_rating.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.remote.model.request.CreateRatingRequest
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
class CreateRatingUseCaseTest {

    @Mock
    private lateinit var repository: RatingRepository

    private lateinit var useCase: CreateRatingUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CreateRatingUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when rating created successfully`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val request = CreateRatingRequest(
            feedback = "Bagus sekali",
            driverId = 1,
            rating = 5,
            createdAt = "2025-11-05T12:00:00Z",
            customerId = 2
        )

        val rating = Rating(
            id = 1,
            driverId = 1,
            customerId = 2,
            rating = 5,
            feedback = "Bagus sekali",
            createdAt = "2025-11-05T12:00:00Z"
        )

        val flow: Flow<Result<Rating>> = flow {
            emit(Result.Loading)
            emit(Result.Success(rating))
        }

        whenever(repository.createRating(token, request)).thenReturn(flow)

        val results = mutableListOf<Result<Rating>>()
        useCase(token, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(rating.feedback, (results.last() as Result.Success).data.feedback)
    }

    @Test
    fun `invoke should emit Error when repository returns failure`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val request = CreateRatingRequest(
            feedback = "Kurang memuaskan",
            driverId = 1,
            rating = 2,
            createdAt = "2025-11-05T12:00:00Z",
            customerId = 2
        )

        val flow: Flow<Result<Rating>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to create rating"))
        }

        whenever(repository.createRating(token, request)).thenReturn(flow)

        val results = mutableListOf<Result<Rating>>()
        useCase(token, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
        assertEquals("Failed to create rating", (results.last() as Result.Error).message)
    }
}