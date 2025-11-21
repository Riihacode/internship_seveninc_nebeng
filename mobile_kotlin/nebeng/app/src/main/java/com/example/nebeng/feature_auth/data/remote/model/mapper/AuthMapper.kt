package com.example.nebeng.feature_auth.data.remote.model.mapper

import com.example.nebeng.feature_auth.data.remote.model.response.DataLogin
import com.example.nebeng.feature_auth.data.remote.model.response.DataRegister
import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.feature_auth.domain.model.AuthSummary

//object AuthMapper {
//
//    // 🔹 Mapping dari DataLogin → Auth
//    fun fromLogin(data: DataLogin): Auth {
//        val user = data.user
//        return Auth(
//            id = user.id,
//            name = user.name,
//            username = user.username,
//            email = user.email,
//
//            // passeord tidak ada di response -> biarakan null
//            password = null,
//            user_type = user.userType,
//            token = data.token,
//            phone = null,
//            avatarUrl = null,
//            isVerified = false
//        )
//    }
//
//    // 🔹 Mapping dari DataRegister → Auth
//    fun fromRegister(data: DataRegister): Auth {
//        val user = data.user
//        return Auth(
//            id = user.id,
//            name = user.name,
//            username = user.username,
//            email = user.email,
//            password = null,
//            user_type = user.userType,
//            token = data.token,
//            phone = null,
//            avatarUrl = null,
//            isVerified = false
//        )
//    }
//}
fun DataLogin.toDomain(): Auth {
    return Auth(
        id = user.id,
        name = user.name,
        username = user.username,
        email = user.email,

        // passeord tidak ada di response -> biarakan null
        password = null,
        user_type = user.userType,
        token = token,
        phone = null,
        avatarUrl = null,
        isVerified = false
    )
}

fun DataRegister.toDomain(): Auth {
    return Auth(
        id = user.id,
        name = user.name,
        username = user.username,
        email = user.email,

        // passeord tidak ada di response -> biarakan null
        password = null,
        user_type = user.userType,
        token = token,
        phone = null,
        avatarUrl = null,
        isVerified = false
    )
}

// CROSS FEATURE KE feature_a_authentication
fun DataLogin.toSummary(): AuthSummary {
    return AuthSummary(
        id = user.id,
        name = user.name,
        username = user.username,
        email = user.email,

        // passeord tidak ada di response -> biarakan null
        password = null,
        user_type = user.userType,
        token = token,
        phone = null,
        avatarUrl = null,
        isVerified = false
    )
}

fun DataRegister.toSummary(): AuthSummary {
    return AuthSummary(
        id = user.id,
        name = user.name,
        username = user.username,
        email = user.email,

        // passeord tidak ada di response -> biarakan null
        password = null,
        user_type = user.userType,
        token = token,
        phone = null,
        avatarUrl = null,
        isVerified = false
    )
}