package com.example.nebeng.feature_homepage.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.feature_homepage.presentation.HomepageViewModel
import com.example.nebeng.feature_homepage.presentation.screen_role.customer.HomepageCustomerScreenUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageNavHost(
    navController: NavHostController = rememberNavController(),
    userType: String,
    viewModel: HomepageViewModel
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val startDestination = if (userType.equals("driver", ignoreCase = true)) {
        "homepage_driver"
    } else {
        "homepage_customer"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("homepage_customer") {
            HomepageCustomerScreenUi(state = uiState)
        }
        composable("homepage_driver") {
//            HomepageDriverScreenUi(uiState = uiState)
        }
    }
}
