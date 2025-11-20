package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_08

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerRideMotorPaymentSuccessScreen(
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD))
    ) {

        // 🔵 TOP APP BAR
        // 🔵 TOP APP BAR
        TopAppBar(
            title = {
                Text(
                    "Pembayaran",
                    color = Color.White,   // warna teks judul putih
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = null,
                        tint = Color.White   // icon juga putih
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF0F3D82)   // background TopAppBar biru
            )
        )

        Spacer(Modifier.height(18.dp))

        // 🎉 PAYMENT SUCCESS ICON + TITLE
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star), // ikon ilustrasi
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(78.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                "Pembayaran Berhasil",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(Modifier.height(22.dp))

        // 📌 CARD DETAIL PEMBAYARAN
        PaymentSummaryCard()

        Spacer(Modifier.height(18.dp))

        // 🧭 CARD ROUTE RIDE
        RideInfoCard()

        Spacer(Modifier.weight(1f))

        // 🔵 BUTTON LANJUT — BAWAH FIX
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F3D82))
        ) {
            Text(
                "Lanjut",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun PaymentSummaryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color(0xFFE6E6E6))
    ) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 18.dp)) {

            Text("Rincian Pembayaran", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(18.dp))

            PaymentRow("Tipe Pembayaran", "QRIS")
            PaymentRow("Tanggal", "10/02/2009")
            PaymentRow("No Transaksi", "INV/20250104/123456789")

            Spacer(Modifier.height(12.dp))
            Divider()
            Spacer(Modifier.height(12.dp))

            PaymentRow("Biaya Per Penebeng", "Rp 50.000,00")
            PaymentRow("Biaya Admin", "Rp 15.000,00")

            Spacer(Modifier.height(12.dp))
            Divider()
            Spacer(Modifier.height(12.dp))

            PaymentRow(
                label = "Total",
                value = "Rp 65.000,00",
                valueColor = Color(0xFF0FA958),
                bold = true
            )
        }
    }
}

@Composable
private fun PaymentRow(
    label: String,
    value: String,
    valueColor: Color = Color.Black,
    bold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Medium)
        Text(
            value,
            color = valueColor,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
private fun RideInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color(0xFFE1E3E6)),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            Text("04 Januari 2025 | 13.45 - 18.45", fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(18.dp))

            RouteRow()

            Spacer(Modifier.height(18.dp))

            Divider(color = Color(0xFFE8E8E8))
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
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
private fun PreviewPassengerRideMotorPaymentSuccessScreen() {
    PassengerRideMotorPaymentSuccessScreen()
}
