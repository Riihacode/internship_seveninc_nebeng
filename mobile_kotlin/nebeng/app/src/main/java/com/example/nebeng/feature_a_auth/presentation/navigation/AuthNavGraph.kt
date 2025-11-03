package com.example.nebeng.feature_a_auth.presentation.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.app.ui.MainActivity
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_a_auth.presentation.auth.login.LoginScreen
import com.example.nebeng.feature_a_auth.presentation.auth.login.LoginViewModel
//import com.example.nebeng.feature_auth.presentation.profile.screen.EditProfileScreen
import com.example.nebeng.feature_a_auth.presentation.auth.signup.SignupScreen
import com.example.nebeng.feature_a_auth.presentation.auth.signup.SignupViewModel

@Composable
fun AuthNavGraph(
    navController: NavHostController = rememberNavController(),
    userPrefsRepo: UserPreferencesRepository
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            val viewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                onNavigateToSignup = { navController.navigate("signup") }
            )
        }

        composable("signup") {
            val viewModel: SignupViewModel = hiltViewModel()

            SignupScreen(
                viewModel = viewModel,
                onSignupSuccess = {
                    // Setelah daftar balik ke LoginScreen
                    navController.popBackStack("login", inclusive = false)
                },
                onBackToLogin = {
                    navController.popBackStack("login", inclusive = false)
                }
            )
        }
    }
}