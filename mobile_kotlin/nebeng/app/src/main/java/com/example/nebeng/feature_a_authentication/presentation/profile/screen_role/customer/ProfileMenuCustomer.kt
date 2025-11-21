package com.example.nebeng.feature_a_authentication.presentation.profile.screen_role.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nebeng.R

@Composable
fun ProfileMenuCustomer(navController: NavHostController) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Akun", fontWeight = FontWeight.Bold, color = Color.Gray)
        MenuItem("🏆 Reward Point") { }
        MenuItem("👤 Edit Profile") { navController.navigate("edit_profile") }
        MenuItem("🧾 Riwayat Transaksi") { }
        MenuItem("🌐 Bahasa") { }
        MenuItem("🔒 Buat PIN") { }

        Spacer(Modifier.height(8.dp))
        Text("Lainnya", fontWeight = FontWeight.Bold, color = Color.Gray)
        MenuItem("🛡️ Keamanan") { }
        MenuItem("💬 Pusat Bantuan") { navController.navigate("help_center") }
    }
}

@Composable
private fun MenuItem(label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Icon(painter = painterResource(R.drawable.ic_arrow_right_black_24), contentDescription = null, tint = Color.Gray)
    }
    Divider(color = Color(0xFFE8EAF0))
}
