package com.example.nebeng.feature_a_auth.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.app.ui.RoleCache
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_a_auth.domain.model.Auth
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_auth.domain.usecase.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val interactor: ProfileUseCases,
    private val userPrefsRepo: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // ================================================================
    // 1️⃣ Cached Flow untuk session dan user list
    // ================================================================
    private val sessionFlow: StateFlow<Auth?> = combine(
        userPrefsRepo.userIdFlow,
        userPrefsRepo.nameFlow,
        userPrefsRepo.usernameFlow,
        userPrefsRepo.userTypeFlow,
        userPrefsRepo.isLoggedInFlow
    ) { id, name, username, userType, isLoggedIn ->
        if (isLoggedIn) Auth(id, name, username, "", "", userType) else null
    }.distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val allUsersFlow: StateFlow<Result<List<Auth>>> =
        interactor.getAllAuth()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading)

    private var initialized = false

    init {
        if (!initialized) {
            initialized = true
            observeCurrentSession()
        }
    }

    // ================================================================
    // 2️⃣ Observasi session & daftar user bersamaan
    // ================================================================
    private fun observeCurrentSession() {
        viewModelScope.launch {
            combine(sessionFlow, allUsersFlow) { sessionUser, result ->
                Pair(sessionUser, result)
            }.collectLatest { (sessionUser, result) ->

                Log.d("ProfileVM", "🧩 sessionUser = ${sessionUser?.username ?: "null"}")
                Log.d("ProfileVM", "🧩 result type = ${result::class.simpleName}")

                when (result) {
                    is Result.Success   -> {
                        val matched = result.data.find { it.id == sessionUser?.id }
                        Log.d("ProfileVM", "✅ matched user = ${matched?.username ?: "tidak ada"}")
                        _uiState.update {
                            it.copy(
                                currentUser = matched,
                                users = result,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }

                    is Result.Error     -> _uiState.update {
                        Log.e("ProfileVM", "❌ getAllUser error: ${result.message}")
                        it.copy(isLoading = false, errorMessage = result.message)
                    }

                    is Result.Loading   -> {
                        Log.d("ProfileVM", "⏳ Loading users...")
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }

                // Jika user sudah logout
                if (sessionUser == null) {
                    Log.w("ProfileVM", "⚠️ sessionUser = null → kemungkinan belum login atau session kosong")
                    _uiState.update {
                        it.copy(currentUser = null, isLoading = false)
                    }
                }
            }
        }
    }

    // ================================================================
    // 3️⃣ Fungsi manual refresh daftar user (retrigger flow)
    // ================================================================
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            interactor.getAllAuth().collectLatest { result ->
                _uiState.update { it.copy(users = result, isLoading = false) }
            }
        }
    }

    // ================================================================
    // 4️⃣ Update profil (I/O thread)
    // ================================================================
    fun updateProfile(
        id: Int,
        name: String,
        username: String,
        password: String,
        userType: String
    ) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                val updatedUser = Auth(
                    id = id,
                    name = name,
                    username = username,
                    password = password,
                    email = "",
                    user_type = userType
                )

                when (val result = interactor.updateAuth(updatedUser)) {
                    is Result.Success   -> {
                        // Sinkronkan ke DataStore agar sessioflow ikut update otomatis
                        userPrefsRepo.saveSession(
                            userId = updatedUser.id,
                            name = updatedUser.name,
                            username = updatedUser.username,
                            user_type = updatedUser.user_type,
                            isLoggedIn = true,
                            token = ""
                        )

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isProfileUpdated = true,
                                currentUser = updatedUser
                            )
                        }
                    }

                    is Result.Error     -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }

                    else -> {}
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Terjadi kesalahan tak terduga"
                    )
                }
            }
        }
    }

    // ================================================================
    // 5️⃣ Hapus akun
    // ================================================================
    fun deleteAccount(
        id: Int,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = interactor.deleteAuth(id)) {
                is Result.Success -> {
                    userPrefsRepo.clearSession()
                    _uiState.update { it.copy(currentUser = null, isDeleted = true) }
                    onSuccess()
                }

                is Result.Error -> onError(result.message ?: "Gagal menghapus akun")
                else -> {}
            }
        }
    }

    // ================================================================
    // 6️⃣ Logout (hapus DataStore)
    // ================================================================
    fun logout(onLogoutDone: () -> Unit) {
        viewModelScope.launch {
            try {
                userPrefsRepo.clearSession()
                _uiState.update { it.copy(currentUser = null, isLoggedOut = true) }
                RoleCache.isLoggedIn = false
                RoleCache.role = null
                onLogoutDone()
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Logout gagal: ${e.message}")
            }
        }
    }


    fun resetProfileUpdatedFlag() {
        _uiState.update { it.copy(isProfileUpdated = false) }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}