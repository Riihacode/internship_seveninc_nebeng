package com.example.nebeng.core.session

import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class UserPreferencesRepository @Inject constructor(
//    private val userPrefs: UserPreferences
//) {
//    suspend fun saveSession(
//        userId: Int,
//        username: String,
//        role: String,
//        isLoggedIn: Boolean
//    ) {
//        userPrefs.saveUserSession(
//            userId,
//            username,
//            role,
//            isLoggedIn
//        )
//    }
//
//    suspend fun clearSession() = userPrefs.clearSeassion()
//
//    val userIdFlow      = userPrefs.userIdFlow
//    val usernameFlow    = userPrefs.usernameFlow
//    val roleFlow        = userPrefs.roleFlow
//    val isLoggedInFlow  = userPrefs.isLoggedInFlow
//}

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val userPrefs: UserPreferences
) {
    suspend fun saveSession(
        userId: Int,
        name: String,
        username: String,
        user_type: String,
        isLoggedIn: Boolean
    ) {
        userPrefs.saveUserSession(
            userId,
            name,
            username,
            user_type,
            isLoggedIn
        )
    }

    suspend fun clearSession() = userPrefs.clearSession()

    val userIdFlow      = userPrefs.userIdFlow
    val nameFlow        = userPrefs.nameFlow
    val usernameFlow    = userPrefs.usernameFlow
    val userTypeFlow    = userPrefs.userTypeFlow
    val isLoggedInFlow  = userPrefs.isLoggedInFlow
}