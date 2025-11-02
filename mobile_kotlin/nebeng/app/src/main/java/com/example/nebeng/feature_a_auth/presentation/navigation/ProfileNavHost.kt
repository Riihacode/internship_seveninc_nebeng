package com.example.nebeng.feature_a_auth.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.feature_a_auth.presentation.profile.ProfileViewModel
//import com.example.nebeng.feature_auth.presentation.profile.screen_role.customer.ProfileCustomerScreen
//import com.example.nebeng.feature_auth.presentation.profile.screen_role.driver.ProfileDriverScreen
import com.example.nebeng.feature_a_auth.presentation.profile.screen_base.DeleteAccountScreen
import com.example.nebeng.feature_a_auth.presentation.profile.screen_base.EditProfileScreen
import com.example.nebeng.feature_a_auth.presentation.profile.screen_base.ProfileDetailScreen
import com.example.nebeng.feature_a_auth.presentation.profile.screen_role.customer.ProfileCustomerScreenUi
import com.example.nebeng.feature_a_auth.presentation.profile.screen_role.driver.ProfileDriverScreenUi
import com.example.nebeng.feature_a_auth.presentation.profile.support_for_present.userlist.UserListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileNavHost(
    navController: NavHostController = rememberNavController(),
    userType: String,
    viewModel: ProfileViewModel
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val user = uiState.currentUser

    // freeze startDestination hanya sekali
    val frozenStart = rememberSaveable(userType.isNotBlank()) {
        mutableStateOf(
            if (userType.isNotBlank()) {
//                if (userType == "driver") "profile_driver_homepage" else "profile_customer_homepage"
                if (userType == "driver") "profile_driver_main" else "profile_customer_main"
            } else null
        )
    }

    // update start destination jika belum terisi
    LaunchedEffect(userType) {
        if (frozenStart.value == null && userType.isNotBlank()) {
            frozenStart.value =
//                if (userType == "driver") "profile_driver_homepage" else "profile_customer_homepage"
                if (userType == "driver") "profile_driver_mian" else "profile_customer_main"
        }
    }

    val startDestination = frozenStart.value ?: "profile_loading"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // 🔹 Loading sementara
        composable("profile_loading") {
            Scaffold { p ->
                Box(
                    Modifier
                        .padding(p)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

//        // 🔹 Homepage customer / driver
//        composable("profile_customer_homepage") {
//            ProfileCustomerScreen(navController)
//        }
//        composable("profile_driver_homepage") {
//            ProfileDriverScreen(navController)
//        }
        // 🔹 Homepage customer / driver
        composable("profile_customer_main") {
            ProfileCustomerScreenUi(navController)
        }
        composable("profile_driver_main") {
            ProfileDriverScreenUi(navController)
        }

        composable("edit_profile") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            SimpleScaffoldWithBack(navController, "Edit Profil") { padding ->
                EditProfileScreen(
                    modifier = Modifier.padding(padding),
                    state = uiState,
                    onSave = { id, name, username, password, userType ->
                        viewModel.updateProfile(id, name, username, password, userType)
                    }
                )
            }
        }

        // 🔹 Hapus Akun
        composable("delete_account") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            SimpleScaffoldWithBack(navController, "Hapus Akun") { padding ->
                DeleteAccountScreen(
                    modifier = Modifier.padding(padding),
                    state = uiState,
                    onDeleteAccount = { userId ->
                        viewModel.deleteAccount(
                            id = userId,
                            onSuccess = {
                                viewModel.logout {
                                    navController.navigate("login") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            },
                            onError = { msg ->
                                Log.e("ProfileNavHost", "Deleted failed: $msg")
                            }
                        )
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
        }

        // 🔹 Detail Profil
        composable("profile_detail") {
            SimpleScaffoldWithBack(navController, "Detail Profil") {
                ProfileDetailScreen(modifier = Modifier.padding(it))
            }
        }

        // 🔹 Daftar Pengguna
        composable("user_list") {
            SimpleScaffoldWithBack(navController, "Daftar Pengguna") {
                UserListScreen(modifier = Modifier.padding(it))
            }
        }

        // ===================================================
        // 🔹 Placeholder route aman (belum diimplementasikan)
        // ===================================================
        composable("verify_ktp") { PlaceholderScreen("Verifikasi KTP", navController) }
        composable("change_password") { PlaceholderScreen("Ubah Password", navController) }
        composable("help_center") { PlaceholderScreen("Pusat Bantuan", navController) }
        composable("driver_history") { PlaceholderScreen("Riwayat Penjemputan", navController) }
        composable("withdraw_balance") { PlaceholderScreen("Tarik Saldo", navController) }
    }
}

// =========================
// Helper reusable scaffold
// =========================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SimpleScaffoldWithBack(
    navController: NavHostController,
    title: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        },
        content = content
    )
}

// =========================
// Placeholder Screen
// =========================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceholderScreen(
    title: String,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Halaman $title belum tersedia.")
        }
    }
}
