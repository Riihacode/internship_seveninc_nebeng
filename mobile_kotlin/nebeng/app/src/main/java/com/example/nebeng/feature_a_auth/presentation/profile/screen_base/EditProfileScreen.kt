package com.example.nebeng.feature_a_auth.presentation.profile.screen_base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.feature_a_auth.domain.model.Auth
import com.example.nebeng.feature_a_auth.presentation.profile.ProfileUiState

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileUiState,
    onSave: (id:Int, name: String, username: String, password: String, userType: String) -> Unit
) {
    val user = state.currentUser ?: return

    var name by remember { mutableStateOf(user.name) }
    var username by remember { mutableStateOf(user.username) }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Edit Profil", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password Baru (Opsional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                onSave(user.id, name, username, password, user.user_type)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {

            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(28.dp)
                )
            } else {
                Text("Simpan Perubahan")
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

        if (state.isProfileUpdated) {
            Spacer(Modifier.height(8.dp))
            Text(
                text =  "✅ Profil berhasil diperbarui",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfileWithAppBarPreview() {
    val dummyUser = Auth(
        id = 1,
        name = "Customer Nisa",
        username = "customer1",
        password = "",
        email = "nisa@example.com",
        user_type = "customer"
    )

    val fakeState = ProfileUiState(
        currentUser = dummyUser,
        isLoading = false,
        errorMessage = null,
        isProfileUpdated = false
    )

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { androidx.compose.material3.Text("Edit Profil") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { /* no-op */ }) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { padding ->
        EditProfileScreen(
            modifier = Modifier.padding(padding),
            state = fakeState,
            onSave = { id, name, username, password, userType ->
                println("Saving: $name / $username")
            }
        )
    }
}