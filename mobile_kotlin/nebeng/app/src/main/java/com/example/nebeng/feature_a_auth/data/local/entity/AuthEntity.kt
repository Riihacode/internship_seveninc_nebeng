package com.example.nebeng.feature_a_auth.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nebeng.feature_a_auth.domain.model.Auth

// [SEBELUM ADA API]
//@Entity(tableName = "users")
//data class AuthEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//    val username: String,
//    val password: String,
//    val role: String
//)
//
//fun AuthEntity.toDomain() = Auth(id, username, password, role)
//fun Auth.toEntity() = AuthEntity(id, username, password, role)


@Entity(tableName = "users")
data class AuthEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val username: String,
    val email: String?,
    val password: String?,
    val user_type: String
)

fun AuthEntity.toDomain() = Auth(id, name, username, email, password, user_type )
fun Auth.toEntity() = AuthEntity(id, name, username, email, password, user_type)
