package com.example.nebeng.feature_a_homepage.domain.interactor.customer.nebeng_motor

import com.example.nebeng.feature_a_homepage.domain.session.customer.nebeng_motor.BookingSession
import com.example.nebeng.feature_a_homepage.domain.usecase.NebengMotorUseCases
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.PaymentStatus
import com.example.nebeng.feature_a_homepage.domain.mapper.*
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PassengerRideCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PaymentMethodCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.TerminalArrivalCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.TerminalDepartureCustomer
import com.example.nebeng.feature_a_homepage.domain.session.customer.nebeng_motor.BookingStep
import com.example.nebeng.feature_passenger_ride_booking.data.remote.model.request.CreatePassengerRideBookingRequest
import com.example.nebeng.feature_passenger_transaction.data.remote.model.request.CreatePassengerTransactionRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay

@HiltViewModel
class NebengMotorBookingInteractor @Inject constructor(
    val useCases: NebengMotorUseCases
) {
    /**
     * LOAD INITIAL (Step pertama)
     * Mengambil semua data statis yang dibutuhkan sebelum user memilih ride.
     */
    suspend fun loadInitial(
        token: String,
        customerId: Int
    ): Result<BookingSession> {
        return try {
            // parallel tapi sequential dulu (biar mudah debugging)
            val customerResult      = useCases.getByIdCustomer(token, customerId).first()
            val ridesResult         = useCases.getAllPassengerRide(token).first()
            val terminalsResult     = useCases.getAllTerminal(token).first()
            val paymentMethodResult = useCases.getAllPaymentMethod(token).first()
            val pricingResult       = useCases.getALlPassengerPricing(token).first()

            if (
                customerResult is Result.Success &&
                ridesResult is Result.Success &&
                terminalsResult is Result.Success &&
                paymentMethodResult is Result.Success &&
                pricingResult is Result.Success
            ) {
                Result.Success(
                    BookingSession(
                        listPassengerRides      = ridesResult.data.map { it.toPassengerRideCustomer() },
                        listTerminals           = terminalsResult.data.map { it.toTerminalDepartureCustomer() },
                        listPaymentMethods      = paymentMethodResult.data.map { it.toPaymentMethoCustomer() },
                        listPassengerPricing    = pricingResult.data.map { it.toPassengerPricingCustomer() },
                        customer                = customerResult.data.toCustomerCurrentCustomer()
                    )
                )
            } else {
                Result.Error("Gagal memuat data awal Nebeng Motor")
            }
        } catch (e: Exception) {
            Result.Error("Terjadi kesalahan loadInitial(): ${e.message}", e)
        }
    }

    suspend fun selectRide(
        token: String,
        session: BookingSession,
        ride: PassengerRideCustomer,
        onUpdated: (BookingSession) -> Unit
    ) {
        // 1. Save selected ride
        var updated = session.copy(selectedRide = ride)

        // 2. Cari terminal departure & arrival dari session.listTerminals
        val departureTerminal = session.listTerminals
            .find { it.idDepartureTerminal == ride.departureTerminalId }
            ?: TerminalDepartureCustomer.empty()

        val arrivalTerminal = session.listTerminals
            .find { it.idDepartureTerminal == ride.arrivalTerminalId } // masih pakai departure ID karena session hanya menyimpan satu jenis mapping
            ?.let {
                // Convert TerminalDeparture → TerminalArrival
                TerminalArrivalCustomer(
                    idArrivalTerminal = it.idDepartureTerminal,
                    arrivalTerminalName = it.departureTerminalName,
                    terminalFullAddress = it.terminalFullAddress,
                    terminalRegencyId = it.terminalRegencyId,
                    terminalLongitude = it.terminalLongitude,
                    terminalLatitude = it.terminalLatitude,
                    publicTerminalSubtype = it.publicTerminalSubtype,
                    terminalType = it.terminalType
                )
            }
            ?: TerminalArrivalCustomer.empty()

        updated = updated.copy(
            selectedDepartureTerminal = departureTerminal,
            selectedArrivalTerminal = arrivalTerminal
        )

        // 3. Cari passengerPricing berdasarkan terminal dep-arr
        val pricing = session.listPassengerPricing.find {
            it.departureTerminalId == ride.departureTerminalId &&
                    it.arrivalTerminalId == ride.arrivalTerminalId
        }

        updated = updated.copy(
            selectedPricing = pricing,
            totalPrice = pricing?.pricePerSeat ?: 0
        )

        // 4. Change step
        updated = updated.copy(step = BookingStep.CONFIRM_PRICE)

        onUpdated(updated)
    }

    fun selectPaymentMethod(
        session: BookingSession,
        paymentMethod: PaymentMethodCustomer,
        onUpdated: (BookingSession) -> Unit
    ) {
        val updated = session.copy(
            selectedPaymentMethod = paymentMethod,
            step = BookingStep.CONFIRM_PRICE // tetap di halaman konfirmasi harga
        )

        onUpdated(updated)
    }

    suspend fun confirmBooking(
        token: String,
        session: BookingSession,
        useCases: NebengMotorUseCases,
        onUpdated: (BookingSession) -> Unit,
        onError: (String) -> Unit
    ) {
        // Validasi
        val ride = session.selectedRide ?: return onError("Ride belum dipilih")
        val customer = session.customer ?: return onError("Customer null (invalid session)")
        val pricing = session.selectedPricing ?: return onError("Pricing belum dipilih")

        // Request body booking
        val reqBooking = CreatePassengerRideBookingRequest(
            passengerRideId = ride.idPassengerRide,
            totalPrice = session.totalPrice,
            customerId = customer.idCustomer,
            seatsReserved = 1,
            status = "Pending"
        )

        // Panggil API
        useCases.createPassengerRideBooking(token, reqBooking).collect { result ->
            when (result) {
                is Result.Loading -> {}
                is Result.Error -> onError(result.message ?: "Gagal membuat booking")
                is Result.Success -> {
                    val bookingSummary = result.data
                    val booking = bookingSummary.toPassengerRideBookingCustomer()

                    val updated = session.copy(
                        booking = booking,
                        step = BookingStep.BOOKING_CREATED    // belum ke transaction dulu
                    )
                    onUpdated(updated)

                    // lanjut otomatis → create transaction
                    createTransactionAfterBooking(
                        token = token,
                        session = updated,
                        useCases = useCases,
                        onUpdated = onUpdated,
                        onError = onError
                    )
                }
            }
        }
    }

    private suspend fun createTransactionAfterBooking(
        token: String,
        session: BookingSession,
        useCases: NebengMotorUseCases,
        onUpdated: (BookingSession) -> Unit,
        onError: (String) -> Unit
    ) {
        val booking = session.booking ?: return onError("Booking tidak ditemukan")
        val customer = session.customer ?: return onError("Customer null")
        val payment = session.selectedPaymentMethod ?: return onError("Payment method tidak dipilih")

        val request = CreatePassengerTransactionRequest(
            passengerRideBookingId = booking.idBooking,
            customerId = customer.idCustomer,
            totalAmount = session.totalPrice,
            paymentMethodId = payment.idPaymentMethod,
            paymentStatus = "Pending",     // BENAR, backend nanti overwrite setelah Midtrans settle
            creditUsed = 0,
            transactionDate = "",
            paymentProofImg = ""                 // fix
        )

        useCases.createPassengerTransaction(token, request).collect { result ->
            when (result) {
                is Result.Loading -> {}
                is Result.Error -> onError(result.message ?: "Gagal membuat transaksi")
                is Result.Success -> {
                    val trx = result.data.toPassengerTransactionCustomer()

                    val updated = session.copy(
                        transaction = trx,
                        step = BookingStep.WAITING_PAYMENT
                    )

                    onUpdated(updated)
                }
            }
        }
    }

    /**
     * Polling status transaksi ke backend sampai berubah menjadi SUCCESS / FAILED / EXPIRED
     *
     * @param maxAttempts 20 attempts × 3 detik ≈ 60 detik monitoring
     */
    suspend fun monitorTransactionStatus(
        token: String,
        session: BookingSession,
        onUpdate: (BookingSession) -> Unit,
        maxAttempts: Int = 20
    ) {
        if (maxAttempts <= 0) return

        val trx = session.transaction ?: run {
            onUpdate(session.copy(step = BookingStep.FAILED))
            return
        }

        val transactionId = trx.idPassengerTransaction

        useCases.getByIdPassengerTransaction(token, transactionId).collect { result ->
            when (result) {

                is Result.Loading -> {
                    // tidak perlu update session saat loading
                }

                is Result.Error -> {
                    // gagal fetch status → dianggap gagal proses
                    onUpdate(
                        session.copy(
                            step = BookingStep.FAILED
                        )
                    )
                    return@collect
                }

                is Result.Success -> {
                    val latest = result.data.toPassengerTransactionCustomer()
                    val paymentStatus = latest.paymentStatus

                    when (paymentStatus) {

                        PaymentStatus.PENDING -> {
                            // belum berubah → masih tunggu
                            onUpdate(
                                session.copy(
                                    transaction = latest,
                                    step = BookingStep.WAITING_PAYMENT
                                )
                            )
                            delay(3000)
                            monitorTransactionStatus(token, session.copy(transaction = latest), onUpdate, maxAttempts - 1)
                        }

                        PaymentStatus.DITERIMA,
                        PaymentStatus.CREDITED -> {
                            // pembayaran sukses
                            onUpdate(
                                session.copy(
                                    transaction = latest,
                                    step = BookingStep.PAYMENT_CONFIRMED
                                )
                            )
                        }

                        PaymentStatus.DITOLAK -> {
                            // pembayaran ditolak
                            onUpdate(
                                session.copy(
                                    transaction = latest,
                                    step = BookingStep.FAILED
                                )
                            )
                        }

                        else -> {
                            // unknown state
                            onUpdate(
                                session.copy(
                                    transaction = latest,
                                    step = BookingStep.FAILED
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    suspend fun observeRideProgress(
        token: String,
        session: BookingSession,
        onUpdate: (BookingSession) -> Unit,
        maxAttempts: Int = 30 // sekitar 30 × 3 detik = 90 detik
    ) {
        if (maxAttempts <= 0) return

        val booking = session.booking ?: run {
            onUpdate(session.copy(step = BookingStep.FAILED))
            return
        }

        val bookingId = booking.idBooking

        useCases.getByIdPassengerRideBooking(token, bookingId).collect { result ->
            when (result) {

                is Result.Loading -> Unit

                is Result.Error -> {
                    onUpdate(session.copy(step = BookingStep.FAILED))
                    return@collect
                }

                is Result.Success -> {
                    val latest = result.data.toPassengerRideBookingCustomer()
                    val bookingStatus = latest.bookingStatus

                    when (bookingStatus) {

                        // masih menunggu driver/backend
                        BookingStatus.PENDING -> {
                            onUpdate(
                                session.copy(
                                    booking = latest,
                                    step = BookingStep.WAITING_RIDE_ACCEPTANCE
                                )
                            )
                            delay(3000)
                            observeRideProgress(token, session.copy(booking = latest), onUpdate, maxAttempts - 1)
                        }

                        // berhasil diterima → perjalanan siap dilanjutkan
                        BookingStatus.DITERIMA -> {
                            onUpdate(
                                session.copy(
                                    booking = latest,
                                    step = BookingStep.RIDE_ACCEPTED
                                )
                            )
                        }

                        // booking gagal
                        BookingStatus.DITOLAK -> {
                            onUpdate(
                                session.copy(
                                    booking = latest,
                                    step = BookingStep.FAILED
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
