//package com.example.nebeng.feature_auth.presentation.profile.screen_base
//
//import android.app.Activity
//import android.content.Intent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import com.example.nebeng.feature_auth.presentation.auth.AuthActivity
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.DividerDefaults
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.compose.rememberNavController
//import com.example.nebeng.R
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import com.example.nebeng.app.ui.common.ErrorScreen
//import com.example.nebeng.app.ui.common.LoadingScreen
//import com.example.nebeng.feature_auth.presentation.profile.ProfileViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BaseProfileScreen(
//    navController: NavHostController,
//    viewModel: ProfileViewModel = hiltViewModel()
//) {
//    val context = LocalContext.current
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    var showLogoutDialog by remember { mutableStateOf(false) }
//
//    val currentUser = uiState.currentUser
//    val isDriver = currentUser?.user_type?.equals("driver", ignoreCase = true) == true
//
//    // === React to one-time events ===
//    LaunchedEffect(uiState.isLoggedOut) {
//        if (uiState.isLoggedOut) {
//            val intent = Intent(context, AuthActivity::class.java).apply {
////                Intent.setFlags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            context.startActivity(intent)
//            (context as? Activity)?.finish()
//        }
//    }
//
//    LaunchedEffect(uiState.isDeleted) {
//        if (uiState.isDeleted) {
//            val intent = Intent(context, AuthActivity::class.java).apply {
////                Intent.setFlags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            context.startActivity(intent)
//            (context as? Activity)?.finish()
//        }
//    }
//
//    // === Main UI ===
//    when {
//        uiState.isLoading               -> LoadingScreen()
//        uiState.errorMessage != null    -> ErrorScreen(uiState.errorMessage)
//        else -> {
//            Scaffold(containerColor = Color(0xFFF8F9FD)) { padding ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .verticalScroll(rememberScrollState())
//                        .padding(bottom = 96.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//
//                    // ===== HEADER =====
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(160.dp)
//                            .background(
//                                Brush.verticalGradient(
//                                    listOf(Color(0xFF1877F2), Color(0xFF3B8DFE))
//                                )
//                            )
//                    ) {
//                        Text(
//                            text = "Akun",
//                            color = Color.White,
//                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
//                            modifier = Modifier
//                                .align(Alignment.TopStart)
//                                .padding(start = 24.dp, top = 24.dp)
//                        )
//                    }
//
//                    // ===== PROFILE CARD =====
//                    Card(
//                        modifier = Modifier
//                            .padding(horizontal = 24.dp)
//                            .offset(y = (-40).dp)
//                            .fillMaxWidth(),
//                        shape = RoundedCornerShape(16.dp),
//                        elevation = CardDefaults.cardElevation(6.dp)
//                    ) {
//                        Column(
//                            modifier = Modifier.padding(16.dp),
//                            verticalArrangement = Arrangement.spacedBy(12.dp)
//                        ) {
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.spacedBy(12.dp)
//                            ) {
//                                Box(
//                                    modifier = Modifier
//                                        .size(56.dp)
//                                        .clip(CircleShape)
//                                        .background(Color(0xFFEAF2FF)),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_profile_black_24),
//                                        contentDescription = "Profile",
//                                        tint = Color(0xFF1877F2),
//                                        modifier = Modifier.size(28.dp)
//                                    )
//                                }
//
//                                Column(modifier = Modifier.weight(1f)) {
//                                    Text(
//                                        text = currentUser?.name ?: "Memuat...",
//                                        style = MaterialTheme.typography.titleMedium.copy(
//                                            fontWeight = FontWeight.Bold
//                                        )
//                                    )
//                                    Text(
//                                        text = currentUser?.username ?: "-",
//                                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
//                                    )
//                                }
//
//                                IconButton(onClick = {
//                                    navController.navigate("edit_profile")
//                                }) {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_edit_black_24),
//                                        contentDescription = "Edit",
//                                        tint = Color(0xFF1877F2)
//                                    )
//                                }
//                            }
//
//                            // Warning section (Verifikasi KTP)
//                            if (isDriver) {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .clip(RoundedCornerShape(8.dp))
//                                        .background(Color(0xFFFFEAEA))
//                                        .padding(12.dp)
//                                ) {
//                                    Row(
//                                        horizontalArrangement = Arrangement.SpaceBetween,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        verticalAlignment = Alignment.CenterVertically
//                                    ) {
//                                        Text(
//                                            "Verifikasi KTP dulu yuk",
//                                            color = Color.Black,
//                                            style = MaterialTheme.typography.bodyMedium
//                                        )
//                                        Text(
//                                            "Verifikasi sekarang",
//                                            color = Color(0xFFD32F2F),
//                                            fontWeight = FontWeight.Bold,
//                                            style = MaterialTheme.typography.bodyMedium,
//                                            modifier = Modifier.clickable {
//                                                navController.navigate("verify_ktp")
//                                            }
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    Spacer(Modifier.height(8.dp))
//
//                    // ===== MENU SECTION =====
//                    MenuSection(navController = navController, isDriver = isDriver)
//
//                    // ===== BUTTON SECTION =====
//                    Column(
//                        modifier = Modifier
//                            .padding(horizontal = 24.dp, vertical = 16.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Button(
//                            onClick = { navController.navigate("user_list") },
//                            modifier = Modifier.fillMaxWidth()
//                        ) { Text("Daftar Pengguna") }
//
//                        Button(
//                            onClick = { navController.navigate("delete_account") },
//                            modifier = Modifier.fillMaxWidth(),
//                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEAEA))
//                        ) { Text("Hapus Akun", color = Color(0xFFD32F2F)) }
//
//                        Button(
//                            onClick = { showLogoutDialog = true },
//                            modifier = Modifier.fillMaxWidth(),
//                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2F3F5))
//                        ) {
//                            Text("Logout", color = Color.Black)
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    // ===== LOGOUT DIALOG =====
//    if (showLogoutDialog) {
//        AlertDialog(
//            onDismissRequest = { showLogoutDialog = false },
//            confirmButton = {
//                TextButton(onClick = {
//                    viewModel.logout {}
//                    showLogoutDialog = false
//                }) {
//                    Text("Ya, Logout")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { showLogoutDialog = false }) {
//                    Text("Batal")
//                }
//            },
//            title = { Text("Konfirmasi Logout") },
//            text = { Text("Apakah Anda yakin ingin keluar dari akun ini?") }
//        )
//    }
//}
//
//@Composable
//private fun MenuSection(navController: NavHostController, isDriver: Boolean) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 24.dp)
//    ) {
//        Text(
//            text = "Akun",
//            style = MaterialTheme.typography.titleSmall.copy(
//                fontWeight = FontWeight.Bold,
//                color = Color.Gray
//            ),
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        MenuItemProfile("Pengaturan akun", R.drawable.ic_settings_black_24) {
//            navController.navigate("profile_detail")
//        }
//        MenuItemProfile("Pilihan bahasa", R.drawable.ic_settings_black_24) { }
//        MenuItemProfile("Notifikasi", R.drawable.ic_notifications_black_24) { }
//        MenuItemProfile("Password akun", R.drawable.ic_lock_black_24) {
//            navController.navigate("change_password")
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        Text(
//            text = "Info Lainnya",
//            style = MaterialTheme.typography.titleSmall.copy(
//                fontWeight = FontWeight.Bold,
//                color = Color.Gray
//            ),
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        MenuItemProfile("Pusat bantuan", R.drawable.ic_help_black_24) {
//            navController.navigate("help_center")
//        }
//        MenuItemProfile("Ketentuan & privasi", R.drawable.ic_privacy_black_24) { }
//
//        if (isDriver) {
//            Spacer(Modifier.height(16.dp))
//            Text(
//                text = "Fitur Driver",
//                style = MaterialTheme.typography.titleSmall.copy(
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Gray
//                ),
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            MenuItemProfile("Riwayat Penjemputan", R.drawable.ic_history_order_black_24) {
//                navController.navigate("driver_history")
//            }
//            MenuItemProfile("Tarik Saldo", R.drawable.ic_account_balance_wallet_black_24) {
//                navController.navigate("withdraw_balance")
//            }
//        }
//
//        Spacer(Modifier.height(24.dp))
//    }
//}
//
//@Composable
//fun MenuItemProfile(title: String, iconRes: Int, onClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() }
//            .padding(vertical = 14.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            painter = painterResource(id = iconRes),
//            contentDescription = title,
//            tint = Color(0xFF1877F2),
//            modifier = Modifier.size(20.dp)
//        )
//        Spacer(Modifier.width(16.dp))
//        Text(
//            text = title,
//            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
//            modifier = Modifier.weight(1f)
//        )
//        Icon(
//            painter = painterResource(id = R.drawable.ic_arrow_right_black_24),
//            contentDescription = "Next",
//            tint = Color.Gray,
//            modifier = Modifier.size(18.dp)
//        )
//    }
//    HorizontalDivider(Modifier, DividerDefaults.Thickness, color = Color(0xFFE8EAF0))
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewBaseProfileScreenCustomer() {
//    val navController = rememberNavController()
//    MaterialTheme {
//        Surface {
//            BaseProfileScreen(navController = navController)
//        }
//    }
//}