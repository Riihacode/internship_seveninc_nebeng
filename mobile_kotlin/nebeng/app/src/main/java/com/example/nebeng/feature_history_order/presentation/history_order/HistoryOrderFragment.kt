package com.example.nebeng.feature_history_order.presentation.history_order

import androidx.compose.runtime.Composable
import com.example.nebeng.app.ui.common.RoleAwareFragment
import com.example.nebeng.feature_homepage.presentation.homepage.HomepageCustomerScreen
import com.example.nebeng.feature_homepage.presentation.homepage.HomepageDriverScreen

//class HistoryOrderFragment : Fragment() {
//
//    private var _binding: FragmentHistoryOrderBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHistoryOrderBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.composeView.apply {
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent {
//                MaterialTheme(
//                    colorScheme = lightColorScheme()
//                ) {
//                    HistoryOrderScreen()
//                }
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}

class HistoryOrderFragment: RoleAwareFragment() {
    @Composable
    override fun CustomerUI() {
        HomepageCustomerScreen()
    }

    @Composable
    override fun DriverUI() {
        HomepageDriverScreen()
    }

}