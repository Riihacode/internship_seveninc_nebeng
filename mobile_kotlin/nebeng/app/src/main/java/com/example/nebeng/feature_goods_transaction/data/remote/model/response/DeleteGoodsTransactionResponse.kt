package com.example.nebeng.feature_goods_transaction.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteGoodsTransactionResponse(
	@field:SerializedName("message") val message: String
)
