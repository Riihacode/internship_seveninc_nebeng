package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_07

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerRideMotorPaymentWaitingScreen(
    hours: Int = 0,
    minutes: Int = 59,
    seconds: Int = 50,
    onBack: () -> Unit = {},
    onCheckStatus: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // ================= BACKGROUND BLUE (STATIC) =================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(bottomStart = 42.dp, bottomEnd = 42.dp))
                .background(Color(0xFF0F3D82))
        )

        // ================= MAIN CONTENT (SCROLLABLE) =================
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // ---------- TOP APP BAR ----------
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
                    containerColor = Color.Transparent
                )
            )

            Spacer(Modifier.height(16.dp))

            // ---------- PAYMENT WAITING CARD ----------
            WaitingPaymentCard(hours, minutes, seconds)

            Spacer(Modifier.height(18.dp))

            // ---------- RIDE INFO CARD ----------
            RideInfoCard()
        }
    }
}

@Composable
private fun WaitingPaymentCard(h: Int, m: Int, s: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(20.dp)) {

            Text("Menunggu Pembayaran", fontWeight = FontWeight.SemiBold)

            Spacer(Modifier.height(16.dp))
            TimerBox(h, m, s)
            Spacer(Modifier.height(16.dp))

            Text("Nomor Virtual Account", fontWeight = FontWeight.Medium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "90888085581244679",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF0F3D82)
                )
            }

            Spacer(Modifier.height(12.dp))
            Divider(color = Color(0xFFE6E6E6), thickness = 1.dp)
            Spacer(Modifier.height(12.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total Pembayaran", fontWeight = FontWeight.Medium)
                Text(
                    "Rp120.000",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF0F3D82)
                )
            }
        }
    }
}

@Composable
private fun TimerBox(h: Int, m: Int, s: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
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
            .size(74.dp)
            .shadow(8.dp, RoundedCornerShape(22.dp))
            .background(Color(0xFF0F3D82), RoundedCornerShape(22.dp))
            .border(3.dp, Color.White, RoundedCornerShape(22.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            value.toString().padStart(2, '0'),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun TimeSeparator() {
    Column(
        modifier = Modifier.height(50.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.size(8.dp).background(Color(0xFFD3D8DE), CircleShape))
        Box(Modifier.size(8.dp).background(Color(0xFFD3D8DE), CircleShape))
    }
}

@Composable
private fun RideInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color(0xFFE1E3E6))
    ) {
        Column(Modifier.padding(18.dp)) {
            Text("04 Januari 2025 | 13.45 – 18.45", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(18.dp))
            RouteRow()
            Spacer(Modifier.height(18.dp))
            Divider(color = Color(0xFFE6E6E6), thickness = 1.dp)
            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
private fun PreviewPassengerRideMotorPaymentWaitingScreen() {
    PassengerRideMotorPaymentWaitingScreen()
}

