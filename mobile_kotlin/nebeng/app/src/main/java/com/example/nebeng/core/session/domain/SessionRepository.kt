package com.example.nebeng.core.session.domain

interface SessionRepository {
    suspend fun clearSession()
}