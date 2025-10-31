package com.example.nebeng.feature_homepage.presentation.fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nebeng.app.ui.common.RoleAwareFragment
import com.example.nebeng.feature_homepage.presentation.HomepageViewModel
import com.example.nebeng.feature_homepage.presentation.screen_role.customer.HomepageCustomerScreenUi
import com.example.nebeng.feature_homepage.presentation.screen_role.driver.HomepageDriverScreenUi
import dagger.hilt.android.AndroidEntryPoint

//import com.example.nebeng.feature_homepage.presentation.homepage.screen_role.customer.HomepageCustomerScreen
//import com.example.nebeng.feature_homepage.presentation.homepage.screen_role.driver.HomepageDriverScreen

//class HomepageFragment : RoleAwareFragment() {
//
//    private var _binding: FragmentHomepageBinding? = null
//    private val binding get() = _binding!!
//
//    private val viewModel: HomepageViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.composeView.setContent {
//            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//            // Cek role user
//            when (uiState.currentUser?.user_type?.lowercase()) {
//                "driver" -> HomepageDriverScreenUi(uiState)
//                else -> HomepageCustomerScreenUi(uiState)
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//    @Composable
//    override fun CustomerUI() {
////        HomepageCustomerScreen()
//    }
//
//    @Composable
//    override fun DriverUI() {
////        HomepageDriverScreen()
//    }
//}
@AndroidEntryPoint
class HomepageFragment : RoleAwareFragment() {

    private val viewModel: HomepageViewModel by viewModels()

    @Composable
    override fun CustomerUI() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomepageCustomerScreenUi(uiState)
    }

    @Composable
    override fun DriverUI() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomepageDriverScreenUi(uiState)
    }
}
