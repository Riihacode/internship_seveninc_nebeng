package com.example.nebeng.feature_rating.domain.usecase
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_rating.data.repository.RatingRepository
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
class DeleteRatingUseCaseTest {
    @Mock
    private lateinit var repository: RatingRepository

    private lateinit var useCase: DeleteRatingUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = DeleteRatingUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when rating deleted`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }

        whenever(repository.deleteRating(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Boolean>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.last() is Result.Success)
        assertEquals(true, (results.last() as Result.Success).data)
    }

    @Test
    fun `invoke should emit Error when deletion fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val flow: Flow<Result<Boolean>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Failed to delete rating"))
        }

        whenever(repository.deleteRating(token, id)).thenReturn(flow)

        val results = mutableListOf<Result<Boolean>>()
        useCase(token, id).collect { results.add(it) }

        assertTrue(results.last() is Result.Error)
        assertEquals("Failed to delete rating", (results.last() as Result.Error).message)
    }
}