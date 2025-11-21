package com.example.nebeng.feature_a_authentication.presentation.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.app.ui.MainActivity
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_a_authentication.presentation.AuthenticationViewModel
import com.example.nebeng.feature_a_authentication.presentation.auth.login.LoginScreen
//import com.example.nebeng.feature_a_authentication.presentation.auth.login.LoginViewModel
//import com.example.nebeng.feature_auth.presentation.profile.screen.EditProfileScreen
import com.example.nebeng.feature_a_authentication.presentation.auth.signup.SignupScreen
//import com.example.nebeng.feature_a_authentication.presentation.auth.signup.SignupViewModel
import com.example.nebeng.feature_auth.data.remote.model.request.CreateLoginRequest
import com.example.nebeng.feature_auth.data.remote.model.request.CreateRegisterRequest

//@Composable
//fun AuthNavGraph(
//    navController: NavHostController = rememberNavController(),
//    userPrefsRepo: UserPreferencesRepository
//) {
//    val context = LocalContext.current
//
//    NavHost(
//        navController = navController,
//        startDestination = "login"
//    ) {
//        composable("login") {
//            val viewModel: LoginViewModel = hiltViewModel()
//
//            LoginScreen(
//                viewModel = viewModel,
//                onLoginSuccess = {
//                    context.startActivity(Intent(context, MainActivity::class.java))
//                },
//                onNavigateToSignup = { navController.navigate("signup") }
//            )
//        }
//
//        composable("signup") {
//            val viewModel: SignupViewModel = hiltViewModel()
//
//            SignupScreen(
//                viewModel = viewModel,
//                onSignupSuccess = {
//                    // Setelah daftar balik ke LoginScreen
//                    navController.popBackStack("login", inclusive = false)
//                },
//                onBackToLogin = {
//                    navController.popBackStack("login", inclusive = false)
//                }
//            )
//        }
//    }
//}
@Composable
fun AuthNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val viewModel: AuthenticationViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
//        composable("login") {
//            val viewModel: LoginViewModel = hiltViewModel()
//            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
//
//            LoginScreen(
//                state = uiState,
//                onLogin = { user, pass ->
//                    viewModel.login(user, pass) {
//                        context.startActivity(Intent(context, MainActivity::class.java))
//                    }
//                },
//                onNavigateToSignup = { navController.navigate("signup") }
//            )
//        }
//        composable("login") {
//            val viewModel: AuthenticationViewModel = hiltViewModel()
//            val state = viewModel.uiState.collectAsState().value
//
//            LoginScreen(
//                state = state,
//                onLogin = { username, password ->
//                    viewModel.login(CreateLoginRequest(username, password))
//                },
//                onNavigateToSignup = { navController.navigate("signup") },
//                onLoginSuccess = {
//                    // navigate ke MainActivity ketika auth != null
//                    context.startActivity(Intent(context, MainActivity::class.java))
//                }
//            )
//        }


//        composable("signup") {
//            val viewModel: SignupViewModel = hiltViewModel()
//            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
//
//            SignupScreen(
//                state = uiState,
//                onSignup = { name, username, email, password, type ->
//                    viewModel.signup(
//                        name, username, email, password, type,
//                        onSuccess = {
//                            navController.popBackStack("login", false)
//                        }
//                    )
//                },
//                onBackToLogin = {
//                    navController.popBackStack("login", false)
//                }
//            )
//        }
//        composable("signup") {
//            val viewModel: SignupViewModel = hiltViewModel()
//            val state = viewModel.uiState.collectAsStateWithLifecycle().value
//
//            SignupScreen(
//                state = state,
//                onSignup = { name, user, email, pass, type ->
//                    viewModel.signup(name, user, email, pass, type)
//                },
//                onBackToLogin = { navController.popBackStack() }
//            )
//        }
//        composable("signup") {
//            val viewModel: AuthenticationViewModel = hiltViewModel()
//            val state = viewModel.uiState.collectAsState().value
//
//            SignupScreen(
//                state = state,
//                onSignup = { name, username, email, password, type ->
//                    viewModel.register(CreateRegisterRequest(name, username, email, password, type))
//                },
//                onBackToLogin = { navController.popBackStack() }
//            )
//        }
        composable("login") {
            val state = viewModel.uiState.collectAsState().value

            LoginScreen(
                state = state,
                onLogin = { username, password ->
                    viewModel.login(CreateLoginRequest(username, password))
                },
                onNavigateToSignup = { navController.navigate("signup") },
                onLoginSuccess = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            )
        }

//        composable("signup") {
//            SignupScreen(
//                state = state,
//                onSignup = { name, username, email, password, passwordConfirmation,type ->
//                    viewModel.register(CreateRegisterRequest(password, passwordConfirmation,type , name, email, username))
//                },
//                onBackToLogin = {
//                    navController.popBackStack("login", false)
//                }
//            )
//        }
        composable("signup") {
            val state = viewModel.uiState.collectAsState().value

            SignupScreen(
                state = state,
                onSignup = { name, username, email, password, passwordConfirmation, userType ->
                    viewModel.register(
                        CreateRegisterRequest(
                            password = password,
                            passwordConfirmation = passwordConfirmation,
                            userType = userType,
                            name = name,
                            email = email,
                            username = username
                        )
                    )
                },
                onBackToLogin = {
                    navController.popBackStack("login", false)
                }
            )
        }
    }
}
