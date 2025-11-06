package com.example.nebeng.feature_passenger_transaction.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeletePassengerTransactionResponse(
	@field:SerializedName("message") val message: String
)
