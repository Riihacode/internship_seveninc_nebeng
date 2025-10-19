package com.example.nebeng.feature_auth.presentation.login

//import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.nebeng.R

//
//@Composable
//fun LoginScreen(
//    viewModel: LoginViewModel,
//    onLoginSuccess: () -> Unit,
//    onNavigateToSignup: () -> Unit
//){
//    val username = remember { mutableStateOf("") }
//    val password = remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.padding(24.dp)){
//        Text("Login", style = MaterialTheme.typography.titleLarge)
//        Spacer(Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = username.value,
//            onValueChange = { username.value = it },
//            label = { Text("Username") }
//        )
//
//        OutlinedTextField(
//            value = password.value,
//            onValueChange = { password.value = it },
//            label = { Text("Password") }
//        )
//
//        Spacer(Modifier.height(16.dp))
//        Button(onClick = {
//            viewModel.login(username.value, password.value, onLoginSuccess) }) {
//            Text("Login")
//        }
//
//        Spacer(Modifier.height(16.dp))
//        TextButton(onClick = onNavigateToSignup) {
//            Text("Belum punya akun? Daftar di sini")
//        }
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isFormValid = username.isNotBlank() && password.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Masuk", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                text = "nebeng",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0xFF1877F2),
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(36.dp))

            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email / Username") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Username"
                    )
                },
                placeholder = { Text("Masukkan email atau username") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                placeholder = { Text("Masukkan password") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
                },
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility
                    else Icons.Default.VisibilityOff

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = "Toggle Password Visibility")
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Lupa Password
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = { /* TODO */ }) {
                    Text("Lupa Password?", color = Color(0xFF1877F2))
                }
            }

            Spacer(Modifier.height(8.dp))

            // Tombol Masuk
            Button(
                onClick = {
                    viewModel.login(username, password, onLoginSuccess)
                },
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF1877F2)
                    else Color(0xFFBDBDBD)
                )
            ) {
                Text("Masuk", color = Color.White, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(24.dp))

            // Divider "Atau"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text("  Atau  ", color = Color.Gray)
                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(24.dp))

            // Social Buttons (dummy)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier.size(36.dp)
                )
                Spacer(Modifier.width(24.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_apple),
                    contentDescription = "Apple",
                    modifier = Modifier.size(36.dp)
                )
                Spacer(Modifier.width(24.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(Modifier.height(36.dp))

            // Daftar
            Row {
                Text("Belum punya akun? ")
                Text(
                    text = "Daftar",
                    color = Color(0xFF1877F2),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onNavigateToSignup)
                )
            }
        }
    }
}
