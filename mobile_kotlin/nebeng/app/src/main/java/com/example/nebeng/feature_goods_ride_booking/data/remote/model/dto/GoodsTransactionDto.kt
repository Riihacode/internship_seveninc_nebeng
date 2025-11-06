package com.example.nebeng.feature_goods_ride_booking.data.remote.model.dto

import com.example.nebeng.core.utils.PaymentStatus
import com.google.gson.annotations.SerializedName

data class GoodsTransactionDto(
    @field:SerializedName("transaction_date") val transactionDate: String,
    @field:SerializedName("transaction_code") val transactionCode: String,
    @field:SerializedName("payment_method_id") val paymentMethodId: Int,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("total_amount") val totalAmount: Int,
    @field:SerializedName("payment_status") val paymentStatus: PaymentStatus,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("goods_ride_booking_id") val goodsRideBookingId: Int,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("customer_id") val customerId: Int,
    @field:SerializedName("payment_proof_img") val paymentProofImg: Any,
    @field:SerializedName("credit_used") val creditUsed: Int
)
