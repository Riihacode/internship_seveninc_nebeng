package com.example.nebeng.feature_auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.model.Result
import com.example.nebeng.core.session.UserPreferencesRepository
import com.example.nebeng.feature_auth.di.AuthUseCases

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
                        userPrefsRepo.saveSession(
                            userId = user.id,
                            username = user.username,
                            role = user.role,
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