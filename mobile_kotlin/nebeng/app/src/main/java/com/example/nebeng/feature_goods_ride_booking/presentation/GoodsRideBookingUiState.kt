package com.example.nebeng.feature_goods_ride_booking.presentation

import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBooking
import com.example.nebeng.feature_goods_ride_booking.domain.model.GoodsRideBookingFull

data class GoodsRideBookingUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    // Data list & detail
    val bookings: List<GoodsRideBookingFull> = emptyList(),
    val selectedBooking: GoodsRideBookingFull? = null,
    val createdBooking: GoodsRideBooking? = null,

    // Flags
    val isCreated: Boolean = false,
    val isUpdated: Boolean = false,
    val isDeleted: Boolean = false
)
