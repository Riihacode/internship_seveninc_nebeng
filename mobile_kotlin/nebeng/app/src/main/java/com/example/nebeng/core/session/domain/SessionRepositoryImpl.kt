package com.example.nebeng.core.session.domain

import com.example.nebeng.core.session.data.UserPreferencesRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val userPrefsRepo: UserPreferencesRepository
): SessionRepository {
    override suspend fun clearSession() = userPrefsRepo.clearSession()
}