package com.example.nebeng.feature_auth.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.session.UserPreferencesRepository
import com.example.nebeng.feature_auth.di.AuthUseCases
import com.example.nebeng.feature_auth.domain.model.Auth
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: AuthUseCases,
    private val userPrefsRepo: UserPreferencesRepository
) : ViewModel() {

    // ===============================
    // 1️⃣ Daftar semua user (untuk admin)
    // ===============================
    private val _users = MutableStateFlow<Result<List<Auth>>>(Result.Loading)
    val users: StateFlow<Result<List<Auth>>> = _users

    // ===============================
    // 2️⃣ User yang sedang login
    // ===============================
    private val _currentUser = MutableStateFlow<Auth?>(null)
    val currentUser: StateFlow<Auth?> = _currentUser

    init {
        observeCurrentSession()
        loadUsers()
    }

    // ===============================
    // 3️⃣ Pantau session dari DataStore
    // ===============================
    fun observeCurrentSession() {
        viewModelScope.launch {
            combine(
                userPrefsRepo.userIdFlow,
                userPrefsRepo.nameFlow,
                userPrefsRepo.usernameFlow,
                userPrefsRepo.userTypeFlow,
                userPrefsRepo.isLoggedInFlow
            ) { id, name, username, user_type, isLoggedIn ->
                if (isLoggedIn) Auth(
                    id = id,
                    name = name,
                    username = username,
                    password = "",
                    email = "",
                    user_type = user_type
                )
                else null
            }.collectLatest { sessionUser ->
                if (sessionUser != null) {
                    useCases.getAllAuth().collectLatest { result ->
                        if (result is Result.Success) {
                            _currentUser.value = result.data.find { it.id == sessionUser.id }
                        }
                    }
                } else {
                    _currentUser.value = null
                }
            }
        }
    }

    // ===============================
    // 4️⃣ Ambil semua user
    // ===============================
    fun loadUsers() {
        viewModelScope.launch {
            useCases.getAllAuth().collectLatest { result ->
                _users.value = result
            }
        }
    }

    // ===============================
    // 5️⃣ Update profil
    // ===============================
    fun updateProfile(
        id: Int,
        name: String,
        username: String,
        password: String,
        user_type: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val updatedUser = Auth(
                id = id,
                name = name,
                username = username,
                password = password,
                email = "",
                user_type = user_type)
            when (val result = useCases.updateAuth(updatedUser)) {
                is Result.Success -> {
                    onSuccess()
                    loadUsers()
                    observeCurrentSession()
                }
                is Result.Error -> onError(result.message ?: "Gagal memperbarui profil")
                else -> {}
            }
        }
    }

    // ===============================
    // 6️⃣ Hapus akun
    // ===============================
    fun deleteAccount(
        id: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            when (val result = useCases.deleteAuth(id)) {
                is Result.Success -> {
//                    onSuccess()
//                    loadUsers()
                    // penghapusan session otomatis
                    userPrefsRepo.clearSession()
                    _currentUser.value = null
                    onSuccess()
                }
                is Result.Error -> onError(result.message ?: "Gagal menghapus akun")
                else -> {}
            }
        }
    }

    // ===============================
    // 7️⃣ Logout (hapus DataStore)
    // ===============================
    fun logout(onLogoutDone: () -> Unit) {
        viewModelScope.launch {
            try {
                userPrefsRepo.clearSession()
                _currentUser.value = null
                onLogoutDone()
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Logout gagal: ${e.message}")
            }
        }
    }
}