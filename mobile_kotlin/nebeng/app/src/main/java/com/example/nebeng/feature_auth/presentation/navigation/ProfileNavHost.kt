package com.example.nebeng.feature_auth.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.feature_auth.presentation.profile.DeleteAccountScreen
import com.example.nebeng.feature_auth.presentation.profile.EditProfileScreen
import com.example.nebeng.feature_auth.presentation.profile.ProfileDetailScreen
import com.example.nebeng.feature_auth.presentation.profile.ProfileScreen
import com.example.nebeng.feature_auth.presentation.profile.UserListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "profile_home"
    ) {
        // ===============================
        // Halaman utama (menu profil)
        // ===============================
        composable("profile_home") {
            ProfileScreen(navController)
        }

        // ===============================
        // Edit Profil
        // ===============================
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
                    userId = 1, // nanti ambil dari DataStore
                    usernameInit = "admin",
                    passwordInit = "1234",
                    role = "admin",
                    modifier = Modifier.padding(padding)
                )
            }
        }

        // ===============================
        // Hapus Akun
        // ===============================
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
                        // Setelah akun terhapus, arahkan ke login
                        navController.navigate("login") {
                            popUpTo("profile_home") { inclusive = true }
                        }
                    }
                )
            }
        }

        // ===============================
        // Detail Profil
        // ===============================
        composable("profile_detail") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Detail Profil") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                            }
                        }
                    )
                }
            ) { padding ->
                ProfileDetailScreen(
                    modifier = Modifier.padding(padding)
                )
            }
        }

        // ===============================
        // Daftar Pengguna
        // ===============================
        composable("user_list") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Daftar Pengguna") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                            }
                        }
                    )
                }
            ) { padding ->
                UserListScreen(modifier = Modifier.padding(padding))
            }
        }
    }
}