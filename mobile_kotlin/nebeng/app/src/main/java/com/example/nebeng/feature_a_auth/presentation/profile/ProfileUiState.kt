package com.example.nebeng.feature_a_auth.presentation.profile

import com.example.nebeng.feature_a_auth.domain.model.Auth
import com.example.nebeng.core.common.Result



data class ProfileUiState(
    // ==== User session yang sedang aktif ====
    val currentUser: Auth? = null,

    // ==== Daftar semua user (cached dari repository) ====
    val users: Result<List<Auth>> = Result.Loading,

    // ==== State UI (tidak memicu recompose besar) ====
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    // ==== Event flag (untuk trigger 1x action di UI) ====
    val isProfileUpdated: Boolean = false,
    val isLoggedOut: Boolean = false,
    val isDeleted: Boolean = false
)
