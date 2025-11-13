package com.example.nebeng.feature_a_history_order.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderViewModel
import com.example.nebeng.feature_a_history_order.presentation.screen_role.customer.DetailHistoryOrderCustomerScreen
import com.example.nebeng.feature_a_history_order.presentation.screen_role.customer.HistoryOrderCustomerScreenUi

@Composable
fun CustomerHistoryNavGraph(
    viewModel: HistoryOrderViewModel,
    onBack: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "history_customer_screen"
    ) {

        // ======================================================
        // 1) LIST SCREEN (History Customer)
        // ======================================================
        composable("history_customer_screen") {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            HistoryOrderCustomerScreenUi(
                uiState = uiState,
                onBack = onBack,
                onChangeSchedule = { item ->
                    navController.navigate("detail_history_customer/${item.bookingId}")
                }
            )
        }

        // ======================================================
        // 2) DETAIL SCREEN (Customer detail)
        // ======================================================
        composable(
            route = "detail_history_customer/{orderId}",
            arguments = listOf(
                navArgument("orderId") { type = NavType.IntType }
            )
        ) { entry ->

            val orderId = entry.arguments?.getInt("orderId") ?: 0
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            DetailHistoryOrderCustomerScreen(
                orderId = orderId,
                uiState = uiState,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
