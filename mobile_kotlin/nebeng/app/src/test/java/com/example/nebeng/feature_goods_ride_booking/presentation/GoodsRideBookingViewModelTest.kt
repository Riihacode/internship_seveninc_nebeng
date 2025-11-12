package com.example.nebeng.feature_goods_ride_booking.presentation

import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.CreateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.GoodsRideBookingUseCases
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.whenever
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_goods_ride_booking.data.remote.model.request.UpdateGoodsRideBookingRequest
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBookingFull
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.CreateGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.DeleteGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadAllGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadByDriverIdGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadByIdGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.ReadByStatusGoodsRideBookingUseCase
import com.example.nebeng.feature_goods_ride_booking.domain.usecase.UpdateGoodsRideBookingUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class GoodsRideBookingViewModelTest {
    @Mock
    private lateinit var createUseCase: CreateGoodsRideBookingUseCase
    @Mock private lateinit var readAllUseCase: ReadAllGoodsRideBookingUseCase
    @Mock private lateinit var readByIdUseCase: ReadByIdGoodsRideBookingUseCase
    @Mock private lateinit var readByDriverIdUseCase: ReadByDriverIdGoodsRideBookingUseCase
    @Mock private lateinit var readByStatusUseCase: ReadByStatusGoodsRideBookingUseCase
    @Mock private lateinit var updateUseCase: UpdateGoodsRideBookingUseCase
    @Mock private lateinit var deleteUseCase: DeleteGoodsRideBookingUseCase

    private lateinit var useCases: GoodsRideBookingUseCases
    private lateinit var viewModel: GoodsRideBookingViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        useCases = GoodsRideBookingUseCases(
            create = createUseCase,
            readAll = readAllUseCase,
            readById = readByIdUseCase,
            readByDriverId = readByDriverIdUseCase,
            readByStatus = readByStatusUseCase,
            update = updateUseCase,
            delete = deleteUseCase
        )
        viewModel = GoodsRideBookingViewModel(useCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region 🔹 CREATE
    @Test
    fun `create booking success updates UiState`() = runTest {
        val token = "token123"
        val request = CreateGoodsRideBookingRequest(
            goodsRideId = 101,
            itemWeight = 15,
            itemImg = "https://example.com/img.png",
            totalPrice = 120000,
            customerId = 10,
            itemDescription = "Laptop Box",
            status = "PENDING"
        )

        val booking = GoodsRideBooking(
            id = 1,
            goodsRideId = 101,
            customerId = 10,
            itemWeight = 15,
            itemDescription = "Laptop Box",
            itemImg = "https://example.com/img.png",
            totalPrice = 120000,
            status = BookingStatus.PENDING,
            createdAt = java.time.LocalDateTime.now(),
            updatedAt = java.time.LocalDateTime.now()
        )

        val flow: Flow<Result<GoodsRideBooking>> = flow {
            emit(Result.Loading)
            emit(Result.Success(booking))
        }

        whenever(createUseCase(token, request)).thenReturn(flow)

        viewModel.createBooking(token, request)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertTrue(ui.isCreated)
        assertEquals(booking, ui.createdBooking)
        assertEquals(null, ui.error)
    }

    @Test
    fun `create booking error updates UiState with error`() = runTest {
        val token = "token123"
        val request = CreateGoodsRideBookingRequest(
            goodsRideId = 101,
            itemWeight = 15,
            itemImg = "https://example.com/img.png",
            totalPrice = 120000,
            customerId = 10,
            itemDescription = "Laptop Box",
            status = "PENDING"
        )

        val flow: Flow<Result<GoodsRideBooking>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Network Error"))
        }

        whenever(createUseCase(token, request)).thenReturn(flow)

        viewModel.createBooking(token, request)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertEquals("Network Error", ui.error)
        assertTrue(!ui.isCreated)
    }
    // endregion


    // region 🔹 READ ALL
    @Test
    fun `read all success updates bookings list`() = runTest {
        val token = "token123"
        val booking = mockBookingFull()
        val flow: Flow<Result<List<GoodsRideBookingFull>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(listOf(booking)))
        }

        whenever(readAllUseCase(token)).thenReturn(flow)

        viewModel.getAllBookings(token)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertEquals(listOf(booking), ui.bookings)
        assertEquals(null, ui.error)
    }
    // endregion


    // region 🔹 READ BY ID
    @Test
    fun `read by id success updates selectedBooking`() = runTest {
        val token = "token"
        val id = 1
        val bookingFull = mockBookingFull()

        val flow: Flow<Result<GoodsRideBookingFull>> = flow {
            emit(Result.Loading)
            emit(Result.Success(bookingFull))
        }

        whenever(readByIdUseCase(token, id)).thenReturn(flow)

        viewModel.getBookingById(token, id)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertEquals(bookingFull, ui.selectedBooking)
        assertEquals(null, ui.error)
    }
    // endregion


    // region 🔹 READ BY DRIVER
    @Test
    fun `read by driver id success updates bookings`() = runTest {
        val token = "token"
        val driverId = 50
        val booking = mockBookingFull()
        val flow: Flow<Result<List<GoodsRideBookingFull>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(listOf(booking)))
        }

        whenever(readByDriverIdUseCase(token, driverId)).thenReturn(flow)

        viewModel.getBookingsByDriverId(token, driverId)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertEquals(listOf(booking), ui.bookings)
        assertEquals(null, ui.error)
    }
    // endregion


    // region 🔹 READ BY STATUS
    @Test
    fun `read by status success updates bookings`() = runTest {
        val token = "token"
        val booking = mockBookingFull()
        val flow: Flow<Result<List<GoodsRideBookingFull>>> = flow {
            emit(Result.Loading)
            emit(Result.Success(listOf(booking)))
        }

        whenever(readByStatusUseCase(token, BookingStatus.PENDING)).thenReturn(flow)

        viewModel.getBookingsByStatus(token, BookingStatus.PENDING)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertEquals(listOf(booking), ui.bookings)
        assertEquals(null, ui.error)
    }
    // endregion


    // region 🔹 UPDATE
    @Test
    fun `update success sets isUpdated to true`() = runTest {
        val token = "token"
        val id = 1
        val request = UpdateGoodsRideBookingRequest(
            itemWeight = 15,
            itemImg = "item.png",
            totalPrice = 120000,
            itemDescription = "Dokumen penting",
            status = "DITERIMA"
        )

        val booking = GoodsRideBooking(
            id = 1,
            goodsRideId = 101,
            customerId = 10,
            itemWeight = 15,
            itemDescription = "Dokumen penting",
            itemImg = "item.png",
            totalPrice = 120000,
            status = BookingStatus.DITERIMA,
            createdAt = java.time.LocalDateTime.now(),
            updatedAt = java.time.LocalDateTime.now()
        )

        val flow: Flow<Result<GoodsRideBooking>> = flow {
            emit(Result.Loading)
            emit(Result.Success(booking))
        }

        whenever(updateUseCase(token, id, request)).thenReturn(flow)

        viewModel.updateBooking(token, id, request)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertTrue(ui.isUpdated)
        assertEquals(null, ui.error)
    }

    // endregion


    // region 🔹 DELETE
    @Test
    fun `delete success sets isDeleted to true`() = runTest {
        val token = "token"
        val id = 1
        val flow: Flow<Result<String>> = flow {
            emit(Result.Loading)
            emit(Result.Success("Deleted successfully"))
        }

        whenever(deleteUseCase(token, id)).thenReturn(flow)

        viewModel.deleteBooking(token, id)
        advanceUntilIdle()

        val ui = viewModel.uiState.value
        assertTrue(ui.isDeleted)
        assertEquals(null, ui.error)
    }
    // endregion


    // region 🧩 Helper
    private fun mockBookingFull(): GoodsRideBookingFull {
        val booking = GoodsRideBooking(
            id = 1,
            goodsRideId = 101,
            customerId = 10,
            itemWeight = 15,
            itemDescription = "Laptop Box",
            itemImg = "https://example.com/img.png",
            totalPrice = 120000,
            status = BookingStatus.PENDING,
            createdAt = java.time.LocalDateTime.now(),
            updatedAt = java.time.LocalDateTime.now()
        )

        val mockCustomer = org.mockito.kotlin.mock<com.example.nebeng.feature_customer.domain.model.Customer>()
        val mockGoodsRide = org.mockito.kotlin.mock<com.example.nebeng.feature_goods_ride.domain.model.GoodsRide>()

        return GoodsRideBookingFull(
            booking = booking,
            customer = mockCustomer,
            goodsRide = mockGoodsRide,
            goodsTransaction = null,
            bookingCode = "BR12345"
        )
    }
    // endregion
}