package com.example.nebeng.feature_a_history_order.presentation.fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nebeng.app.ui.common.RoleAwareFragment
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderViewModel
import com.example.nebeng.feature_a_history_order.presentation.navigation.CustomerHistoryNavGraph
import com.example.nebeng.feature_a_history_order.presentation.navigation.DriverHistoryNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * ============================================================
 * 🧭 Fragment: HistoryOrderFragment
 * ------------------------------------------------------------
 * Mengatur pemilihan UI sesuai role (customer/driver).
 * Menyediakan token dari DataStore dan memanggil ViewModel.
 * ============================================================
 */
@AndroidEntryPoint
class HistoryOrderFragment : RoleAwareFragment() {

    private val viewModel: HistoryOrderViewModel by viewModels()

    @Inject
    lateinit var userPrefsRepo: UserPreferencesRepository

    // ========================
    // 🔹 CUSTOMER VIEW
    // ========================
    @Composable
    override fun CustomerUI() {
        val userState = userPrefsRepo.currentUserFlow.collectAsStateWithLifecycle(initialValue = null)
        val customerIdState = userPrefsRepo.customerIdFlow.collectAsStateWithLifecycle(initialValue = 0)


        LaunchedEffect(userState.value) {
            val user = userState.value ?: return@LaunchedEffect
            val customerId = customerIdState.value
            if (user.token.isBlank()) return@LaunchedEffect

            viewModel.loadHistory(
                token = user.token,
//                customerId = user.id
                customerId = customerId
            )
        }

        CustomerHistoryNavGraph(
            viewModel = viewModel,
            onBack = { requireActivity().onBackPressedDispatcher.onBackPressed() }
        )
    }

    // ========================
    // 🔹 DRIVER VIEW
    // ========================
    @Composable
    override fun DriverUI() {
//        val userState = userPrefsRepo.currentUserFlow.collectAsStateWithLifecycle(initialValue = null)
//
//        LaunchedEffect(userState.value) {
//            val user = userState.value ?: return@LaunchedEffect
//
//            viewModel.loadHistory(
//                token = user.token,
//                customerId = user.id
//            )
//        }
//
//        DriverHistoryNavGraph(
//            viewModel = viewModel,
//            onBack = { requireActivity().onBackPressedDispatcher.onBackPressed() }
//        )
    }
}