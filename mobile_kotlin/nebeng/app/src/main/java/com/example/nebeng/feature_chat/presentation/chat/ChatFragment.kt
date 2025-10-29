package com.example.nebeng.feature_chat.presentation.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nebeng.app.ui.common.LoadingScreen
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//
//class ChatFragment : Fragment() {
//
//    private var _binding: FragmentChatBinding? = null
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
//        _binding = FragmentChatBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.composeView.apply {
//            setContent {
//                MaterialTheme(
//                    colorScheme = lightColorScheme()
//                ) {
//                    ChatScreen()
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

@AndroidEntryPoint
class ChatFragment : Fragment() {
    @Inject
    lateinit var  userPrefsRepo: UserPreferencesRepository

    private var _binding: FragmentChatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.composeView.setContent {
//            val userType by userPrefsRepo.userTypeFlow.collectAsState(initial = "customer")
//
//            MaterialTheme(colorScheme = lightColorScheme()) {
//                when(userType) {
//                    "driver"    -> ChatDriverScreen()
//                    else        -> ChatCustomerScreen()
//                }
//            }
//        }
//        viewLifecycleOwner.lifecycleScope.launch {
//            userPrefsRepo.userTypeFlow.collect { role ->
//                binding.composeView.apply {
//                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//                    setContent {
//                        MaterialTheme(colorScheme = lightColorScheme()) {
//                            when(role) {
//                                "driver"    -> ChatDriverScreen()
//                                else        -> ChatCustomerScreen()
//                            }
//                        }
//                    }
//                }
//            }
//        }
        // 🔹 Pastikan hanya diinisialisasi sekali
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme(colorScheme = lightColorScheme()) {

                    // 🔹 Observe flow sebagai state Compose
                    val role by userPrefsRepo.userTypeFlow
                        .collectAsStateWithLifecycle(initialValue = null)

                    when (role) {
                        "driver" -> ChatDriverScreen()
                        "customer" -> ChatCustomerScreen()
                        else -> LoadingScreen()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}