package com.example.nebeng.feature_passenger_ride_booking.data.remote.model.dto

import com.example.nebeng.core.utils.PaymentStatus
import com.google.gson.annotations.SerializedName

data class PassengerTransactionDto(
    @field:SerializedName("transaction_date") val transactionDate: String,
    @field:SerializedName("payment_method_id") val paymentMethodId: Int,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("total_amount") val totalAmount: Int,
    @field:SerializedName("payment_status") val paymentStatus: PaymentStatus,
    @field:SerializedName("passenger_ride_booking_id") val passengerRideBookingId: Int,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("customer_id") val customerId: Int,
    @field:SerializedName("payment_proof_img") val paymentProofImg: String?,
    @field:SerializedName("credit_used") val creditUsed: Int
)

