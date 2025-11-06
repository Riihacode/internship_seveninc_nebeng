package com.example.nebeng.feature_passenger_transaction.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class PatchStatusByIdPassengerTransactionRequest(
	@field:SerializedName("status") val status: String
)
