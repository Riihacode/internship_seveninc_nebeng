package com.example.nebeng.feature_payment_method.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ReadAllPaymentMethodResponse(

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class DataItem(

	@field:SerializedName("account_number")
	val accountNumber: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("account_name")
	val accountName: String,

	@field:SerializedName("bank_name")
	val bankName: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int
)
