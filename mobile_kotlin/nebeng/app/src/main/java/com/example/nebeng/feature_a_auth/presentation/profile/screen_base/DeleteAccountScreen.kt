package com.example.nebeng.feature_a_auth.presentation.profile.screen_base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nebeng.feature_a_auth.domain.model.Auth
import com.example.nebeng.feature_a_auth.presentation.profile.ProfileUiState

@Composable
fun DeleteAccountScreen(
//    userId: Int,
    modifier: Modifier = Modifier,
//    viewModel: ProfileViewModel = hiltViewModel(),
    state: ProfileUiState,
    onDeleteAccount: (Int) -> Unit,
    onCancel: () -> Unit
) {
//    val context = LocalContext.current
//    var confirm by remember { mutableStateOf(false) }
////    val currentUser by viewModel.currentUser.collectAsState()
//    val uiState by viewModel.uiState.collectAsState()
//
//    val currentUser = uiState.currentUser

    val user = state.currentUser ?: return

    var confirm by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hapus Akun", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

//        if (currentUser == null) {
//            Text("Tidak ada akun login saat ini", color = MaterialTheme.colorScheme.error)
//            return@Column
//        }

        if (!confirm) {
            Button(
                onClick = { confirm = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Hapus Akun")
            }
        } else {
            Text("Apakah Anda yakin ingin menghapus akun ini?", color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { confirm = false }) {
                    Text("Batal")
                }

                Button(
//                    onClick = {
//                        val userId = currentUser.id ?: return@Button
//                        viewModel.deleteAccount(
//                            id = userId,
//                            onSuccess = {
//                                Toast.makeText(context, "Akun berhasil dihapus", Toast.LENGTH_SHORT).show()
//                                viewModel.logout {
//                                    onAccountDeleted()
//                                }
//                            },
//                            onError = { msg ->
//                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
//                            }
//                        )
//                    },
                    onClick = { onDeleteAccount(user.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
//                    Text("Ya, Hapus")
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    } else {
                        Text("Ya, Hapus")
                    }
                }

                if (state.errorMessage != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = state.errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (state.isDeleted) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "✅ Akun berhasil dihapus",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeleteAccountScreenPreview() {
    val fakeUser = Auth(
        id = 1,
        name = "Customer Nisa",
        username = "customer1",
        password = "",
        email = "nisa@example.com",
        user_type = "customer"
    )

    val fakeState = ProfileUiState(
        currentUser = fakeUser,
        isLoading = false,
        isDeleted = false,
        errorMessage = null
    )

    MaterialTheme {
        DeleteAccountScreen(
            state = fakeState,
            onDeleteAccount = { println("Pretend deleting user id: $it") },
            onCancel = { println("Cancelled") }
        )
    }
}
