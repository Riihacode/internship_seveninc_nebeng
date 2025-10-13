package com.example.nebeng.core.session

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val userPrefs: UserPreferences
) {
    suspend fun saveSession(
        userId: Int,
        username: String,
        role: String,
        isLoggedIn: Boolean
    ) {
        userPrefs.saveUserSession(
            userId,
            username,
            role,
            isLoggedIn
        )
    }

    suspend fun clearSession() = userPrefs.clearSeassion()

    val userIdFlow      = userPrefs.userIdFlow
    val usernameFlow    = userPrefs.usernameFlow
    val roleFlow        = userPrefs.roleFlow
    val isLoggedInFlow  = userPrefs.isLoggedInFlow
}