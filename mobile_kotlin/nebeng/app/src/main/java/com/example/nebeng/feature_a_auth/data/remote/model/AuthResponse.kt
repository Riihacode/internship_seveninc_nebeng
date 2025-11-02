package com.example.nebeng.feature_a_auth.data.remote.model

data class AuthResponse(
    val success: Boolean,
    val message: String,
    val data: Data?
) {
    data class Data(
        val user: User?,
        val token: String?
    )

    data class User(
        val id: Int,
        val name: String,
        val username: String,
        val email: String,
        val user_type: String,
        val banned: Int,
        val created_at: String,
        val updated_at: String
    )
}
