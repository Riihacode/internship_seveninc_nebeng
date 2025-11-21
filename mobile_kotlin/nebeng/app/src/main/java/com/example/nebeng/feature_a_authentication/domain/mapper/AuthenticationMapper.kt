package com.example.nebeng.feature_a_authentication.domain.mapper

import com.example.nebeng.core.utils.UserType
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import com.example.nebeng.feature_auth.domain.model.AuthSummary
import com.example.nebeng.feature_customer.domain.model.UserCustomerSummary
import com.example.nebeng.feature_user.domain.model.UserSummary

/**
 * Mapping dari Auth (hasil login/register) → AuthenticationItem.
 * Ini dipakai saat user berhasil login / signup.
 */
fun AuthSummary.toAuthenticationItem(): AuthenticationItem {
    return AuthenticationItem(
        userId = id,
        name = name,
        username = username,
        email = email,
        password = password,
        userType = UserType.fromString(user_type),
        token = token,
        phone = phone,
        avatarUrl = avatarUrl,
        isVerified = isVerified
    )
}

/**
 * Mapping dari User (hasil get / update user) → AuthenticationItem.
 * - token diambil dari parameter (Auth tetap pemilik token)
 * - base: state auth lama (kalau mau mewarisi phone/avatar/isVerified/password)
 */
fun UserSummary.toAuthenticationItem(
    token: String,
    base: AuthenticationItem? = null
): AuthenticationItem {
    return AuthenticationItem(
        userId = id,
        name = name,
        username = username,
        email = email,
        password = base?.password,
        userType = userType,
        token = token,
        phone = base?.phone,
        avatarUrl = base?.avatarUrl,
        isVerified = base?.isVerified ?: false,
        createdAt = createdAt,
        updatedAt = updatedAt,
        banned = banned,
        customerId = base?.customerId,
        customerName = base?.customerName
    )
}

// ======================================================================
// 3️⃣ ⬆ Enrichment customer info → AuthenticationItem
// ======================================================================
fun AuthenticationItem.withCustomerInfo(
    summary: UserCustomerSummary
): AuthenticationItem {
    return this.copy(
        customerId = summary.customerId,
        customerName = summary.customerName
    )
}