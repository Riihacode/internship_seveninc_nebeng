package com.example.nebeng.feature_auth.presentation.profile.screen_role.customer

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nebeng.feature_auth.presentation.profile.ProfileViewModel
import com.example.nebeng.feature_auth.presentation.profile.screen_base.BaseProfileScreen

@Composable
fun ProfileCustomerScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    BaseProfileScreen(
        navController = navController,
        viewModel = viewModel
    )
}
