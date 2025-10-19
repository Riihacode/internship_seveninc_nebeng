package com.example.nebeng.feature_auth.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
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
//import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.nebeng.R

//@Composable
//fun SignupScreen(
//    viewModel: SignupViewModel,
//    onSignupSuccess: () -> Unit,
//    onBackToLogin: () -> Unit
//) {
//    var name by remember { mutableStateOf("") }
//    var username by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordConfirmation by remember { mutableStateOf("") }
//    var userType by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    Column(modifier = Modifier.padding(24.dp)) {
//        Text("Daftar Akun", style = MaterialTheme.typography.titleLarge)
//        Spacer(Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Name") }
//        )
//
//        OutlinedTextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Username") }
//        )
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") }
//        )
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") }
//        )
//
//        OutlinedTextField(
//            value = passwordConfirmation,
//            onValueChange = { passwordConfirmation = it },
//            label = { Text("Konfirmasi Password") }
//        )
//
//        OutlinedTextField(
//            value = userType,
//            onValueChange = { userType = it },
//            label = { Text("Role") }
//        )
//
//        if (errorMessage != null) {
//            Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        Button(onClick = {
//            if (password != passwordConfirmation) {
//                errorMessage = "Password tidak sama"
//            } else {
//                viewModel.signup(
//                    name,
//                    username,
//                    email,
//                    password,
//                    userType,
//                    onSuccess = {
//                        errorMessage = null
//                        onSignupSuccess()
//                    },
//                    onError = { msg -> errorMessage = msg}
//                )
//            }
//        }) {
//            Text("Daftart")
//        }
//
//        Spacer(Modifier.height(0.dp))
//        TextButton(onClick = onBackToLogin) {
//            Text("Sudah punya akun? Login di sini")
//        }
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignupScreen(
//    viewModel: SignupViewModel,
//    onSignupSuccess: () -> Unit,
//    onBackToLogin: () -> Unit
//) {
//    var name by remember { mutableStateOf("") }
//    var username by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordConfirmation by remember { mutableStateOf("") }
//    var userType by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }
//    var confirmPasswordVisible by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    val isFormValid = name.isNotBlank() && username.isNotBlank() &&
//            email.isNotBlank() && password.isNotBlank() && passwordConfirmation.isNotBlank()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Daftar", style = MaterialTheme.typography.titleMedium) },
//                navigationIcon = {
//                    IconButton(onClick = onBackToLogin) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(horizontal = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(Modifier.height(24.dp))
//
//            Text(
//                text = "Buat Akun Baru",
//                style = MaterialTheme.typography.titleLarge.copy(
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF1877F2)
//                )
//            )
//            Text(
//                text = "Masukkan data untuk membuat akun",
//                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
//            )
//
//            Spacer(Modifier.height(24.dp))
//
//            // Username
//            OutlinedTextField(
//                value = username,
//                onValueChange = { username = it },
//                modifier = Modifier.fillMaxWidth(),
//                label = { Text("Username") },
//                leadingIcon = {
//                    Icon(Icons.Default.Person, contentDescription = "Username")
//                },
//                placeholder = { Text("Masukkan username") },
//                singleLine = true,
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            Spacer(Modifier.height(16.dp))
//
//            // Email
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                modifier = Modifier.fillMaxWidth(),
//                label = { Text("Email") },
//                leadingIcon = {
//                    Icon(Icons.Default.Email, contentDescription = "Email")
//                },
//                placeholder = { Text("Masukkan email") },
//                singleLine = true,
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            Spacer(Modifier.height(16.dp))
//
//            // Password
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                modifier = Modifier.fillMaxWidth(),
//                label = { Text("Password") },
//                placeholder = { Text("Buat password") },
//                leadingIcon = {
//                    Icon(Icons.Default.Lock, contentDescription = "Password")
//                },
//                trailingIcon = {
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        val image = if (passwordVisible) Icons.Default.Visibility
//                        else Icons.Default.VisibilityOff
//                        Icon(image, contentDescription = "Toggle Password")
//                    }
//                },
//                visualTransformation = if (passwordVisible) VisualTransformation.None
//                else PasswordVisualTransformation(),
//                singleLine = true,
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            Spacer(Modifier.height(16.dp))
//
//            // Konfirmasi Password
//            OutlinedTextField(
//                value = passwordConfirmation,
//                onValueChange = { passwordConfirmation = it },
//                modifier = Modifier.fillMaxWidth(),
//                label = { Text("Konfirmasi Password") },
//                placeholder = { Text("Konfirmasi password") },
//                leadingIcon = {
//                    Icon(Icons.Default.Lock, contentDescription = "Konfirmasi Password")
//                },
//                trailingIcon = {
//                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
//                        val image = if (confirmPasswordVisible) Icons.Default.Visibility
//                        else Icons.Default.VisibilityOff
//                        Icon(image, contentDescription = "Toggle Confirm Password")
//                    }
//                },
//                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None
//                else PasswordVisualTransformation(),
//                singleLine = true,
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            Spacer(Modifier.height(8.dp))
//
//            if (errorMessage != null) {
//                Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
//                Spacer(Modifier.height(8.dp))
//            }
//
//            // Tombol Buat Akun
//            Button(
//                onClick = {
//                    if (password != passwordConfirmation) {
//                        errorMessage = "Password tidak sama"
//                    } else {
//                        viewModel.signup(
//                            name,
//                            username,
//                            email,
//                            password,
//                            userType,
//                            onSuccess = {
//                                errorMessage = null
//                                onSignupSuccess()
//                            },
//                            onError = { msg -> errorMessage = msg }
//                        )
//                    }
//                },
//                enabled = isFormValid,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(52.dp),
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = if (isFormValid) Color(0xFF1877F2)
//                    else Color(0xFFBDBDBD)
//                )
//            ) {
//                Text("Buat Akun", color = Color.White, fontWeight = FontWeight.SemiBold)
//            }
//
//            Spacer(Modifier.height(24.dp))
//
//            // Divider "Atau"
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Divider(modifier = Modifier.weight(1f))
//                Text("  Atau  ", color = Color.Gray)
//                Divider(modifier = Modifier.weight(1f))
//            }
//
//            Spacer(Modifier.height(24.dp))
//
//            // Social Login
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_google),
//                    contentDescription = "Google",
//                    modifier = Modifier.size(36.dp)
//                )
//                Spacer(Modifier.width(24.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.ic_apple),
//                    contentDescription = "Apple",
//                    modifier = Modifier.size(36.dp)
//                )
//                Spacer(Modifier.width(24.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.ic_facebook),
//                    contentDescription = "Facebook",
//                    modifier = Modifier.size(36.dp)
//                )
//            }
//
//            Spacer(Modifier.height(36.dp))
//
//            // Sudah punya akun?
//            Row {
//                Text("Sudah punya akun? ")
//                Text(
//                    text = "Masuk",
//                    color = Color(0xFF1877F2),
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.clickable(onClick = onBackToLogin)
//                )
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
    onSignupSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    val isFormValid = name.isNotBlank() &&
            username.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            passwordConfirmation.isNotBlank() &&
            userType.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = onBackToLogin) {
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
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            Text(
                text = "Buat Akun Baru",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1877F2)
                )
            )
            Text(
                text = "Masukkan data untuk membuat akun",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )

            Spacer(Modifier.height(24.dp))

            // Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Name") },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Name")
                },
                placeholder = { Text("Masukkan nama lengkap") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Username") },
                leadingIcon = {
                    Icon(Icons.Default.PersonOutline, contentDescription = "Username")
                },
                placeholder = { Text("Masukkan username") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email")
                },
                placeholder = { Text("Masukkan email") },
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
                placeholder = { Text("Buat password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password")
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle Password"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Konfirmasi Password
            OutlinedTextField(
                value = passwordConfirmation,
                onValueChange = { passwordConfirmation = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Konfirmasi Password") },
                placeholder = { Text("Konfirmasi password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Konfirmasi Password")
                },
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            if (confirmPasswordVisible) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle Confirm Password"
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            // User Type (sementara manual input)
            OutlinedTextField(
                value = userType,
                onValueChange = { userType = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("User Type") },
                leadingIcon = {
                    Icon(Icons.Default.Badge, contentDescription = "User Type")
                },
                placeholder = { Text("Masukkan jenis akun (sementara)") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(8.dp))

            if (errorMessage != null) {
                Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(8.dp))
            }

            // Tombol Buat Akun
            Button(
                onClick = {
                    if (password != passwordConfirmation) {
                        errorMessage = "Password tidak sama"
                    } else {
                        viewModel.signup(
                            name,
                            username,
                            email,
                            password,
                            userType,
                            onSuccess = {
                                errorMessage = null
                                onSignupSuccess()
                            },
                            onError = { msg -> errorMessage = msg }
                        )
                    }
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
                Text("Buat Akun", color = Color.White, fontWeight = FontWeight.SemiBold)
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

            // Social Login
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

            // Sudah punya akun?
            Row {
                Text("Sudah punya akun? ")
                Text(
                    text = "Masuk",
                    color = Color(0xFF1877F2),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onBackToLogin)
                )
            }
        }
    }
}

