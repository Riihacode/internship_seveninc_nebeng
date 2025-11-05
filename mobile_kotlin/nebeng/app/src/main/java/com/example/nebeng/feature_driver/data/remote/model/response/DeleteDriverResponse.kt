package com.example.nebeng.feature_driver.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteDriverResponse(
	@field:SerializedName("message") val message: String
)
