package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_02

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.nebeng.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerRideMotorScheduleScreen(
    onBack: () -> Unit = {},
    onDetailClick: (Int) -> Unit = {}   // index order sementara
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD))
    ) {

        // ==================== TOP BAR ====================
        TopAppBar(
            title = {
                Text(
                    text = "Yogyakarta → Purwokerto",
                    color = Color(0xFF0F3D82),
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = null,
                        tint = Color(0xFF0F3D82)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(Color.White)
        )

        Spacer(Modifier.height(6.dp))

        // =================== DAFTAR CARD ==================
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(5) { index ->  // dummy 5 order
                OrderCard(
                    index = index,
                    onDetailClick = onDetailClick
                )
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun OrderCard(
    index: Int,
    onDetailClick: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 18.dp)) {

            // === Tanggal & Jam ===
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Minggu, 25-07-2024", fontWeight = FontWeight.SemiBold)
                Text("09.00 - 13.00", fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(16.dp))

            // === Rute (Awal — Tujuan) ===
            RouteRow(
                startTitle = "Yogyakarta · Pos 1",
                startDetail = "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
                endTitle = "Purwokerto · Pos 2",
                endDetail = "Jl. Prof. Dr. Suharso No.8, Purwokerto Lor, Kec. Purwokerto Timur"
            )

            Spacer(Modifier.height(12.dp))

            // === Total Harga + Selengkapnya ===
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Total Biaya", fontWeight = FontWeight.Medium)
                Text(
                    "Rp. 120.000",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    color = Color(0xFF0F3D82)
                )
            }

            Spacer(Modifier.height(12.dp))

            Divider(color = Color(0xFFE8E8E8), thickness = 1.dp)

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Selengkapnya",
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0F3D82),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(index) }
                    .padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun RouteRow(
    startTitle: String,
    startDetail: String,
    endTitle: String,
    endDetail: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 6.dp, bottom = 6.dp)
                .width(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // DOT HIJAU
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color(0xFF2ECC71), shape = CircleShape)
            )

            // GARIS TENGAH
            Box(
                modifier = Modifier
                    .weight(1f)
                    .width(2.dp)
                    .background(Color(0xFFC8CCD0))
            )

            // DOT ORANYE
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color(0xFFFF6E42), shape = CircleShape)
            )
        }

        Spacer(Modifier.width(14.dp))

        Column(Modifier.weight(1f)) {
            Text(startTitle, fontWeight = FontWeight.Bold)
            Text(startDetail, color = Color.Gray, fontSize = 13.sp)
            Spacer(Modifier.height(10.dp))
            Text(endTitle, fontWeight = FontWeight.Bold)
            Text(endDetail, color = Color.Gray, fontSize = 13.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewPassengerRideMotorResultScreen() {
    PassengerRideMotorScheduleScreen()
}

