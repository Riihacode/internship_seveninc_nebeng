package com.example.nebeng.core.session.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.nebeng.core.utils.UserType
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//val Context.dataStore by preferencesDataStore(name = "user_prefs")
//
//class UserPreferences(val context: Context) {
//    companion object {
//        private val USER_ID = intPreferencesKey("user_id")
//        private val NAME = stringPreferencesKey("name")
//        private val USERNAME = stringPreferencesKey("username")
//        private val USER_TYPE = stringPreferencesKey("role")
//        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
//        private val TOKEN = stringPreferencesKey("token")
//        private val CUSTOMER_ID = intPreferencesKey("customer_id")
//        private val CUSTOMER_NAME = stringPreferencesKey("customer_name")
//    }
//
//    // Ambil data user (Flow)
//    val userIdFlow: Flow<Int>           = context.dataStore.data.map { it[USER_ID] ?: 0}
//    val nameFlow: Flow<String>          = context.dataStore.data.map { it[NAME] ?: ""}
//    val usernameFlow: Flow<String>      = context.dataStore.data.map { it[USERNAME] ?: ""}
//    val userTypeFlow: Flow<String>      = context.dataStore.data.map { it[USER_TYPE] ?: ""}
//    val isLoggedInFlow: Flow<Boolean>   = context.dataStore.data.map { it[IS_LOGGED_IN] ?: false}
//    val tokenFlow: Flow<String?>        = context.dataStore.data.map { it[TOKEN] }
//    val customerIdFlow                  = context.dataStore.data.map { it[CUSTOMER_ID] ?: 0}
//    val customerNameFlow                = context.dataStore.data.map { it[CUSTOMER_NAME] ?: "" }
//
//    // Simpan data user setelah login
//    suspend fun saveUserSession(
//        userId: Int,
//        name: String,
//        username: String,
//        user_type: String,
//        isLoggedIn: Boolean,
//        token: String,
//        customerId: Int?,
//        customerName: String?
//    ) {
//        context.dataStore.edit { prefs ->
//            prefs[USER_ID]      = userId
//            prefs[NAME]         = name
//            prefs[USERNAME]     = username
//            prefs[USER_TYPE]    = user_type
//            prefs[IS_LOGGED_IN] = isLoggedIn
//            prefs[TOKEN]        = token
//            if (customerId != null) prefs[CUSTOMER_ID]      = customerId
//            if (customerName != null) prefs[CUSTOMER_NAME]  = customerName
//        }
//    }
//
////    suspend fun saveToken(token: String) {
////        context.dataStore.edit { it[TOKEN] = token }
////    }
////
////    suspend fun getToken(): String? = context.dataStore.data.first()[TOKEN]
////
////    // Hapus session saat logout
////    suspend fun clearSession() {
////        context.dataStore.edit { it.clear() }
////    }
////
////    // 🔹 simpan customerId terpisah (dipanggil setelah call /customers/user/{userId})
////    suspend fun saveCustomerId(customerId: Int) {
////        context.dataStore.edit { prefs ->
////            prefs[CUSTOMER_ID] = customerId
////        }
////    }
//
//    suspend fun saveToken(token: String) {
//        context.dataStore.edit { it[TOKEN] = token }
//    }
//
//    suspend fun getToken(): String? = context.dataStore.data.first()[TOKEN]
//
//    suspend fun saveCustomerId(customerId: Int) {
//        context.dataStore.edit { it[CUSTOMER_ID] = customerId }
//    }
//
//    /** UPDATE CUSTOMER INFO */
//    suspend fun saveCustomerInfo(customerId: Int, customerName: String) {
//        context.dataStore.edit { prefs ->
//            prefs[CUSTOMER_ID] = customerId
//            prefs[CUSTOMER_NAME] = customerName
//        }
//    }
//
//    /** AMBIL SESSION — RETURN NULL jika belum login */
//    suspend fun getUserSession(): AuthenticationItem? {
//        val prefs = context.dataStore.data.first()
//        val token = prefs[TOKEN] ?: return null  // indikator login
//
//        return AuthenticationItem(
//            userId = prefs[USER_ID] ?: 0,
//            name = prefs[NAME] ?: "",
//            username = prefs[USERNAME] ?: "",
//            email = null,
//            password = null,
//            userType = UserType.fromString(prefs[USER_TYPE] ?: ""),
//            token = token,
//            phone = null,
//            avatarUrl = null,
//            isVerified = false,
//            createdAt = null,
//            updatedAt = null,
//            banned = false,
//            customerId = prefs[CUSTOMER_ID],
//            customerName = prefs[CUSTOMER_NAME]
//        )
//    }
//
//    /** LOGOUT */
//    suspend fun clearSession() {
//        context.dataStore.edit { it.clear() }
//    }
//}

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val USER_ID = intPreferencesKey("user_id")
        private val NAME = stringPreferencesKey("name")
        private val USERNAME = stringPreferencesKey("username")
        private val USER_TYPE = stringPreferencesKey("role")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val TOKEN = stringPreferencesKey("token")
        private val CUSTOMER_ID = intPreferencesKey("customer_id")
        private val CUSTOMER_NAME = stringPreferencesKey("customer_name")
    }

    // =======================
    // 🔥 NOW ALL FLOWS ARE PUBLIC
    // =======================
    val userIdFlow          = context.dataStore.data.map { it[USER_ID] ?: 0 }
    val nameFlow            = context.dataStore.data.map { it[NAME] ?: "" }
    val usernameFlow        = context.dataStore.data.map { it[USERNAME] ?: "" }
    val userTypeFlow        = context.dataStore.data.map { it[USER_TYPE] ?: "" }
    val isLoggedInFlow      = context.dataStore.data.map { it[IS_LOGGED_IN] ?: false }
    val tokenFlow           = context.dataStore.data.map { it[TOKEN] }
    val customerIdFlow      = context.dataStore.data.map { it[CUSTOMER_ID] ?: 0 }
    val customerNameFlow    = context.dataStore.data.map { it[CUSTOMER_NAME] ?: "" }

    // ===========================================
    // 🔥 SIMPAN DATA LOGIN KE DATASTORE
    // ===========================================
    suspend fun saveUserSession(
        userId: Int,
        name: String,
        username: String,
        user_type: String,
        isLoggedIn: Boolean,
        token: String,
        customerId: Int?,
        customerName: String?
    ) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[NAME] = name
            prefs[USERNAME] = username
            prefs[USER_TYPE] = user_type
            prefs[IS_LOGGED_IN] = isLoggedIn
            prefs[TOKEN] = token
            if (customerId != null) prefs[CUSTOMER_ID] = customerId
            if (customerName != null) prefs[CUSTOMER_NAME] = customerName
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { it[TOKEN] = token }
    }

    suspend fun getToken(): String? = context.dataStore.data.first()[TOKEN]

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }

    suspend fun saveCustomerId(customerId: Int) {
        context.dataStore.edit { it[CUSTOMER_ID] = customerId }
    }

    /**
     * 🔥 Flow AuthenticationItem untuk UI
     * return null → jika belum login
     */
    val currentUserFlow: Flow<AuthenticationItem?> =
        context.dataStore.data.map { prefs ->
            val token = prefs[TOKEN] ?: return@map null

            AuthenticationItem(
                userId = prefs[USER_ID] ?: 0,
                name = prefs[NAME] ?: "",
                username = prefs[USERNAME] ?: "",
                email = null,
                password = null,
                userType = UserType.fromString(prefs[USER_TYPE] ?: ""),
                token = token,
                phone = null,
                avatarUrl = null,
                isVerified = false,
                createdAt = null,
                updatedAt = null,
                banned = false,
                customerId = prefs[CUSTOMER_ID],
                customerName = prefs[CUSTOMER_NAME]
            )
        }
}
