package com.example.nebeng.feature_auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_auth.domain.model.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.model.Result
import com.example.nebeng.feature_auth.di.AuthUseCases

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val useCases: AuthUseCases
): ViewModel() {
    fun signup(
        username: String,
        password: String,
        role: String = "user",
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            when (
                val result = useCases.createAuth(
                    Auth(
                        id = 0,
                        username = username,
                        password = password,
                        role = role
                    )
                )
            ) {
                is Result.Success -> onSuccess()
                is Result.Error -> onError(result.message ?: "Gagal membuat user baru")
                else -> {}
            }
        }
    }
}