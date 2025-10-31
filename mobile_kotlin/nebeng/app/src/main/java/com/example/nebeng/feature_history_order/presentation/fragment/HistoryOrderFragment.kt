package com.example.nebeng.feature_history_order.presentation.fragment

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nebeng.app.ui.common.RoleAwareFragment
import com.example.nebeng.feature_history_order.presentation.HistoryOrderViewModel
import com.example.nebeng.feature_history_order.presentation.screen_role.customer.HistoryOrderCustomerScreenUi
import com.example.nebeng.feature_history_order.presentation.screen_role.driver.HistoryOrderDriverScreenUi
import dagger.hilt.android.AndroidEntryPoint

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

@AndroidEntryPoint
class HistoryOrderFragment : RoleAwareFragment() {

    private val viewModel: HistoryOrderViewModel by viewModels()

    @Composable
    override fun CustomerUI() {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        HistoryOrderCustomerScreenUi(uiState)
    }

    @Composable
    override fun DriverUI() {
        // nanti diisi
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
//        HistoryOrderDriverScreenUi(uiState)
    }
}