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
class ReadByIdRatingUseCaseTest {

    @Mock
    private lateinit var repository: RatingRepository

    private lateinit var useCase: ReadByIdRatingUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ReadByIdRatingUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when rating found`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1
        val rating = Rating(id, 1, 2, 5, "Perfect", "2025-11-05T12:00:00Z")

        val flow: Flow<Result<Rating>> = flow {
            emit(Result.Loading)
            emit(Result.Success(rating))
        }

        whenever(repository.getRatingById(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Rating>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.last() is Result.Success)
        assertEquals(5, (results.last() as Result.Success).data.rating)
    }

    @Test
    fun `invoke should emit Error when rating not found`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 999
        val flow: Flow<Result<Rating>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Rating not found"))
        }

        whenever(repository.getRatingById(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Rating>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.last() is Result.Error)
        assertEquals("Rating not found", (results.last() as Result.Error).message)
    }
}