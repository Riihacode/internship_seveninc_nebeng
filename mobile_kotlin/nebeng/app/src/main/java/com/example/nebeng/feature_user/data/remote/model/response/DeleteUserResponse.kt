package com.example.nebeng.feature_user.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DeleteUserResponse(
	@field:SerializedName("message") val message: String
)
