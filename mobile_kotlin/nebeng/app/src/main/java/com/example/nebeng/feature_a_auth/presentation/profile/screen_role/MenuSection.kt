package com.example.nebeng.feature_a_auth.presentation.profile.screen_role

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.navigation.NavController
import com.example.nebeng.R

@Composable
fun MenuSection(
    navController: NavController,
    isDriver: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Admin",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        MenuItemProfile("Pengaturan akun", R.drawable.ic_settings_black_24) {
            navController.navigate("profile_detail")
        }

        MenuItemProfile("Pilihan bahasa", R.drawable.ic_settings_black_24) {  }
        MenuItemProfile("Notifikasi", R.drawable.ic_notifications_black_24) {  }
        MenuItemProfile("Password akun", R.drawable.ic_lock_black_24) {
            navController.navigate("change_password")
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Info Lainnya",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        MenuItemProfile("Pusat bantuan", R.drawable.ic_help_black_24) {
            navController.navigate("help_center")
        }
        MenuItemProfile("Ketentuan & Privasi", R.drawable.ic_privacy_black_24) {  }

        if (isDriver) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Fitur Driver",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            MenuItemProfile("Riwayat Penjemputan", R.drawable.ic_history_order_black_24) {
                navController.navigate("withdraw_balance")
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun MenuItemProfile(
    title: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            tint = Color(0xFF1877F2),
            modifier = Modifier.size(28.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right_black_24),
            contentDescription = "Next",
            tint = Color.Gray,
            modifier = Modifier.size(18.dp)
        )
    }
    HorizontalDivider(Modifier, DividerDefaults.Thickness, color = Color(0xFFE8EAF0))
}