package com.example.nebeng.feature_goods_ride_booking.domain.model

import com.example.nebeng.core.utils.BookingStatus
import java.time.LocalDateTime

data class GoodsRideBooking(
    val id: Int,
    val goodsRideId: Int,
    val customerId: Int,
    val itemWeight: Int,
    val itemDescription: String,
    val itemImg: String?,
    val totalPrice: Int,
    val status: BookingStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
