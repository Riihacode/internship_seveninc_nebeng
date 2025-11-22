package com.example.nebeng.feature_payment_method.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeletePaymentMethodResponse(
	@field:SerializedName("message") val message: String
)
