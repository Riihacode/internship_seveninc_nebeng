package com.example.nebeng.feature_a_authentication.presentation.profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.feature_a_authentication.presentation.AuthenticationViewModel
import com.example.nebeng.feature_a_authentication.presentation.navigation.ProfileNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // 🧱 Jangan buang komposisi saat fragment re-attach
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                MaterialTheme {
                    // 🧩 1️⃣ Buat navController yang bertahan di state Compose
                    val navController = rememberNavController()

                    // 🧩 2️⃣ Buat ViewModel dengan scope fragment (bukan tiap screen)
//                    val viewModel: ProfileViewModel = hiltViewModel()
                    val viewModel: AuthenticationViewModel = hiltViewModel()


                    // 🧩 3️⃣ Ambil state user_type sekali
//                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//                    val role = uiState.currentUser?.user_type ?: "customer"
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    val role = uiState.auth?.userType?.name ?: "customer"


                    // 🧩 4️⃣ Navigasi internal profile
                    ProfileNavHost(
                        navController = navController,
                        userType = role
//                        viewModel = viewModel
                    )
                }
            }
        }
    }
}