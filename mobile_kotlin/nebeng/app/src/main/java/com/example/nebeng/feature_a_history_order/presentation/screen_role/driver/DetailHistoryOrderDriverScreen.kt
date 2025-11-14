package com.example.nebeng.feature_a_history_order.presentation.screen_role.driver

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
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

// [ BASE WORK ]
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailHistoryOrderDriverScreen(
//    orderId: Int,
//    uiState: HistoryOrderUiState,
//    onBack: () -> Unit = {},
//    onRetry: () -> Unit = {}
//) {
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Detail Riwayat Order") },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        }
//    ) { padding ->
//
//        when {
//            uiState.isLoading -> {
//                Box(
//                    modifier = Modifier
//                        .padding(padding)
//                        .fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator()
//                }
//            }
//
//            uiState.errorMessage != null -> {
//                Box(
//                    modifier = Modifier
//                        .padding(padding)
//                        .fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Text(
//                            text = uiState.errorMessage,
//                            color = Color.Red,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Spacer(Modifier.height(8.dp))
//                        Button(onClick = onRetry) {
//                            Text("Coba Lagi")
//                        }
//                    }
//                }
//            }
//
//            else -> {
//                val order = uiState.historyItems
//                    .firstOrNull { it.bookingId == orderId }
//
//                if (order == null) {
//                    Box(
//                        modifier = Modifier
//                            .padding(padding)
//                            .fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text("Data tidak ditemukan")
//                    }
//                } else {
//                    DetailOrderContent(
//                        order = order,
//                        padding = padding
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun DetailOrderContent(
//    order: HistoryOrderItem,
//    padding: PaddingValues
//) {
//    Column(
//        modifier = Modifier
//            .padding(padding)
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        Text(
//            text = "Kode Booking: ${order.bookingCode}",
//            style = MaterialTheme.typography.titleMedium
//        )
//
//        Text("Titik Jemput: ${order.departureTerminalName}")
//        Text("Detail Jemput: ${order.departureTerminalDetail}")
//
//        Text("Tujuan: ${order.arrivalTerminalName}")
//        Text("Detail Tujuan: ${order.arrivalTerminalDetail}")
//
//        Text("Harga: Rp ${order.totalPrice}")
//        Text("Status: ${order.rideStatus?.value}")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewDetailHistoryOrderDriverScreen() {
//
//    val dummy = HistoryOrderUiState(
//        historyItems = listOf(
//            HistoryOrderItem(
//                bookingId = 10,
//                bookingCode = "NB900",
//                createdAt = "2025-01-01T10:00:00",
//                departureTerminalId = 1,
//                arrivalTerminalId = 2,
//                seatsReserved = 2,
//                totalPrice = 50000,
//                bookingStatus = BookingStatus.PENDING,
//                vehicleType = VehicleType.MOTOR,
//                rideStatus = RideStatus.SELESAI,
//                driverName = "Tanjirou",
//                departureTerminalName = "Terminal A",
//                arrivalTerminalName = "Terminal B",
//                departureTerminalDetail = "Jl. Mawar No. 42",
//                arrivalTerminalDetail = "Jl. Melati No. 10"
//            )
//        )
//    )
//
//    MaterialTheme {
//        DetailHistoryOrderDriverScreen(
//            orderId = 10,
//            uiState = dummy
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailHistoryOrderDriverScreen(
    orderId: Int,
    uiState: HistoryOrderUiState,
    onBack: () -> Unit = {},
    onRetry: () -> Unit = {}
) {
    val order = uiState.historyItems.firstOrNull { it.bookingId == orderId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Tebengan", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        when {
            order == null -> Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Data tidak ditemukan")
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    // ======================================================
                    // 1) HEADER CARD
                    // ======================================================
                    item {
                        DetailHeaderCard(order)
                    }

                    // ======================================================
                    // 2) INFORMASI MITRA
                    // ======================================================
                    item {
                        MitraInfoCard(order)
                    }

                    // ======================================================
                    // 3) INFORMASI PENEBENG
                    // ======================================================
                    item {
                        PenebengInfoCard()
                    }

                    item { Spacer(Modifier.height(32.dp)) }
                }
            }
        }
    }
}


@Composable
fun DetailHeaderCard(order: HistoryOrderItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // ====== Row Atas (tanggal + status)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "${order.createdAt.take(10)} | ${order.createdAt.takeLast(8)} | ${order.bookingCode}",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
                StatusBadge(order.rideStatus)
            }

            Spacer(Modifier.height(12.dp))

            // ====== Rute
            RideRouteItem(
                from = order.departureTerminalName,
                fromDetail = order.departureTerminalDetail,
                to = order.arrivalTerminalName,
                toDetail = order.arrivalTerminalDetail
            )

            Spacer(Modifier.height(16.dp))
            Divider(color = Color(0xFFECECEC))
            Spacer(Modifier.height(8.dp))

            // ====== Pendapatan
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (order.rideStatus == RideStatus.SELESAI) "Pendapatan" else "Estimasi Pendapatan",
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Rp ${String.format("%,d", order.totalPrice)}",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F1C43)
                )
            }
        }
    }
}

@Composable
private fun RideRouteItem(
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

@Composable
private fun MitraInfoCard(order: HistoryOrderItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Informasi Mitra",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(12.dp))

            InfoRow("Nama Mitra", order.driverName ?: "-")
            InfoRow("Transportasi", order.vehicleType.value)   // Motor / Mobil
            InfoRow("Nomor Plat",  "-") // memang belum ada field nomor plat
//            InfoRow("Tahun Registrasi", "-")
//            InfoRow("Masa STNK",  "-")
            InfoRow("Nama Kendaraan",  "-") // memang belum ada variable vehicleName di HistoryItemData
            InfoRow("Warna", "-")
//            InfoRow("Verifikasi", "Terverifikasi")
            InfoRow("Jumlah Kursi", order.seatsReserved.toString() ?: "-")
        }
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
private fun PenebengInfoCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Informasi Penebeng",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE8EAF6)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("N", fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.width(12.dp))

                Column {
                    Text("Nezuko", fontWeight = FontWeight.Medium)
                    Text("Chat customer", fontSize = 12.sp, color = Color.Gray)
                }

                Spacer(Modifier.weight(1f))

                Icon(Icons.Default.ChevronRight, contentDescription = null)
            }

            Spacer(Modifier.height(8.dp))

            Divider(color = Color(0xFFECECEC))

            Spacer(Modifier.height(8.dp))

            BarangDikirimCard()
        }
    }
}

@Composable
private fun BarangDikirimCard() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Barang yang Dikirim",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        )

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_barang),
                contentDescription = "Barang",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(16.dp))

            Text(
                text = "Berlian 1 Kilogram",
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp
            )
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailHistoryOrderDriverScreen() {

    // Dummy item untuk preview
    val dummyOrder = HistoryOrderItem(
        bookingId = 1,
        bookingCode = "NB-98299A",
        createdAt = "2025-01-04T13:45:00",
        departureTerminalId = 1,
        arrivalTerminalId = 2,
        seatsReserved = 3,
        totalPrice = 50000,
        bookingStatus = BookingStatus.PENDING,
        rideStatus = RideStatus.SELESAI,
        vehicleType = VehicleType.MOBIL,
//        vehicleName = "Toyota",
//        vehicleColor = "White",
//        driverId = 1,

        // data terminal hasil join
        departureTerminalName = "Yogyakarta",
        departureTerminalDetail = "Alun-alun Yogyakarta",
        arrivalTerminalName = "Purwokerto",
        arrivalTerminalDetail = "Alun-alun Purwokerto",

        // data driver/vehicle (sementara minimal dulu)
        driverName = "Zenitsu Agatsuma",
//        vehicleName = "Avanza",
//        vehicleColor = "Putih"
        // tambah field lain jika Anda punya
        customerName = "Customer 1",
        customerId = 1,
        averageRating = 4.5f
    )

    val dummyState = HistoryOrderUiState(
        historyItems = listOf(dummyOrder),
        isLoading = false
    )

    MaterialTheme {
        DetailHistoryOrderDriverScreen(
            orderId = 1,
            uiState = dummyState
        )
    }
}
