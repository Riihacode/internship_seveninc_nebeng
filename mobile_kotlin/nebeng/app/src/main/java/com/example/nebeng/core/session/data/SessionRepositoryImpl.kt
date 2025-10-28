package com.example.nebeng.core.session.data

import com.example.nebeng.core.session.domain.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val userPrefsRepo: UserPreferencesRepository
): SessionRepository {
    override suspend fun clearSession() = userPrefsRepo.clearSession()
}