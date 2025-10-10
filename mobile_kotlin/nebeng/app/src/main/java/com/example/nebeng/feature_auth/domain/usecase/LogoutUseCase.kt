package com.example.nebeng.feature_auth.domain.usecase

import com.example.nebeng.core.session.UserPreferencesRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userPrefsRepo: UserPreferencesRepository
){
    suspend operator fun invoke() {
        userPrefsRepo.clearSession()
    }
}