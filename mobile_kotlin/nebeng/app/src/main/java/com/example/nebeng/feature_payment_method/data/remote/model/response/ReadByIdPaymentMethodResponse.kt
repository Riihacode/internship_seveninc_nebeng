package com.example.nebeng.feature_payment_method.data.remote.model.response

import com.example.nebeng.feature_payment_method.data.remote.model.dto.DataDto
import com.google.gson.annotations.SerializedName

data class ReadByIdPaymentMethodResponse(

	@field:SerializedName("data")
	val data: DataDto
)

