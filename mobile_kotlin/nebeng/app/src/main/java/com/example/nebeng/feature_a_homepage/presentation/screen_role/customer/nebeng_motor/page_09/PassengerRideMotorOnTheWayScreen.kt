package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_09

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerRideMotorDriverOnTheWayScreen(
    onBack: () -> Unit = {}
) {
    val bankName = "BNI Bank"          // placeholder
    val price = "Rp 120.000"           // placeholder
    val departureCity = "Yogyakarta"   // placeholder
    val arrivalCity = "Purwokerto"     // placeholder

    Box(modifier = Modifier.fillMaxSize()) {

        // ================= MAP STATIC BACKGROUND =================
        Image(
            painter = painterResource(id = R.drawable.img_map_placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp),
            contentScale = ContentScale.Crop
        )

        // ================ SCROLLABLE CONTENT =====================
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp)
        ) {
            item {
                // ===== TOP APP BAR =====
                TopAppBar(
                    title = {
                        Text(
                            "Nebeng",
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
            }

            item {
                Spacer(Modifier.height(260.dp)) // agar card turun dari map
                RideStatusCard(
                    departureCity = departureCity,
                    arrivalCity = arrivalCity,
                    bankName = bankName,
                    price = price
                )
                Spacer(Modifier.height(26.dp))
            }
        }
    }
}

@Composable
private fun RideStatusCard(
    departureCity: String,
    arrivalCity: String,
    bankName: String,
    price: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {

            // ===== JUDUL =====
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Menunggu driver ke Lokasi Anda", fontWeight = FontWeight.SemiBold)
                Image(
                    painter = painterResource(id = R.drawable.img_pin_map_24),
                    contentDescription = null,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(Modifier.height(22.dp))

            // ===== DEPARTURE =====
            RideLocationRow(
                iconColor = Color(0xFF2ECC71),
                title = "Lokasi Anda",
                city = departureCity,
                time = "12.00"
            )

            Divider(Modifier.padding(vertical = 12.dp))

            // ===== ARRIVAL =====
            RideLocationRow(
                iconColor = Color(0xFFFF6E42),
                title = "Tujuan Anda",
                city = arrivalCity,
                time = "17.00"
            )

            Spacer(Modifier.height(16.dp))
            Divider()

            Spacer(Modifier.height(10.dp))

            // ===== PAYMENT =====
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.qris),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(bankName, fontWeight = FontWeight.Medium)
                }
                Text(price, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun RideLocationRow(
    iconColor: Color,
    title: String,
    city: String,
    time: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(iconColor, CircleShape)
            )

            Spacer(Modifier.width(10.dp))

            Column {
                Text(title, fontSize = 12.sp, color = Color.Gray)
                Text(city, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }

        Text(time, fontSize = 13.sp, color = Color.Gray)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewDriverOnTheWay() {
    PassengerRideMotorDriverOnTheWayScreen()
}
