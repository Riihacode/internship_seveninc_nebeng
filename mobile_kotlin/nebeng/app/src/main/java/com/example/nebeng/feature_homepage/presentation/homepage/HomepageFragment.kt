package com.example.nebeng.feature_homepage.presentation.homepage

import androidx.compose.runtime.Composable
import com.example.nebeng.app.ui.common.RoleAwareFragment

//class HomepageFragment : Fragment() {
//
//    private var _binding: FragmentHomepageBinding? = null
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
//        val homepageViewModel =
//            ViewModelProvider(this).get(HomepageViewModel::class.java)
//
//        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textDashboard
//        homepageViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}

//class HomepageFragment : Fragment() {
//
//    private var _binding: FragmentHomepageBinding? = null
//    private val binding get() = _binding!!
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
//        // Hindari memory leak (ComposeView lifecycle mengikuti Fragment)
//        binding.composeView.apply {
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent {
//                MaterialTheme(
//                    colorScheme = lightColorScheme(),
//                ) {
//                    HomeScreen(
//                        userName = "Nadya Amalya",
//                        points = 0
//                    )
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

class HomepageFragment : RoleAwareFragment() {
    @Composable
    override fun CustomerUI() {
        HomepageCustomerScreen()
    }

    @Composable
    override fun DriverUI() {
        HomepageDriverScreen()
    }
}