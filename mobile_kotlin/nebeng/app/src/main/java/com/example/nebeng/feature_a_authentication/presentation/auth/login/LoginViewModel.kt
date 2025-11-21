package com.example.nebeng.feature_a_authentication.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_auth.domain.usecase.AuthUseCases
import com.example.nebeng.feature_customer.domain.usecase.ReadByUserIdCustomerUseCase

//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val useCases: AuthUseCases,
//    private val userPrefsRepo: UserPreferencesRepository,
//    private val readByUserIdCustomerUseCase: ReadByUserIdCustomerUseCase
//) : ViewModel() {
//
//    fun login(
//        username: String,
//        password: String,
//        onSuccess: () -> Unit
//    ) {
//        viewModelScope.launch {
//            when (val result = useCases.loginAuth(username, password)) {
//                is Result.Success -> {
//                    val user = result.data ?: return@launch
//
//                    // simpan session user dulu
//                    userPrefsRepo.saveSession(
//                        userId = user.id,
//                        name = user.name,
//                        username = user.username,
//                        user_type = user.user_type,
//                        isLoggedIn = true,
//                        token = user.token
//                    )
//
//                    // --- ambil customer berdasarkan user.id ---
//                    readByUserIdCustomerUseCase(
//                        token = user.token,
//                        userId = user.id
//                    ).collect { customerResult ->
//                        when (customerResult) {
//                            is Result.Success -> {
//                                val customer = customerResult.data
//                                userPrefsRepo.saveCustomerId(customer.id)
//                                Log.d("LoginVM", "Customer ID saved = ${customer.id}")
//                                onSuccess()
//                            }
//                            is Result.Error -> {
//                                Log.e("LoginVM", "Error GetCustomerByUserId → ${customerResult.message}")
//                            }
//                            else -> Unit
//                        }
//                    }
//
//                    onSuccess()
//                }
//
//                is Result.Error -> Log.e("LoginVM", result.message ?: "Error")
//                is Result.Loading -> Unit
//            }
//        }
//    }
//}
