package com.example.nebeng.feature_a_authentication.presentation.profile.screen_role.driver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.nebeng.R
import com.example.nebeng.core.utils.UserType
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import com.example.nebeng.feature_a_authentication.presentation.AuthenticationUiState
import com.example.nebeng.feature_a_authentication.presentation.AuthenticationViewModel
import kotlinx.coroutines.flow.MutableStateFlow

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileDriverScreenUi(
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
//                        .padding(96.dp),
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
//                    MenuSection(navController = navController, isDriver = true)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDriverScreenUi(
    navController: NavHostController,
//    viewModel: ProfileViewModel = hiltViewModel()
) {
    // 🔥 ViewModel diambil internal, bukan parameter
    val viewModel: AuthenticationViewModel = hiltViewModel()

    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var showLogoutDialog by remember { mutableStateOf(false) }
//    val currentUser = uiState.currentUser
    val user = state.auth  // 🔥 menggantikan currentUser

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Akun") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            // Profile card
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F9FC))
            ) {
                Row(
                    Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = user?.avatarUrl ?: R.drawable.ic_profile_black_24,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(user?.name ?: "Kamado Tanjirō", fontWeight = FontWeight.Bold)
//                        Text(currentUser?.phone ?: "(+62) 81349182987", color = Color.Gray)
                        Text(user?.email ?: "kamado.tanjiro@gmail.com", color = Color.Gray)
                        Text("Yogyakarta", color = Color.Gray)
                    }
                    IconButton(onClick = { navController.navigate("edit_profile") }) {
                        Icon(painterResource(R.drawable.ic_edit_black_24), contentDescription = "Edit")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            ProfileMenuDriver(navController)

            Spacer(Modifier.height(20.dp))
            OutlinedButton(
                onClick = { showLogoutDialog = true },
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                Icon(painterResource(R.drawable.ic_logout_red_24), contentDescription = null, tint = Color.Red)
                Spacer(Modifier.width(8.dp))
                Text("Keluar", color = Color.Red)
            }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.logout()
                    showLogoutDialog = false
                }) { Text("Ya, Keluar") }
            },
            dismissButton = { TextButton(onClick = { showLogoutDialog = false }) { Text("Batal") } },
            title = { Text("Konfirmasi Keluar") },
            text = { Text("Yakin ingin keluar dari akun?") }
        )
    }
}

class FakeViewModelOwner(
    private val fakeState: AuthenticationUiState
) : ViewModelStoreOwner {
    override val viewModelStore = ViewModelStore()
}

@Composable
fun AuthenticationViewModel.forPreview(fake: AuthenticationUiState) {
    val mutableState = remember { MutableStateFlow(fake) }
    val field = AuthenticationViewModel::class.java.getDeclaredField("_uiState")
    field.isAccessible = true
    field.set(this, mutableState)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileDriverScreenUi() {
    val navController = rememberNavController()

    // Dummy user
    val fakeUser = AuthenticationItem(
        userId = 1,
        name = "Driver Rengoku",
        username = "driver1",
        email = "driver1@gmail.com",
        userType = UserType.DRIVER,
        avatarUrl = null,
        token = "dummy"
    )

    // Kita pakai CompositionLocal untuk memalsukan ViewModel
    CompositionLocalProvider(
        LocalViewModelStoreOwner provides FakeViewModelOwner(
            fakeState = AuthenticationUiState(auth = fakeUser)
        )
    ) {
        MaterialTheme {
            ProfileDriverScreenUi(navController = navController)
        }
    }
}


