package com.example.nebeng.feature_a_authentication.presentation.profile.screen_base

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.nebeng.feature_a_authentication.presentation.AuthenticationViewModel

@Composable
fun ProfileDetailScreen(
//    viewModel: ProfileViewModel = hiltViewModel(),
//    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
    modifier: Modifier = Modifier
) {
//    val state by viewModel.uiState.collectAsState()
    // Pakai AuthenticationViewModel
    val viewModel: AuthenticationViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    val user = state.auth

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
//            state.isLoading -> CircularProgressIndicator()
//            state.errorMessage != null -> Text(state.errorMessage!!)
//            state.currentUser != null -> {
//                val user = state.currentUser!!
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text("Detail Profil", style = MaterialTheme.typography.titleLarge)
//                    Spacer(Modifier.height(16.dp))
//                    Text("Username: ${user.username}", fontWeight = FontWeight.Medium)
//                    Text("Password: ${user.password}")
//                    Text("User Type: ${user.user_type}")
//                }
//            }
            state.isLoading -> CircularProgressIndicator()

            state.errorMessage != null -> Text(state.errorMessage!!)

            user != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Detail Profil", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(16.dp))
                    Text("Nama        : ${user.name}", fontWeight = FontWeight.Medium)
                    Text("Username    : ${user.username}")
                    Text("Email       : ${user.email}")
                    Text("User Type   : ${user.userType}")
                    Spacer(Modifier.height(16.dp))
                    Text("Token (debug): ${user.token.take(15)}...", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}
