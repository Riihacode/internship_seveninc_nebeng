package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_09.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCancelledBottomSheet(
    onDetailClick: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ─── Drag Indicator ───────────────────────────
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(5.dp)
                    .background(Color(0xFFDDDDDD), RoundedCornerShape(50))
            )

            Spacer(Modifier.height(26.dp))

            // ─── Judul ────────────────────────────────────
            Text(
                text = "Pesananmu dicancel oleh driver!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                lineHeight = 30.sp
            )

            Spacer(Modifier.height(32.dp))

            // ─── Icon Placeholder PNG ─────────────────────
            Image(
                painter = painterResource(id = R.drawable.ic_close_24), // sementara
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )

            Spacer(Modifier.height(40.dp))

            // ─── Button Selengkapnya ──────────────────────
            Button(
                onClick = onDetailClick,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F3D82))
            ) {
                Text(
                    text = "Selengkapnya",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(26.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewOrderCancelledBottomSheet() {
    var showSheet by remember { mutableStateOf(true) }

    MaterialTheme {   // penting untuk styling Material 3
        Box(modifier = Modifier.fillMaxSize()) {

            // Background dummy (misal halaman di belakang bottom sheet)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF2F4F7)),
                contentAlignment = Alignment.Center
            ) {
                Text("Halaman Sebelumnya", color = Color.Gray)
            }

            if (showSheet) {
                OrderCancelledBottomSheet(
                    onDetailClick = {},
                    onDismiss = { showSheet = false }
                )
            }
        }
    }
}
