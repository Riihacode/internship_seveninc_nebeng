package com.example.nebeng.feature_goods_transaction.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateGoodsTransactionRequest(
	@field:SerializedName("transaction_date") val transactionDate: String,
	@field:SerializedName("payment_method_id") val paymentMethodId: Int,
	@field:SerializedName("total_amount") val totalAmount: Int,
	@field:SerializedName("payment_status") val paymentStatus: String,
	@field:SerializedName("goods_ride_booking_id") val goodsRideBookingId: Int,
	@field:SerializedName("customer_id") val customerId: Int,
	@field:SerializedName("payment_proof_img") val paymentProofImg: String,
	@field:SerializedName("credit_used") val creditUsed: Int
)
