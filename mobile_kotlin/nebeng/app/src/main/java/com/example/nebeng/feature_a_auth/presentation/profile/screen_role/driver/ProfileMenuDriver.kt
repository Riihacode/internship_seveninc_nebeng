package com.example.nebeng.feature_a_auth.presentation.profile.screen_role.driver

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nebeng.R

@Composable
fun ProfileMenuDriver(navController: NavHostController) {
    val items = listOf(
        "Pengaturan" to { navController.navigate("profile_detail") },
        "PIN" to { },
        "Dokumen" to { navController.navigate("verify_ktp") },
        "Status akun" to { },
        "Bantuan" to { navController.navigate("help_center") }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .shadow(1.dp, RoundedCornerShape(12.dp))
    ) {
        items.forEachIndexed { index, (label, action) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { action() }
                    .padding(vertical = 16.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right_black_24),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(16.dp))
                Text(label, Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right_black_24),
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
            if (index != items.lastIndex) Divider()
        }
    }
}
