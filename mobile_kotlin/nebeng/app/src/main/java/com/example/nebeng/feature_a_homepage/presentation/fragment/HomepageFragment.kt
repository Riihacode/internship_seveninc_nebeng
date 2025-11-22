package com.example.nebeng.feature_a_homepage.presentation.fragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nebeng.app.ui.MainActivity
import com.example.nebeng.app.ui.common.RoleAwareFragment
import com.example.nebeng.feature_a_homepage.presentation.HomepageViewModel
import com.example.nebeng.feature_a_homepage.presentation.navigation.HomepageNavHost
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

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun CustomerUI() {
        HomepageNavHost(
            userType = "customer",
            viewModel = viewModel,
            onRouteChanged = { route ->
                val hide = route.startsWith("nebeng_motor") || route == "passenger_motor_map"
                setBottomNavVisible(!hide)
            }
        )
    }

    @Composable
    override fun DriverUI() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//        HomepageDriverScreenUi(uiState)
    }

    fun setBottomNavVisible(visible: Boolean) {
        val activity = requireActivity() as MainActivity
        activity.setBottomNavVisibility(visible)
    }
}
