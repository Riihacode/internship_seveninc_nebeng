package com.example.nebeng.core.session.data

import com.example.nebeng.feature_a_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val userPrefs: UserPreferences
) {
    suspend fun saveSession(
        userId: Int,
        name: String,
        username: String,
        user_type: String,
        isLoggedIn: Boolean,
        token: String = ""
    ) {
        userPrefs.saveUserSession(
            userId,
            name,
            username,
            user_type,
            isLoggedIn,
            token
        )
    }

    // ✅ Aliran token dari DataStore
    val tokenFLow: Flow<String?> = userPrefs.tokenFlow

    // ✅ Getter / Setter opsional
    suspend fun getToken(): String? = userPrefs.getToken()
    suspend fun saveToken(token: String) = userPrefs.saveToken(token)

    suspend fun getUser(): Auth? {
        val id = userIdFlow.first()
        val name = nameFlow.first()
        val username = usernameFlow.first()
        val userType = userTypeFlow.first()
        val isLoggedIn = isLoggedInFlow.first()

        return if (isLoggedIn) {
            Auth(
                id = id,
                name = name,
                username = username,
                email = "$username@mail.com", // optional dummy
                user_type = userType
            )
        } else null
    }

    suspend fun clearSession() = userPrefs.clearSession()

    val userIdFlow      = userPrefs.userIdFlow
    val nameFlow        = userPrefs.nameFlow
    val usernameFlow    = userPrefs.usernameFlow
    val userTypeFlow    = userPrefs.userTypeFlow
    val isLoggedInFlow  = userPrefs.isLoggedInFlow
}