package com.example.nebeng.feature_rating.domain.usecase

import com.example.nebeng.core.common.Result
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
class ReadByDriverIdRatingUseCaseTest {

    @Mock
    private lateinit var repository: RatingRepository

    private lateinit var useCase: ReadByDriverIdRatingUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadByDriverIdRatingUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when driver ratings loaded`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val driverId = 1
        val ratings = listOf(
            Rating(1, driverId, 2, 5, "Top", "2025-11-05T12:00:00Z"),
            Rating(2, driverId, 3, 4, "Good", "2025-11-05T13:00:00Z")
        )

        val flow: Flow<Result<List<Rating>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(ratings))
        }

        whenever(repository.getRatingsByDriverId(token, driverId)).thenReturn(flow)

        val results = mutableListOf<Result<List<Rating>>>()
        useCase(token, driverId).collect { results.add(it) }

        assertTrue(results.last() is Result.Success)
        assertEquals(2, (results.last() as Result.Success).data.size)
    }

    @Test
    fun `invoke should emit Error when failed`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val driverId = 1
        val flow: Flow<Result<List<Rating>>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to load driver ratings"))
        }

        whenever(repository.getRatingsByDriverId(token, driverId)).thenReturn(flow)

        val results = mutableListOf<Result<List<Rating>>>()
        useCase(token, driverId).collect { results.add(it) }

        assertTrue(results.last() is Result.Error)
        assertEquals("Failed to load driver ratings", (results.last() as Result.Error).message)
    }
}