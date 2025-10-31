package com.example.nebeng.feature_homepage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_auth.domain.model.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val userPrefsRepo: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomepageUiState(isLoading = true))
    val uiState: StateFlow<HomepageUiState> = _uiState

    private var initialized = false

    init {
        if (!initialized) {
            initialized = true
            observeUserSession()
        }
    }

    /**
     * Menggabungkan semua flow dari UserPreferencesRepository agar
     * setiap perubahan session langsung tercermin di UI (recompose otomatis).
     */
    private fun observeUserSession() {
        viewModelScope.launch {
            combine(
                userPrefsRepo.userIdFlow,
                userPrefsRepo.nameFlow,
                userPrefsRepo.usernameFlow,
                userPrefsRepo.userTypeFlow,
                userPrefsRepo.isLoggedInFlow
            ) { id, name, username, userType, isLoggedIn ->
                if (isLoggedIn) {
                    Auth(
                        id = id,
                        name = name,
                        username = username,
                        email = "$username@mail.com",
                        user_type = userType
                    )
                } else null
            }.stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), null)
                .collect { currentUser ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            currentUser = currentUser,
                            points = currentUser?.id?.times(10) ?: 0 // dummy kalkulasi poin
                        )
                    }
                }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun refreshPoints() {
        _uiState.update {
            it.copy(points = (it.points + 5).coerceAtMost(9999)) // contoh dummy reward
        }
    }
}