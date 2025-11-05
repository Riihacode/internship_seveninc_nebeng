package com.example.nebeng.feature_customer.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteCustomerResponse(
	@field:SerializedName("message") val message: String
)
