package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_06

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerRideMotorPaymentStatusScreen(
    onBack: () -> Unit = {},
    onCheckStatus: () -> Unit = {}
) {
    // ------- Dummy Timer 00:59:50 -------
    var hours by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(59) }
    var seconds by remember { mutableStateOf(50) }

    LaunchedEffect(true) {
        while (hours + minutes + seconds > 0) {
            delay(1000)
            if (seconds > 0) seconds--
            else {
                seconds = 59
                if (minutes > 0) minutes--
                else {
                    minutes = 59
                    if (hours > 0) hours--
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ================= TOP BAR =================
        TopAppBar(
            title = {
                Text(
                    "Selesaikan Pembayaran",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF0F3D82)
            )
        )

        Spacer(Modifier.height(22.dp))

        // ========== BOX TIMER ==========
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Sisa waktu pembayaran :", fontSize = 14.sp, color = Color.Black)

            Spacer(Modifier.height(14.dp))

            TimerBox(hours, minutes, seconds)

            Spacer(Modifier.height(14.dp))

            Text("Batas Akhir Pembayaran", fontWeight = FontWeight.Medium, color = Color.Gray)
            Text(
                "Kamis, 2 September 2024 14:50",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        Spacer(Modifier.height(26.dp))

        // ========== CARD VIRTUAL ACCOUNT ==========
        VaPaymentCard()

        Spacer(Modifier.height(22.dp))

        // ========== CARD DETAIL RUTE ==========
        RideInfoCard()

        Spacer(Modifier.weight(1f))

        // ========== BUTTON ==========
        Button(
            onClick = onCheckStatus,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF0F3D82))
        ) {
            Text("Cek Status Pembayaran", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun TimeSeparator() {
    Column(
        modifier = Modifier.height(52.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(Color(0xFFD3D8DE), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(Color(0xFFD3D8DE), CircleShape)
        )
    }
}

@Composable
private fun TimerBox(h: Int, m: Int, s: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TimeBox(h)
        TimeSeparator()
        TimeBox(m)
        TimeSeparator()
        TimeBox(s)
    }
}

@Composable
private fun TimeBox(value: Int) {
    Box(
        modifier = Modifier
            .size(68.dp)
            .shadow(8.dp, RoundedCornerShape(22.dp)) // efek bayangan seluruh sisi
            .background(Color(0xFF0F3D82), RoundedCornerShape(22.dp)) // warna biru
            .border(3.dp, Color.White, RoundedCornerShape(22.dp)),   // border putih tebal
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value.toString().padStart(2, '0'),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun VaPaymentCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, Color(0xFFE1E3E6))
    ) {
        Column(Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("BRI Virtual Account", fontWeight = FontWeight.Medium)
                Icon(
                    painter = painterResource(id = R.drawable.qris),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(Modifier.height(14.dp))

            Text(
                text = "9088 8085 5812 6739 33",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center        // ⬅️ kunci
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF0F3D82))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qris),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text("Salin")
            }
        }
    }
}

@Composable
private fun RideInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color(0xFFE1E3E6)),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            Text("04 Januari 2025 | 13.45 - 18.45", fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(18.dp))

            RouteRow()

            Spacer(Modifier.height(18.dp))

            Divider(color = Color(0xFFE8E8E8), thickness = 1.dp)

            Spacer(Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("No Pemesanan:", fontWeight = FontWeight.Medium)
                Text("FR-2345678997543234", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun RouteRow() {
    Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp)
                .width(38.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(16.dp).background(Color(0xFF2ECC71), RoundedCornerShape(50))
            )
            Box(
                modifier = Modifier.weight(1f).width(2.dp).background(Color(0xFFD0D4D8))
            )
            Box(
                modifier = Modifier.size(16.dp).background(Color(0xFFFF6E42), RoundedCornerShape(50))
            )
        }

        Spacer(Modifier.width(10.dp))

        Column {
            Text("Yogyakarta", fontWeight = FontWeight.Bold)
            Text("Patehan, Kecamatan Kraton, Kota Yogyakarta, Daerah Istimewa", fontSize = 13.sp, color = Color.Gray)
            Spacer(Modifier.height(10.dp))
            Text("Purwokerto", fontWeight = FontWeight.Bold)
            Text("Alun-alun Purwokerto", fontSize = 13.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewPassengerRideMotorPaymentStatusScreen() {
    PassengerRideMotorPaymentStatusScreen()
}