package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_a_homepage.domain.session.customer.nebeng_motor.BookingSession
import com.example.nebeng.feature_a_homepage.domain.usecase.NebengMotorUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_homepage.domain.aggregator.customer.page_02.PassengerRideAggregator
import com.example.nebeng.feature_a_homepage.domain.interactor.customer.nebeng_motor.NebengMotorBookingInteractor
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PassengerRideCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PassengerTransactionCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PaymentMethodCustomer
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
class NebengMotorBookingViewModel @Inject constructor(
    private val interactor: NebengMotorBookingInteractor
) : ViewModel() {

    private val _session = MutableStateFlow(BookingSession())
    val session = _session.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private var token: String = ""
    private var customerId: Int = -1


    /** 📌 dipanggil pertama kali saat halaman NebengMotor dibuka */
    fun start(token: String, customerId: Int) {
        this.token = token
        this.customerId = customerId

        viewModelScope.launch {
            _loading.value = true
            when (val res = interactor.loadInitial(token, customerId)) {
                is Result.Error -> _error.value = res.message
                is Result.Success -> _session.value = res.data
                else -> {}
            }
            _loading.value = false
        }
    }


    /** 📌 user klik card ride */
    fun selectRide(ride: PassengerRideCustomer) {
        viewModelScope.launch {
            interactor.selectRide(
                token = token,
                session = _session.value,
                ride = ride,
                onUpdated = { updated -> _session.value = updated }
            )
        }
    }


    /** 📌 user klik radio button payment */
    fun selectPaymentMethod(payment: PaymentMethodCustomer) {
        interactor.selectPaymentMethod(
            session = _session.value,
            paymentMethod = payment,
            onUpdated = { updated -> _session.value = updated }
        )
    }


    /** 📌 user klik tombol “Bayar / Book” */
    fun confirmBooking() {
        viewModelScope.launch {
            _loading.value = true
            interactor.confirmBooking(
                token = token,
                session = _session.value,
                useCases = interactor.useCases, // karena interactor memerlukan useCases
                onUpdated = { updated -> _session.value = updated },
                onError = { message -> _error.value = message }
            )
            _loading.value = false
        }
    }


    /** 📌 mulai polling pembayaran */
    fun startMonitorTransaction() {
        viewModelScope.launch {
            interactor.monitorTransactionStatus(
                token = token,
                session = _session.value,
                onUpdate = { updated -> _session.value = updated }
            )
        }
    }


    /** 📌 mulai polling bookingStatus menunggu driver accept */
    fun startObserveRideProgress() {
        viewModelScope.launch {
            interactor.observeRideProgress(
                token = token,
                session = _session.value,
                onUpdate = { updated -> _session.value = updated }
            )
        }
    }


    /** 📌 reset setelah selesai (berhasil / gagal) */
    fun reset() {
        _session.value = BookingSession()
        _error.value = null
        _loading.value = false
    }
}
