package com.example.nebeng.feature_a_authentication.presentation.profile.support_for_present.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_auth.domain.model.Auth
import androidx.compose.foundation.lazy.items
import com.example.nebeng.feature_a_authentication.presentation.AuthenticationViewModel

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
//    viewModel: ProfileViewModel = hiltViewModel()
) {
//    val state by viewModel.users.collectAsState()
//    val uiState by viewModel.uiState.collectAsState()
//    val userResult = uiState.users
//    val viewModel: AuthenticationViewModel = hiltViewModel()
//    val state = viewModel.uiState.collectAsState().value
//
//    // load once when first shown
//    LaunchedEffect(Unit) { viewModel.loadUsers() }
//
//    when (userResult) {
//        is Result.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//
//        is Result.Error -> Text(
//            text = userResult.message ?: "Gagal memuat data",
//            color = MaterialTheme.colorScheme.error,
//            modifier = Modifier.padding(24.dp)
//        )
//
//        is Result.Success -> {
////            val users = userResult.data
//            val users: List<Auth> = userResult.data ?: emptyList()
//            LazyColumn(
//                modifier = modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(users) { user ->
//                    Card(
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = CardDefaults.cardColors(
//                            containerColor = MaterialTheme.colorScheme.surfaceVariant
//                        )
//                    ) {
//                        Column(Modifier.padding(16.dp)) {
//                            Text(
//                                "Username: ${user.username}",
//                                style = MaterialTheme.typography.titleMedium
//                            )
//                            Text("Password: ${user.password}")
//                            Text("User Type: ${user.user_type}")
//                        }
//                    }
//                }
//            }
//        }
//
//        else -> {}
//    }
}