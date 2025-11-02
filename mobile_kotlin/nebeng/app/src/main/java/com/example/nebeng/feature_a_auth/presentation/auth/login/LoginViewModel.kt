package com.example.nebeng.feature_a_auth.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_a_auth.domain.usecase.AuthUseCases

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthUseCases,
    private val userPrefsRepo: UserPreferencesRepository
): ViewModel() {
    fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            when (
                val result = useCases.loginAuth(
                    username,
                    password
                )
            ) {
                is Result.Success   -> {
                    result.data?.let { user ->
                        Log.d("LoginVM", "✅ Login success → simpan session ${user.username} (${user.user_type})")
                        userPrefsRepo.saveSession(
                            userId = user.id,
                            name = user.name,
                            username = user.username,
                            user_type = user.user_type,
                            isLoggedIn = true
                        )
                        onSuccess()
                    }
                }
                is Result.Error     -> Log.e("Login", result.message ?: "Error")
                else                -> {}
            }
        }
    }
}