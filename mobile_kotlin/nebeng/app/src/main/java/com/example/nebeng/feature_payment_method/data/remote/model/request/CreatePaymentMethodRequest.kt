package com.example.nebeng.feature_payment_method.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreatePaymentMethodRequest(

	@field:SerializedName("account_number")
	val accountNumber: String,

	@field:SerializedName("account_name")
	val accountName: String,

	@field:SerializedName("bank_name")
	val bankName: String
)
