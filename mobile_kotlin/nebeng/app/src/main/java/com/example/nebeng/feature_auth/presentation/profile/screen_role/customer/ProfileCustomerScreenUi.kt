package com.example.nebeng.feature_auth.presentation.profile.screen_role.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.nebeng.R
import com.example.nebeng.feature_auth.presentation.profile.ProfileViewModel

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileCustomerScreenUi(
//    navController: NavController,
//    viewModel: ProfileViewModel = hiltViewModel()
//) {
//    val context = LocalContext.current
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    var showLogoutDialog by remember { mutableStateOf(false) }
//
//    val currentUser = uiState.currentUser
//
//    // React to logout / delete events
//
//    LaunchedEffect(uiState.isLoggedOut) {
//        if (uiState.isLoggedOut) {
//            val intent = Intent(context, AuthActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            context.startActivity(intent)
//            (context as? Activity)?.finish()
//        }
//    }
//
//    LaunchedEffect(uiState.isDeleted) {
//        if(uiState.isDeleted) {
//            val intent = Intent(context, AuthActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            context.startActivity(intent)
//            (context as? Activity)?.finish()
//        }
//    }
//
//    when {
//        uiState.isLoading               -> LoadingScreen()
//        uiState.errorMessage != null    -> ErrorScreen(uiState.errorMessage)
//        else    -> {
//            Scaffold(containerColor = Color(0xFFF8F9FD)) { padding ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .verticalScroll(rememberScrollState())
//                        .padding(bottom = 96.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    // Header
//                    Box(
//                        modifier =  Modifier
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
//                    // Profile Card
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
//                                IconButton(onClick = { navController.navigate("edit_profile") }) {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_edit_black_24),
//                                        contentDescription = "Edit",
//                                        tint = Color(0xFF1877F2)
//                                    )
//                                }
//                            }
//                        }
//                    }
//
//                    Spacer(Modifier.height(8.dp))
//                    MenuSection(navController = navController, isDriver = false)
//
//                    Column(
//                        modifier = Modifier
//                            .padding(horizontal = 24.dp, vertical = 16.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Button(onClick = { navController.navigate("user_list") }, modifier = Modifier.fillMaxWidth()) {
//                            Text("Daftar Pengguna")
//                        }
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
//                        ) { Text("Logout", color = Color.Black) }
//                    }
//                }
//            }
//        }
//    }
//
//    if (showLogoutDialog) {
////        BasicAlertDialog(
//        AlertDialog(
//            onDismissRequest = { showLogoutDialog = false },
//            confirmButton = {
//                TextButton(onClick = {
//                    viewModel.logout {}
//                    showLogoutDialog = false
//                }) { Text("Ya, Logout") }
//            },
//            dismissButton = {
//                TextButton(onClick = { showLogoutDialog = false } ) { Text("Batal") }
//            },
//            title = { Text("Konfirmasi Logout") },
//            text = { Text("Apakah Anda yakin ingin keluar dari akun ini?") }
//        )
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileCustomerScreenUi(
//    navController: NavHostController,
//    viewModel: ProfileViewModel = hiltViewModel()
//) {
//    val context = LocalContext.current
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    var showLogoutDialog by remember { mutableStateOf(false) }
//    val currentUser = uiState.currentUser
//
//    Scaffold(
//        containerColor = Color.White
//    ) { padding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            // Header biru melengkung
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(220.dp)
//                    .clip(RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp))
//                    .background(Color(0xFF0D47A1)),
//                contentAlignment = Alignment.Center
//            ) {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text(
//                        text = "Profile",
//                        color = Color.White,
//                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//                    Spacer(Modifier.height(12.dp))
//                    Box {
//                        AsyncImage(
//                            model = currentUser?.avatarUrl ?: R.drawable.ic_profile_black_24,
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(90.dp)
//                                .clip(CircleShape)
//                                .border(2.dp, Color.White, CircleShape)
//                        )
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_edit_black_24),
//                            contentDescription = null,
//                            tint = Color.White,
//                            modifier = Modifier
//                                .align(Alignment.BottomEnd)
//                                .background(Color(0xFF1877F2), CircleShape)
//                                .padding(6.dp)
//                                .size(18.dp)
//                                .clickable { navController.navigate("edit_profile") }
//                        )
//                    }
//                    Spacer(Modifier.height(6.dp))
//                    Text(currentUser?.name ?: "Nasywa", color = Color.White)
//                    Text(currentUser?.email ?: "nasywa3@gmail.com", color = Color.White.copy(alpha = 0.9f))
//                }
//            }
//
//            // Card menu di bawah header
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.TopCenter)
//                    .padding(top = 190.dp)
//                    .clip(RoundedCornerShape(24.dp))
//                    .background(Color.White)
//                    .shadow(4.dp, RoundedCornerShape(24.dp))
//                    .padding(horizontal = 20.dp, vertical = 16.dp)
//                    .verticalScroll(rememberScrollState())
//            ) {
//                ProfileMenuCustomer(navController)
//                Spacer(Modifier.height(12.dp))
//                TextButton(onClick = { showLogoutDialog = true }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
//                    Icon(painterResource(R.drawable.ic_logout_red_24), contentDescription = null, tint = Color.Red)
//                    Spacer(Modifier.width(6.dp))
//                    Text("Log out", color = Color.Red)
//                }
//            }
//        }
//    }
//
//    if (showLogoutDialog) {
//        AlertDialog(
//            onDismissRequest = { showLogoutDialog = false },
//            confirmButton = {
//                TextButton(onClick = {
//                    viewModel.logout {}
//                    showLogoutDialog = false
//                }) { Text("Ya, Logout") }
//            },
//            dismissButton = { TextButton(onClick = { showLogoutDialog = false }) { Text("Batal") } },
//            title = { Text("Konfirmasi Logout") },
//            text = { Text("Apakah Anda yakin ingin keluar?") }
//        )
//    }
//}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileCustomerScreenUi(
//    navController: NavHostController,
//    viewModel: ProfileViewModel = hiltViewModel()
//) {
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    val user = uiState.currentUser
//    var showLogout by remember { mutableStateOf(false) }
//
//    Scaffold(
//        containerColor = Color(0xFFF6F8FA)
//    ) { padding ->
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            // ===== HEADER =====
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp)
//                    .verticalScroll(rememberScrollState())
//                    .background(
//                        color = Color(0xFF0D47A1),
//                        shape = RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp)
//                    )
//                    .padding(bottom = 48.dp),
//                contentAlignment = Alignment.TopStart
//            ) {
//                Text(
//                    text = "Profile",
//                    color = Color.White,
//                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
//                    modifier = Modifier.padding(top = 16.dp, start = 20.dp)
//                )
//            }
//
//            // ===== CONTENT =====
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .verticalScroll(rememberScrollState())
//                    .padding(horizontal = 20.dp)
//                    .padding(top = 60.dp, bottom = 96.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                // Avatar card
//                Box(contentAlignment = Alignment.BottomEnd) {
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_profile_black_24),
//                        contentDescription = "Avatar",
//                        modifier = Modifier
//                            .size(90.dp)
//                            .clip(CircleShape)
//                            .background(Color.White)
//                            .border(3.dp, Color.White, CircleShape)
//                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_edit_black_24),
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier
//                            .offset(x = (-6).dp, y = (-6).dp)
//                            .background(Color(0xFF1877F2), CircleShape)
//                            .padding(6.dp)
//                            .size(16.dp)
//                            .clickable { navController.navigate("edit_profile") }
//                    )
//                }
//
//                Spacer(Modifier.height(8.dp))
//                Text(user?.name ?: "Customer Nisa", fontWeight = FontWeight.Bold, color = Color.White)
//                Text(user?.email ?: "customer1@gmail.com", color = Color.White.copy(alpha = 0.9f))
//
//                Spacer(Modifier.height(24.dp))
//
//                // ===== MENU CARD =====
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = CardDefaults.cardColors(containerColor = Color.White),
//                    shape = RoundedCornerShape(20.dp),
//                    elevation = CardDefaults.cardElevation(6.dp)
//                ) {
//                    Column(Modifier.padding(vertical = 8.dp)) {
//                        SectionTitle("Akun")
//
//                        MenuItem(icon = R.drawable.ic_edit_black_24, label = "Edit Profile") {
//                            navController.navigate("edit_profile")
//                        }
//                        MenuItem(icon = R.drawable.ic_receipt_black_24, label = "Riwayat Transaksi") {}
//                        MenuItem(icon = R.drawable.ic_language_black_24, label = "Bahasa") {}
//                        MenuItem(icon = R.drawable.ic_lock_black_24, label = "Buat PIN") {}
//
//                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
//                        SectionTitle("Lainnya")
//                        MenuItem(icon = R.drawable.ic_shield_black_24, label = "Keamanan") {}
//                        MenuItem(icon = R.drawable.ic_help_black_24, label = "Pusat Bantuan") {}
//                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
//                        MenuItemLogout(
//                            icon = R.drawable.ic_logout_red_24,
//                            label = "Log out",
//                            onClick = { showLogout = true }
//                        )
//                    }
//                }
//
//            }
//        }
//    }
//
//    if (showLogout) {
//        AlertDialog(
//            onDismissRequest = { showLogout = false },
//            confirmButton = {
//                TextButton(onClick = {
//                    viewModel.logout {}
//                    showLogout = false
//                }) { Text("Ya, Logout") }
//            },
//            dismissButton = { TextButton(onClick = { showLogout = false }) { Text("Batal") } },
//            title = { Text("Konfirmasi Logout") },
//            text = { Text("Apakah Anda yakin ingin keluar dari akun ini?") }
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCustomerScreenUi(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val user = uiState.currentUser
    var showLogout by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color(0xFFF6F8FA)
    ) { padding ->

        // Semua bagian bisa discroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ===== HEADER =====
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(
                        color = Color(0xFF0D47A1),
                        shape = RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp)
                    ),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Profile",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(Modifier.height(20.dp))

                    Box(contentAlignment = Alignment.BottomEnd) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile_black_24),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .border(3.dp, Color.White, CircleShape)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit_black_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .offset(x = (-6).dp, y = (-6).dp)
                                .background(Color(0xFF1877F2), CircleShape)
                                .padding(6.dp)
                                .size(16.dp)
                                .clickable { navController.navigate("edit_profile") }
                        )
                    }

                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = user?.name ?: "Customer Nisa",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = user?.email ?: "customer1@gmail.com",
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            // ===== CARD MENU (di atas bagian bawah header, tampak timbul) =====
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-100).dp), // supaya card sedikit overlap di bawah curve header
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(Modifier.padding(vertical = 8.dp)) {
                    SectionTitle("Akun")

                    MenuItem(icon = R.drawable.ic_star, label = "Reward Point") {}
                    MenuItem(icon = R.drawable.ic_edit_black_24, label = "Edit Profile") {
                        navController.navigate("edit_profile")
                    }
                    MenuItem(icon = R.drawable.ic_receipt_black_24, label = "Riwayat Transaksi") {}
                    MenuItem(icon = R.drawable.ic_language_black_24, label = "Bahasa") {}
                    MenuItem(icon = R.drawable.ic_lock_black_24, label = "Buat PIN") {}

                    Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
                    SectionTitle("Lainnya")
                    MenuItem(icon = R.drawable.ic_shield_black_24, label = "Keamanan") {}
                    MenuItem(icon = R.drawable.ic_help_black_24, label = "Pusat Bantuan") {}
                    Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
                    MenuItemLogout(
                        icon = R.drawable.ic_logout_red_24,
                        label = "Log out",
                        onClick = { showLogout = true }
                    )
                }
            }

            Spacer(Modifier.height(96.dp))
        }
    }

    // ===== DIALOG LOGOUT =====
    if (showLogout) {
        AlertDialog(
            onDismissRequest = { showLogout = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.logout {}
                    showLogout = false
                }) { Text("Ya, Logout") }
            },
            dismissButton = { TextButton(onClick = { showLogout = false }) { Text("Batal") } },
            title = { Text("Konfirmasi Logout") },
            text = { Text("Apakah Anda yakin ingin keluar dari akun ini?") }
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(start = 20.dp, top = 12.dp, bottom = 4.dp)
    )
}

@Composable
private fun MenuItem(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Icon(painter = painterResource(icon), contentDescription = null, tint = Color(0xFF1877F2))
//        Spacer(Modifier.width(16.dp))
//        Text(label, Modifier.weight(1f))
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(22.dp)
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(1f),
            color = Color.Black
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_right_black_24),
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Composable
private fun MenuItemLogout(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Icon(painter = painterResource(icon), contentDescription = null, tint = Color.Red)
        Spacer(Modifier.width(16.dp))
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = Color.Red,
            fontWeight = FontWeight.Medium
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_right_black_24),
            contentDescription = null,
            tint = Color.Red.copy(alpha = 0.7f)
        )
    }
}