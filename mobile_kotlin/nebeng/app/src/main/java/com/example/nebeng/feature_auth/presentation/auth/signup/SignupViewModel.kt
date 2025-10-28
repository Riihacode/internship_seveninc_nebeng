package com.example.nebeng.feature_auth.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.feature_auth.domain.model.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_auth.domain.usecase.AuthUseCases

// [SEBELUM ADA API]
//@HiltViewModel
//class SignupViewModel @Inject constructor(
//    private val useCases: AuthUseCases
//): ViewModel() {
//    fun signup(
//        username: String,
//        password: String,
//        role: String = "user",
//        onSuccess: () -> Unit,
//        onError: (String) -> Unit
//    ) {
//        viewModelScope.launch {
//            when (
//                val result = useCases.createAuth(
//                    Auth(
//                        id = 0,
//                        username = username,
//                        password = password,
//                        role = role
//                    )
//                )
//            ) {
//                is Result.Success -> onSuccess()
//                is Result.Error -> onError(result.message ?: "Gagal membuat user baru")
//                else -> {}
//            }
//        }
//    }
//}


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val useCases: AuthUseCases
): ViewModel() {
    fun signup(
        name: String,
        username: String,
        email: String,
        password: String,
        user_type: String?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
//        val safeRole = user_type ?: "customer"
        val safeUserType = user_type ?: "customer"
        viewModelScope.launch {
            when (
                val result = useCases.createAuth(
                    Auth(
                        id = 0,
                        name = name,
                        username = username,
                        email = email,
                        password = password,
                        user_type = safeUserType
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