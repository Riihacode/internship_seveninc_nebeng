package com.example.nebeng.core.session.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {
    companion object {
        private val USER_ID = intPreferencesKey("user_id")
        private val NAME = stringPreferencesKey("name")
        private val USERNAME = stringPreferencesKey("username")
        private val USER_TYPE = stringPreferencesKey("role")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    // Simpan data user setelah login
    suspend fun saveUserSession(
        userId: Int,
        name: String,
        username: String,
        user_type: String,
        isLoggedIn: Boolean
    ) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[NAME] = name
            prefs[USERNAME] = username
            prefs[USER_TYPE] = user_type
            prefs[IS_LOGGED_IN] = isLoggedIn
        }
    }

    // Ambil data user (Flow)
    val userIdFlow: Flow<Int>           = context.dataStore.data.map { it[USER_ID] ?: 0}
    val nameFlow: Flow<String>          = context.dataStore.data.map { it[NAME] ?: ""}
    val usernameFlow: Flow<String>      = context.dataStore.data.map { it[USERNAME] ?: ""}
    val userTypeFlow: Flow<String>      = context.dataStore.data.map { it[USER_TYPE] ?: ""}
    val isLoggedInFlow: Flow<Boolean>   = context.dataStore.data.map { it[IS_LOGGED_IN] ?: false}

    // Hapus session saat logout
    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}