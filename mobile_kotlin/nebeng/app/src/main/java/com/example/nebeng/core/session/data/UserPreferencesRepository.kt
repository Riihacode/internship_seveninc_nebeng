package com.example.nebeng.core.session.data

import com.example.nebeng.core.utils.UserType
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import com.example.nebeng.feature_auth.domain.model.Auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class UserPreferencesRepository @Inject constructor(
//    private val userPrefs: UserPreferences
//) {
////    suspend fun saveSession(
////        userId: Int,
////        name: String,
////        username: String,
////        user_type: String,
////        isLoggedIn: Boolean,
////        token: String = ""
////    ) {
////        userPrefs.saveUserSession(
////            userId,
////            name,
////            username,
////            user_type,
////            isLoggedIn,
////            token
////        )
////    }
////    suspend fun saveSession(auth: AuthenticationItem) {
////        userPrefs.saveUserSession(
////            userId = auth.userId,
////            name = auth.name,
////            username = auth.username,
////            user_type = auth.userType.value,
////            isLoggedIn = true,
////            token = auth.token,
////            customerId = auth.customerId,
////            customerName = auth.customerName
////        )
////    }
////
////    // ✅ Getter / Setter opsional
////    suspend fun getToken(): String? = userPrefs.getToken()
////    suspend fun saveToken(token: String) = userPrefs.saveToken(token)
////
////    suspend fun getUser(): Auth? {
////        val id = userIdFlow.first()
////        val name = nameFlow.first()
////        val username = usernameFlow.first()
////        val userType = userTypeFlow.first()
////        val isLoggedIn = isLoggedInFlow.first()
////
////        return if (isLoggedIn) {
////            Auth(
////                id = id,
////                name = name,
////                username = username,
////                email = "$username@mail.com", // optional dummy
////                user_type = userType
////            )
////        } else null
////    }
////
////    suspend fun clearSession() = userPrefs.clearSession()
////
////    suspend fun saveCustomerId(customerId: Int) = userPrefs.saveCustomerId(customerId)
////
////    val userIdFlow      = userPrefs.userIdFlow
////    val nameFlow        = userPrefs.nameFlow
////    val usernameFlow    = userPrefs.usernameFlow
////    val userTypeFlow    = userPrefs.userTypeFlow
////    val isLoggedInFlow  = userPrefs.isLoggedInFlow
////    val customerIdFlow  = userPrefs.customerIdFlow
////    // ✅ Aliran token dari DataStore
////    val tokenFLow       = userPrefs.tokenFlow
////    val customerNameFlow = userPrefs.customerNameFlow
////
//////    val currentUserFlow: Flow<Auth?> = combine(
//////        userPrefs.userIdFlow,
//////        userPrefs.nameFlow,
//////        userPrefs.usernameFlow,
//////        userPrefs.userTypeFlow,
//////        userPrefs.tokenFlow
//////    ) { id, name, username, role, token ->
//////        if (token.isNullOrBlank()) return@combine null  // belum login
//////
//////        Auth(
//////            id = id,
//////            name = name,
//////            username = username,
//////            email = null,             // tidak disimpan di DataStore
//////            password = null,          // tidak boleh disimpan
//////            user_type = role,
//////            token = token,
//////            phone = null,             // tidak disimpan
//////            avatarUrl = null,         // tidak disimpan
//////            isVerified = false        // belum ada di backend
//////        )
//////    }
////// ============================================================
////// AUTHENTICATION FLOW READY FOR UI
////// ============================================================
////    val currentUserFlow: Flow<AuthenticationItem?> =
////        userPrefs.context.dataStore.data.map { prefs ->
////            val token = prefs[TOKEN] ?: return@map null
////
////            AuthenticationItem(
////                userId = prefs[USER_ID] ?: 0,
////                name = prefs[NAME] ?: "",
////                username = prefs[USERNAME] ?: "",
////                email = null,
////                password = null,
////                userType = UserType.fromString(prefs[USER_TYPE] ?: ""),
////                token = token,
////                phone = null,
////                avatarUrl = null,
////                isVerified = false,
////                createdAt = null,
////                updatedAt = null,
////                banned = false,
////                customerId = prefs[CUSTOMER_ID],
////                customerName = prefs[CUSTOMER_NAME]
////            )
////        }
//
//    // -------------------------------------------
//    // FLOW EXPOSED KE VIEWMODEL / MAIN ACTIVITY
//    // -------------------------------------------
//    val userIdFlow       = userPrefs.userIdFlow
//    val nameFlow         = userPrefs.nameFlow
//    val usernameFlow     = userPrefs.usernameFlow
//    val userTypeFlow     = userPrefs.userTypeFlow     // ⚠️ jangan hapus
//    val isLoggedInFlow   = userPrefs.isLoggedInFlow   // ⚠️ jangan hapus
//    val tokenFlow        = userPrefs.tokenFlow
//    val customerIdFlow   = userPrefs.customerIdFlow
//    val customerNameFlow = userPrefs.customerNameFlow
//
//    // =======================
//    // 🔥 INDIVIDUAL SAVE/GET
//    // =======================
//    suspend fun saveToken(token: String) = userPrefs.saveToken(token)
//    suspend fun getToken(): String? = userPrefs.getToken()
//    suspend fun saveCustomerId(customerId: Int) = userPrefs.saveCustomerId(customerId)
//
//
//    /** SIMPAN SESSION LOGIN */
//    suspend fun saveSession(auth: AuthenticationItem) {
//        userPrefs.saveUserSession(
//            userId = auth.userId,
//            name = auth.name,
//            username = auth.username,
//            user_type = auth.userType.value,
//            isLoggedIn = true,
//            token = auth.token,
//            customerId = auth.customerId,
//            customerName = auth.customerName
//        )
//    }
//
//    /** UPDATE CUSTOMER INFO */
//    suspend fun saveCustomerInfo(customerId: Int, customerName: String) {
//        userPrefs.saveCustomerInfo(customerId, customerName)
//    }
//
//    /** LOGOUT */
//    suspend fun clearSession() = userPrefs.clearSession()
//
//    /** Flow AuthenticationItem untuk UI */
//    val currentUserFlow: Flow<AuthenticationItem?> = userPrefs.context.dataStore.data.map {
//        userPrefs.getUserSession()  // DataStore → AuthenticationItem?
//    }
//}

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val userPrefs: UserPreferences
) {

    // =======================
    // 🔥 SIMPAN SESSION
    // =======================
    suspend fun saveSession(auth: AuthenticationItem) {
        userPrefs.saveUserSession(
            userId = auth.userId,
            name = auth.name,
            username = auth.username,
            user_type = auth.userType.value,
            isLoggedIn = true,
            token = auth.token,
            customerId = auth.customerId,
            customerName = auth.customerName
        )
    }

    suspend fun clearSession() = userPrefs.clearSession()
    suspend fun saveCustomerId(customerId: Int) = userPrefs.saveCustomerId(customerId)
    suspend fun saveToken(token: String) = userPrefs.saveToken(token)
    suspend fun getToken(): String? = userPrefs.getToken()

    // =======================
    // 🔥 PUBLIC FLOWS (untuk ViewModel di seluruh aplikasi)
    // =======================
    val userIdFlow         = userPrefs.userIdFlow
    val nameFlow           = userPrefs.nameFlow
    val usernameFlow       = userPrefs.usernameFlow
    val userTypeFlow       = userPrefs.userTypeFlow         // ❗ diperlukan MainActivity
    val isLoggedInFlow     = userPrefs.isLoggedInFlow       // ❗ diperlukan MainActivity
    val tokenFLow          = userPrefs.tokenFlow            // ❗ diperlukan HistoryOrderViewModel
    val customerIdFlow     = userPrefs.customerIdFlow       // ❗ diperlukan HistoryOrderViewModel
    val customerNameFlow   = userPrefs.customerNameFlow

    // =======================
    // 🔥 Untuk kebutuhan UI yang ingin AuthenticationItem lengkap
    // =======================
    val currentUserFlow: Flow<AuthenticationItem?> = userPrefs.currentUserFlow
}