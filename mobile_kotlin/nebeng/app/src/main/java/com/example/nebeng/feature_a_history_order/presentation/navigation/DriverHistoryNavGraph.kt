package com.example.nebeng.feature_a_history_order.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderUiState
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderViewModel
import com.example.nebeng.feature_a_history_order.presentation.screen_role.driver.DetailHistoryOrderDriverScreen
import com.example.nebeng.feature_a_history_order.presentation.screen_role.driver.HistoryOrderDriverScreenUi

@Composable
fun DriverHistoryNavGraph(
    viewModel: HistoryOrderViewModel,   // <-- ViewModel DI-INJEK dari Fragment
    onBack: () -> Unit                  // <-- Back handler dari Fragment
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "history_driver_screen"
    ) {

        // ============================
        // 1) Driver History List Screen
        // ============================
        composable("history_driver_screen") {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            HistoryOrderDriverScreenUi(
                uiState = uiState,
                onBack = onBack,
                onRefresh = {
                    // fragment nanti memanggil ulang loadHistory(token)
                    // jadi ini hanya trigger saja
                },
                onHistoryClick = { orderId ->
                    navController.navigate("detail_history/$orderId")
                }
            )
        }

        // ============================
        // 2) Detail Order Screen
        // ============================
        composable(
            route = "detail_history/{orderId}",
            arguments = listOf(
                navArgument("orderId") { type = NavType.IntType }
            )
        ) { entry ->

            val orderId = entry.arguments?.getInt("orderId") ?: 0
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            DetailHistoryOrderDriverScreen(
                orderId = orderId,
                uiState = uiState,
                onBack = { navController.popBackStack() },
                onRetry = {
                    // biasanya panggil ulang loadHistory() di Fragment
                }
            )
        }
    }
}


