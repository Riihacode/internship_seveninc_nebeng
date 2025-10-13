package com.example.nebeng.feature_auth.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
fun DeleteAccountScreen(
//    userId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onAccountDeleted: () -> Unit = {}
) {
    val context = LocalContext.current
    var confirm by remember { mutableStateOf(false) }
    val currentUser by viewModel.currentUser.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hapus Akun", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        if (currentUser == null) {
            Text("Tidak ada akun login saat ini", color = MaterialTheme.colorScheme.error)
            return@Column
        }

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
                    onClick = {
                        val userId = currentUser?.id ?: return@Button
                        viewModel.deleteAccount(
                            id = userId,
                            onSuccess = {
                                Toast.makeText(context, "Akun berhasil dihapus", Toast.LENGTH_SHORT).show()
                                viewModel.logout {
                                    onAccountDeleted()
                                }
                            },
                            onError = { msg ->
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Ya, Hapus")
                }
            }
        }
    }
}
