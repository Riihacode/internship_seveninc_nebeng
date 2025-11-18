package com.example.nebeng.feature_a_authentication.presentation.profile.screen_base

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.nebeng.core.utils.UserType
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import com.example.nebeng.feature_a_authentication.presentation.AuthenticationUiState
import com.example.nebeng.feature_auth.domain.model.Auth
import com.example.nebeng.feature_a_authentication.presentation.profile.ProfileUiState
import com.example.nebeng.feature_user.data.remote.model.request.UpdateUserRequest

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    state: AuthenticationUiState,
    onSave: (token: String, userId: Int, request: UpdateUserRequest) -> Unit,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        Log.d("NAV_DEBUG", "EditProfileScreen COMPOSED, user = ${state.auth}")
    }

//    val user = state.auth ?: return   // kalau user null → UI tidak crash
    val user = state.auth
    if (user == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    var name by remember { mutableStateOf(user.name) }
    var username by remember { mutableStateOf(user.username) }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
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
                val req = UpdateUserRequest(
                    password = password,
                    userType = user.userType.name.lowercase(),
                    name = name,
                    banned = false,
                    email = user.email ?: "",
                    username = username
                )

                onSave(user.token, user.userId, req)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text("Simpan Perubahan")
        }

        if (state.errorMessage != null) {
            Spacer(Modifier.height(6.dp))
            Text(state.errorMessage ?: "", color = MaterialTheme.colorScheme.error)
        }

        if (state.successMessage != null) {
            Spacer(Modifier.height(6.dp))
            Text(state.successMessage ?: "", color = MaterialTheme.colorScheme.primary)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditProfileScreen() {
    val fakeState = AuthenticationUiState(
        auth = AuthenticationItem(
            userId = 1,
            name = "Customer Nisa",
            username = "customer1",
            email = "nisa@example.com",
            userType = UserType.CUSTOMER,
            token = "dummy_token",
            banned = false
        ),
        isLoading = false
    )

    MaterialTheme {
        EditProfileScreen(
            state = fakeState,
            onSave = { token, userId, req ->
                println("TOKEN = $token")
                println("ID = $userId")
                println("REQ = $req")
            },
            onBack = {}
        )
    }
}
