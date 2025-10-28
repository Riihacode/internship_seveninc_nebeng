package com.example.nebeng.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nebeng.R
import com.example.nebeng.app.ui.navigation.CustomerNavGraph
import com.example.nebeng.app.ui.navigation.DriverNavGraph
import com.example.nebeng.databinding.ActivityMainBinding
import com.example.nebeng.feature_auth.presentation.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val appRoleViewModel: AppRoleViewModel by viewModels()

    private var hasSetupGraph = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    appRoleViewModel.isLoggedInFlow,
                    appRoleViewModel.userTypeFlow
                ) { isLoggedIn, userType ->
                    isLoggedIn to userType
                }.collect { (isLoggedIn, userType) ->
                    if (!isLoggedIn) {
                        redirectToAuth()
                    } else {
                        setupGraphOnce(navController, userType)
                    }
                }
            }
        }
    }

    private fun setupGraphOnce(navController: NavController, role: String?) {
        if(hasSetupGraph) return
        hasSetupGraph = true

        when(role?.lowercase()) {
            "customer"  -> CustomerNavGraph.setup(navController)
            "driver"    -> DriverNavGraph.setup(navController)
            else        -> redirectToAuth()
        }
    }

    private fun redirectToAuth() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}