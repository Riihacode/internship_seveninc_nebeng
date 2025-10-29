package com.example.nebeng.app.ui

import androidx.lifecycle.ViewModel
import com.example.nebeng.core.session.data.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppRoleViewModel @Inject constructor(
    userPrefsRepo: UserPreferencesRepository
): ViewModel() {
    val userTypeFlow = userPrefsRepo.userTypeFlow
    val isLoggedInFlow = userPrefsRepo.isLoggedInFlow
}