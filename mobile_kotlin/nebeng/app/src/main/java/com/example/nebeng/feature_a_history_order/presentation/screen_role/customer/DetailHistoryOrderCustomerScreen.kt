package com.example.nebeng.feature_a_history_order.presentation.screen_role.customer

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailHistoryOrderCustomerScreen(
    orderId: Int,
    uiState: HistoryOrderUiState,
    onBack: () -> Unit = {}
) {
    val order = uiState.historyItems.firstOrNull { it.bookingId == orderId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        if (order == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Data tidak ditemukan")
            }
            return@Scaffold
        }

        // ============================
        // CONTENT
        // ============================
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            item {
                TripHeaderCustomer(order)
            }

            item {
                DriverInfoCard(order)
            }

            item {
                CustomerRouteCard(order)
            }

            item {
                PassengerSection(order)
            }

            item {
                PriceSummary(order)
            }

            item { Spacer(Modifier.height(22.dp)) }
        }
    }
}

@Composable
fun TripHeaderCustomer(order: HistoryOrderItem) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        // Icon lingkaran
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3F2FD)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_motor), // placeholder
                contentDescription = null,
                tint = Color(0xFF1565C0)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                text = "Nebeng ${order.vehicleType.value}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            StatusBadge(order.rideStatus)
        }
    }
}

@Composable
private fun StatusBadge(status: RideStatus?) {
    val (bgColor, textColor, label) = when (status) {
        RideStatus.SELESAI          -> Triple(Color(0xFFE8F5E9), Color(0xFF388E3C), "Selesai")
        RideStatus.DALAM_PERJALANAN -> Triple(Color(0xFFE3F2FD), Color(0xFF1565C0), "Dalam Perjalanan")
        RideStatus.DIBATALKAN       -> Triple(Color(0xFFFFEBEE), Color(0xFFD32F2F), "Dibatalkan")
        RideStatus.PENDING          -> Triple(Color(0xFFFFF1E6), Color(0xFFE27A00), "Menunggu")
        else                        -> Triple(Color(0xFFF5F5F5), Color.Gray, "Tidak diketahui")
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun DriverInfoCard(order: HistoryOrderItem) {

    Column(modifier = Modifier.padding(8.dp)) {

        Text(
            text = "Detail Perjalanan",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            // Foto Driver (dummy dulu)
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = order.driverName ?: "-",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text("⭐ 5.0  •  R2424MJ", fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(Modifier.weight(1f))

            Row {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Call, contentDescription = "Call")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Message, contentDescription = "Chat")
                }
            }
        }
    }

}

@Composable
fun CustomerRouteCard(order: HistoryOrderItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            RideRouteItem(
                from = order.departureTerminalName,
                fromDetail = order.departureTerminalDetail,
                to = order.arrivalTerminalName,
                toDetail = order.arrivalTerminalDetail
            )
        }
    }
}

@Composable
fun PassengerSection(order: HistoryOrderItem) {
    Column {
        Text(
            text = "Penumpang",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(12.dp))

        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Ailsa Nasywa")
                Spacer(Modifier.weight(1f))
                Text("Penumpang 1", color = Color.Gray, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun PriceSummary(order: HistoryOrderItem) {
    Column {
        InfoRow("Harga", "Rp${String.format("%,d", order.totalPrice)}")
        InfoRow("Total Penumpang", order.seatsReserved.toString())
        Divider(Modifier.padding(vertical = 8.dp))
        InfoRow(
            label = "Total",
            value = "Rp${String.format("%,d", order.totalPrice)}"
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Medium)
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
fun RideRouteItem(
    from: String,
    fromDetail: String,
    to: String,
    toDetail: String
) {
    val dotSize = 10.dp
    val lineColor = Color(0xFFCFD8DC)
    val density = LocalDensity.current

    var fromCenterY by remember { mutableStateOf(0f) }   // px
    var toCenterY by remember { mutableStateOf(0f) }     // px
    var leftTopY by remember { mutableStateOf(0f) }      // px
    var containerHeight by remember { mutableStateOf(0f) } // px

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coords ->
                containerHeight = coords.size.height.toFloat()
                leftTopY = coords.boundsInWindow().top
            }
    ) {

        // ============================
        // 1) LAYOUT ASLI ANDA
        // ============================
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {

            // KIRI – tempat rail akan digambar (di canvas)
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .fillMaxHeight()
                    .onGloballyPositioned { leftCoords ->
                        leftTopY = leftCoords.boundsInWindow().top
                    }
            )

            Spacer(Modifier.width(8.dp))

            // ============================
            //   KANAN – TEXT
            // ============================
            Column(modifier = Modifier.weight(1f)) {

                // ===== FROM =====
                Text(
                    text = from,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F1C43)
                    ),
                    modifier = Modifier.onGloballyPositioned { coords ->
                        fromCenterY = coords.boundsInWindow().center.y
                    }
                )

                Text(
                    text = fromDetail,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ===== TO =====
                Text(
                    text = to,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F1C43)
                    ),
                    modifier = Modifier.onGloballyPositioned { coords ->
                        toCenterY = coords.boundsInWindow().center.y
                    }
                )

                Text(
                    text = toDetail,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }

        }

        // ============================
        // 2) RAIL (DOT + LINE) — CANVAS
        // ============================
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            // pastikan data sudah siap
            if (fromCenterY == 0f || toCenterY == 0f) return@Canvas

            // hitung posisi Y relatif terhadap top box kiri
            val fromY = fromCenterY - leftTopY
            val toY = toCenterY - leftTopY

            val dotPx = with(density) { dotSize.toPx() }
            val dotRadius = dotPx / 2
            val centerX = with(density) { 24.dp.toPx() } / 2f

            // GARIS di antara dot (dengan jarak aman)
            drawLine(
                color = lineColor,
                start = Offset(centerX, fromY + dotRadius + 2),
                end = Offset(centerX, toY - dotRadius - 2),
                strokeWidth = with(density) { 2.dp.toPx() }
            )

            // DOT biru
            drawCircle(
                color = Color(0xFF1565C0),
                radius = dotRadius,
                center = Offset(centerX, fromY)
            )

            // DOT merah
            drawCircle(
                color = Color(0xFFD32F2F),
                radius = dotRadius,
                center = Offset(centerX, toY)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailHistoryOrderCustomerScreen() {
    val dummyOrder = HistoryOrderItem(
        bookingId = 1,
        bookingCode = "NB83422",
        createdAt = "2025-01-04T13:45:00",
        departureTerminalId = 1,
        arrivalTerminalId = 2,
        seatsReserved = 1,
        totalPrice = 62000,
        bookingStatus = BookingStatus.PENDING,
        rideStatus = RideStatus.SELESAI,
        vehicleType = VehicleType.MOTOR,
        departureTerminalName = "Yogyakarta Pos 1",
        departureTerminalDetail = "Senin, 02 September 2024",
        arrivalTerminalName = "Purwokerto Pos 1",
        arrivalTerminalDetail = "Senin, 02 September 2024",
        driverName = "Jamal"
    )

    val dummyState = HistoryOrderUiState(
        historyItems = listOf(dummyOrder)
    )

    MaterialTheme {
        DetailHistoryOrderCustomerScreen(orderId = 1, uiState = dummyState)
    }
}
