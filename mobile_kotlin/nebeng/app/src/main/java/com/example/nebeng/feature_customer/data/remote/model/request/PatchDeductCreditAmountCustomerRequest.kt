package com.example.nebeng.feature_customer.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class PatchDeductCreditAmountCustomerRequest(
	@field:SerializedName("amount") val amount: Int
)
