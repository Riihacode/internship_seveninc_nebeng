package com.example.nebeng.feature_a_auth.domain.usecase

import com.example.nebeng.core.session.domain.SessionRepository
import javax.inject.Inject

//class LogoutUseCase @Inject constructor(
//    private val userPrefsRepo: UserPreferencesRepository
//){
//    suspend operator fun invoke() {
//        userPrefsRepo.clearSession()
//    }
//}

class LogoutUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        sessionRepository.clearSession()
    }
}