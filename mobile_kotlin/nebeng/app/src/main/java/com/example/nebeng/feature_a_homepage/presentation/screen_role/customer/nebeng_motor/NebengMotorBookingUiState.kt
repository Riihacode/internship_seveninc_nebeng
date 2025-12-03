package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor

import com.example.nebeng.feature_a_homepage.domain.aggregator.customer.page_02.PassengerRideAggregator
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PassengerRideCustomer
import com.example.nebeng.feature_a_homepage.domain.model.nebeng_motor.customer.PaymentMethodCustomer
import com.example.nebeng.core.common.Result

data class NebengMotorBookingUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    // list rides dengan pagination
    val rides: List<PassengerRideCustomer> = emptyList(),
    val isEndReached: Boolean = false,

    // payment method
    val paymentMethods: List<PaymentMethodCustomer> = emptyList(),

    // setelah aggregator
//    val aggregatedRideDetail: PassengerRideAggregator.Result? = null,

    // apakah transaksi sudah selesai
    val transactionSuccess: Boolean = false
)