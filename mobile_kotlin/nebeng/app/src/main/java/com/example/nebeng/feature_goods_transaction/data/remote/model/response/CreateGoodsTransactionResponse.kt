package com.example.nebeng.feature_goods_transaction.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CreateGoodsTransactionResponse(
	@field:SerializedName("data") val data: CreateData,
	@field:SerializedName("success") val success: Boolean,
	@field:SerializedName("message") val message: String
)

data class CreateData(
	@field:SerializedName("transaction_date") val transactionDate: String,
	@field:SerializedName("transaction_code") val transactionCode: String,
	@field:SerializedName("payment_method_id") val paymentMethodId: Int,
	@field:SerializedName("updated_at") val updatedAt: String,
	@field:SerializedName("total_amount") val totalAmount: Int,
	@field:SerializedName("payment_status") val paymentStatus: String,
	@field:SerializedName("created_at") val createdAt: String,
	@field:SerializedName("goods_ride_booking_id") val goodsRideBookingId: Int,
	@field:SerializedName("id") val id: Int,
	@field:SerializedName("customer_id") val customerId: Int,
	@field:SerializedName("payment_proof_img") val paymentProofImg: String,
	@field:SerializedName("credit_used") val creditUsed: Int
)
