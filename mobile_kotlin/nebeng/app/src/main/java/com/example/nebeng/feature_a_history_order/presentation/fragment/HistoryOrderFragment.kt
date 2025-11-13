package com.example.nebeng.feature_a_history_order.presentation.fragment

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nebeng.app.ui.common.RoleAwareFragment
import com.example.nebeng.core.session.data.UserPreferencesRepository
//import com.example.nebeng.feature_a_history_order.domain.mapper.HistoryOrderItemMapper.toDomain
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderViewModel
import com.example.nebeng.feature_a_history_order.presentation.navigation.CustomerHistoryNavGraph
import com.example.nebeng.feature_a_history_order.presentation.navigation.DriverHistoryNavGraph
import com.example.nebeng.feature_a_history_order.presentation.screen_role.customer.HistoryOrderCustomerScreenUi
import com.example.nebeng.feature_a_history_order.presentation.screen_role.driver.HistoryOrderDriverScreenUi
//import com.example.nebeng.feature_a_history_order.presentation.screen_role.driver.HistoryOrderDriverScreenUi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//class HistoryOrderFragment: RoleAwareFragment() {
//    @Composable
//    override fun CustomerUI() {
////        HomepageCustomerScreen()
//    }
//
//    @Composable
//    override fun DriverUI() {
////        HomepageDriverScreen()
//    }
//
//}

//@AndroidEntryPoint
//class HistoryOrderFragment : RoleAwareFragment() {
//
//    private val viewModel: HistoryOrderViewModel by viewModels()
//
//    @Composable
//    override fun CustomerUI() {
//        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
////        HistoryOrderCustomerScreenUi(uiState)
//    }
//
//    @Composable
//    override fun DriverUI() {
//        // nanti diisi
//        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
////        HistoryOrderDriverScreenUi(uiState)
//    }
//}

//@AndroidEntryPoint
//class HistoryOrderFragment : RoleAwareFragment() {
//
//    private val viewModel: HistoryOrderViewModel by viewModels()
//    @Inject lateinit var userPrefsRepo: UserPreferencesRepository   // token ambil via hilt
//
////    @Composable
////    override fun CustomerUI() {
////        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
////        HistoryOrderCustomerScreenUi(
////            uiState = uiState,
////            onBack = { requireActivity().onBackPressedDispatcher.onBackPressed() },
////            onChangeSchedule = { viewModel.onChangeSchedule(it) }
////        )
////    }
//    @Composable
//    override fun CustomerUI() {
//        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
//        val token by userPrefsRepo.tokenFLow.collectAsStateWithLifecycle(initialValue = null)
//
//        // Panggil loadHistory sekali
//        LaunchedEffect(token) {
//            token?.let {
//                viewModel.loadHistory(it)
//            }
//        }
//        HistoryOrderCustomerScreenUi(
//            uiState = uiState,
//            onBack = { requireActivity().onBackPressedDispatcher.onBackPressed() },
////            onChangeSchedule = { viewModel.onChangeSchedule(it) }
//            onChangeSchedule = { data ->
//                viewModel.onChangeSchedule(data.toDomain()) // gunakan mapper dari UI → domain
//            }
//
//        )
//    }
//
//    @Composable
//    override fun DriverUI() {
////        Text("Driver view belum diimplementasikan", modifier = Modifier.padding(16.dp))
//        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
////        HistoryOrderDriverScreenUi(
////            state = uiState
////        )
//    }
//
//    private fun getTokenFromPrefs(): String {
//        return "jwt-token-placeholder"
//    }
//}


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
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        val token by userPrefsRepo.tokenFLow.collectAsStateWithLifecycle(initialValue = null)

        // Panggil loadHistory hanya saat token valid
        LaunchedEffect(token) {
            token?.let {
                Log.d("HistoryOrderFragment", "Token digunakan: $it")
                viewModel.loadHistory(it)
            }
        }

//        HistoryOrderCustomerScreenUi(
//            uiState = uiState,
//            onBack = { requireActivity().onBackPressedDispatcher.onBackPressed() },
//            onChangeSchedule = { viewModel.onChangeSchedule(it) }
//        )
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
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        val token by userPrefsRepo.tokenFLow.collectAsStateWithLifecycle(initialValue = null)
        // Placeholder: nanti diganti dengan UI driver versi final

        // Panggil loadHistory hanya saat token valid
        LaunchedEffect(token) {
            token?.let {
                Log.d("HistoryOrderFragment", "Token digunakan: $it")
                viewModel.loadHistory(it)
            }
        }

//        HistoryOrderDriverScreenUi(
//            uiState = uiState,
//            onBack = { requireActivity().onBackPressedDispatcher.onBackPressed() },
//            onRefresh = {
//                token?.let {
//                    Log.d("HistoryOrderFragment", "Token digunakan: $it")
//                    viewModel.loadHistory(it)
//                }
//            }
//        )

        DriverHistoryNavGraph(
            viewModel = viewModel,
            onBack = { requireActivity().onBackPressedDispatcher.onBackPressed() }
        )
    }
}