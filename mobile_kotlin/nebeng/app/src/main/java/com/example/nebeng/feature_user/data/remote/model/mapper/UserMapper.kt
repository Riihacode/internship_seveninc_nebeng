package com.example.nebeng.feature_user.data.remote.model.mapper

import com.example.nebeng.core.utils.UserType
import com.example.nebeng.feature_user.data.remote.model.dto.DataDto
import com.example.nebeng.feature_user.domain.model.User
import com.example.nebeng.feature_user.domain.model.UserSummary

/**
 * Mapping extension untuk mengubah DataDto menjadi User domain model.
 * NPE SAFE — jika dto null, semua nilai fallback aman.
 */
fun DataDto?.toUser(): User {
    return User(
        id = this?.id ?: -1,
        name = this?.name ?: "",
        username = this?.username ?: "",
        email = this?.email ?: "",
        userType = UserType.fromString(this?.userType ?: ""),
        banned = (this?.banned ?: 0) == 1,
        createdAt = this?.createdAt ?: "",
        updatedAt = this?.updatedAt ?: ""
    )
}

fun DataDto?.toSummary(): UserSummary {
    return UserSummary(
        id = this?.id ?: -1,
        name = this?.name ?: "",
        username = this?.username ?: "",
        email = this?.email ?: "",
        userType = UserType.fromString(this?.userType ?: ""),
        banned = (this?.banned ?: 0) == 1,
        createdAt = this?.createdAt ?: "",
        updatedAt = this?.updatedAt ?: ""
    )
}