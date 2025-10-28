package com.example.nebeng.app.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nebeng.R
import com.example.nebeng.app.ui.AppRoleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class RoleAwareFragment : Fragment() {
    private val appRoleViewModel: AppRoleViewModel by viewModels()

    @Composable
    abstract fun CustomerUI()

    @Composable
    abstract fun DriverUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val composeView = ComposeView(requireContext()).apply {
            // Pastikan ComposeView mempertahankan state
            isSaveEnabled = true
            setContent {
                val userType by appRoleViewModel.userTypeFlow.collectAsStateWithLifecycle(initialValue = "")
                MaterialTheme {
                    when (userType) {
                        "customer" -> CustomerUI()
                        "driver" -> DriverUI()
                        else -> {}
                    }
                }
            }
        }

        return composeView
    }
}