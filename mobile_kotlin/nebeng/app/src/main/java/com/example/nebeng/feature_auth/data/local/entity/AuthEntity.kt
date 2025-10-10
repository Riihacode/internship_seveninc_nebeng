package com.example.nebeng.feature_auth.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nebeng.feature_auth.domain.model.Auth

@Entity(tableName = "users")
data class AuthEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: String
)

fun AuthEntity.toDomain() = Auth(id, username, password, role)
fun Auth.toEntity() = AuthEntity(id, username, password, role)
