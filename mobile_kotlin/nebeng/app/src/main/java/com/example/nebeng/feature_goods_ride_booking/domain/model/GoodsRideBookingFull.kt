package com.example.nebeng.feature_goods_ride_booking.domain.model

import com.example.nebeng.feature_customer.domain.model.Customer
import com.example.nebeng.feature_goods_ride.domain.model.GoodsRide
import com.example.nebeng.feature_goods_transaction.domain.model.GoodsTransaction

// -------------------- DOMAIN AGGREGATE MODEL --------------------
data class GoodsRideBookingFull(
    val booking: GoodsRideBooking,
    val customer: Customer,
    val goodsRide: GoodsRide,
    val goodsTransaction: GoodsTransaction?,
    val bookingCode: String
)