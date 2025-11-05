package com.example.nebeng.feature_customer.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class PatchAddCreditAmountCustomerRequest(
	@field:SerializedName("amount") val amount: Int
)
