package com.example.nebeng.feature_goods_transaction.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateGoodsTransactionRequest(
	@field:SerializedName("total_amount") val totalAmount: Int,
	@field:SerializedName("payment_status") val paymentStatus: String,
	@field:SerializedName("payment_proof_img") val paymentProofImg: String,
	@field:SerializedName("credit_used") val creditUsed: Int
)
