package com.example.nebeng.feature_auth.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nebeng.core.model.Result
import com.example.nebeng.feature_auth.di.AuthUseCases
import com.example.nebeng.feature_auth.domain.model.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val useCases: AuthUseCases
): ViewModel() {
    private val _users = MutableStateFlow<Result<List<Auth>>>(Result.Loading)
    val users: StateFlow<Result<List<Auth>>> = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            useCases.getAllAuth().collectLatest { result ->
                _users.value = result
            }
        }
    }
}