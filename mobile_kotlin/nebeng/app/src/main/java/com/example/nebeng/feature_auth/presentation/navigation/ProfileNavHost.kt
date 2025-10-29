package com.example.nebeng.feature_auth.presentation.navigation

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.feature_auth.presentation.profile.screen_role.customer.ProfileCustomerScreen
import com.example.nebeng.feature_auth.presentation.profile.screen_role.driver.ProfileDriverScreen
import com.example.nebeng.feature_auth.presentation.profile.screen_base.DeleteAccountScreen
import com.example.nebeng.feature_auth.presentation.profile.screen_base.EditProfileScreen
import com.example.nebeng.feature_auth.presentation.profile.screen_base.ProfileDetailScreen
import com.example.nebeng.feature_auth.presentation.profile.support_for_present.userlist.UserListScreen

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileNavHost(
//    navController: NavHostController = rememberNavController()
//) {
//    NavHost(
//        navController = navController,
//        startDestination = "profile_home"
//    ) {
//        // ===============================
//        // Halaman utama (menu profil)
//        // ===============================
//        composable("profile_home") {
//            ProfileScreen(navController)
//        }
//
//        // ===============================
//        // Edit Profil
//        // ===============================
//        composable("edit_profile") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Edit Profil") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                EditProfileScreen(
//                    userId = 1, // nanti ambil dari DataStore
//                    nameInit = "adm",
//                    usernameInit = "admin",
//                    passwordInit = "1234",
//                    userTypeInit = "admin",
//                    modifier = Modifier.padding(padding)
//                )
//            }
//        }
//
//        // ===============================
//        // Hapus Akun
//        // ===============================
//        composable("delete_account") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Hapus Akun") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                DeleteAccountScreen(
//                    modifier = Modifier.padding(padding),
//                    onAccountDeleted = {
//                        // Setelah akun terhapus, arahkan ke login
//                        navController.navigate("login") {
//                            popUpTo("profile_home") { inclusive = true }
//                        }
//                    }
//                )
//            }
//        }
//
//        // ===============================
//        // Detail Profil
//        // ===============================
//        composable("profile_detail") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Detail Profil") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                ProfileDetailScreen(
//                    modifier = Modifier.padding(padding)
//                )
//            }
//        }
//
//        // ===============================
//        // Daftar Pengguna
//        // ===============================
//        composable("user_list") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Daftar Pengguna") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                UserListScreen(modifier = Modifier.padding(padding))
//            }
//        }
//    }
//}

// [ROLE] Pembagian UI Per Role
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileNavHost(
//    navController: NavHostController = rememberNavController(),
//    userType: String
//) {
//    // Bekukan startDestination hanya sekali (saat userType sudah valid)
//    val frozenStart = rememberSaveable(userType.isNotBlank()) {
//        mutableStateOf<String?>(if (userType.isNotBlank()) {
//            if (userType == "driver") "profile_driver_homepage" else "profile_customer_homepage"
//        } else null)
//    }
//
//    // Jika userType baru datang dan belum ada frozenStart, set sekarang lalu JANGAN diubah lagi
//    LaunchedEffect(userType) {
//        if (frozenStart.value == null && userType.isNotBlank()) {
//            frozenStart.value =
//                if (userType == "driver") "profile_driver_homepage" else "profile_customer_homepage"
//        }
//    }
//
//    val startDestination = frozenStart.value ?: "profile_loading"
//
//
////    val startDestination = when(userType) {
////        "driver"    -> "profile_driver_homepage"
////        else        -> "profile_customer_homepage"
////    }
//
//    NavHost(
//        navController = navController,
//        startDestination = startDestination
//    ) {
//
//        // Loading sementara sampai role siap
//        composable("profile_loading") {
//            // boleh pakai layar sederhana biar gak blank
//            androidx.compose.material3.Scaffold { p ->
//                androidx.compose.material3.Text(
//                    "Loading…",
//                    modifier = Modifier.padding(p).padding(24.dp)
//                )
//            }
//        }
//
//        composable("profile_customer_homepage") {
//            ProfileCustomerScreen(navController)
//        }
//
//        composable("profile_driver_homepage") {
//            ProfileDriverScreen(navController)
//        }
//
//        // ===============================
//        // Edit Profil
//        // ===============================
//        composable("edit_profile") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Edit Profil") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                EditProfileScreen(
//                    userId = 1, // nanti ambil dari DataStore
//                    nameInit = "adm",
//                    usernameInit = "admin",
//                    passwordInit = "1234",
//                    userTypeInit = "admin",
//                    modifier = Modifier.padding(padding)
//                )
//            }
//        }
//
//        // ===============================
//        // Hapus Akun
//        // ===============================
//        composable("delete_account") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Hapus Akun") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                DeleteAccountScreen(
//                    modifier = Modifier.padding(padding),
//                    onAccountDeleted = {
//                        // Setelah akun terhapus, arahkan ke login
//                        navController.navigate("login") {
//                            popUpTo("profile_home") { inclusive = true }
//                        }
//                    }
//                )
//            }
//        }
//
//        // ===============================
//        // Detail Profil
//        // ===============================
//        composable("profile_detail") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Detail Profil") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                ProfileDetailScreen(
//                    modifier = Modifier.padding(padding)
//                )
//            }
//        }
//
//        // ===============================
//        // Daftar Pengguna
//        // ===============================
//        composable("user_list") {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text("Daftar Pengguna") },
//                        navigationIcon = {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                            }
//                        }
//                    )
//                }
//            ) { padding ->
//                UserListScreen(modifier = Modifier.padding(padding))
//            }
//        }
//    }
//}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileNavHost(
    navController: NavHostController = rememberNavController(),
    userType: String
) {
    // freeze startDestination hanya sekali
    val frozenStart = rememberSaveable(userType.isNotBlank()) {
        mutableStateOf<String?>(
            if (userType.isNotBlank()) {
                if (userType == "driver") "profile_driver_homepage" else "profile_customer_homepage"
            } else null
        )
    }

    // update start destination jika belum terisi
    LaunchedEffect(userType) {
        if (frozenStart.value == null && userType.isNotBlank()) {
            frozenStart.value =
                if (userType == "driver") "profile_driver_homepage" else "profile_customer_homepage"
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

        // 🔹 Homepage customer / driver
        composable("profile_customer_homepage") {
            ProfileCustomerScreen(navController)
        }
        composable("profile_driver_homepage") {
            ProfileDriverScreen(navController)
        }

        // 🔹 Edit Profil
        composable("edit_profile") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Edit Profil") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                            }
                        }
                    )
                }
            ) { padding ->
                EditProfileScreen(
                    userId = 1, // TODO: ambil dari userPrefRepo nanti
                    nameInit = "adm",
                    usernameInit = "admin",
                    passwordInit = "1234",
                    userTypeInit = "admin",
                    modifier = Modifier.padding(padding)
                )
            }
        }

        // 🔹 Hapus Akun
        composable("delete_account") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Hapus Akun") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                            }
                        }
                    )
                }
            ) { padding ->
                DeleteAccountScreen(
                    modifier = Modifier.padding(padding),
                    onAccountDeleted = {
                        // arahkan ke login
                        navController.navigate("profile_customer_homepage") {
                            popUpTo("profile_customer_homepage") { inclusive = true }
                        }
                    }
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
