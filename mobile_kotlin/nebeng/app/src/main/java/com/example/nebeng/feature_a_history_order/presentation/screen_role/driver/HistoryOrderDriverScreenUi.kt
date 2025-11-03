package com.example.nebeng.feature_a_history_order.presentation.screen_role.driver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderUiState
import com.example.nebeng.feature_a_history_order.presentation.support_for_present.model.HistoryItemData

//@Composable
//fun HistoryOrderDriverScreenUi() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("History Order driver")
//    }
//}


@Composable
fun HistoryOrderDriverScreenUi(state: HistoryOrderUiState) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Semua", "Selesai", "Menunggu", "Dibatalkan")

    // ambil semua data order dari state
    val allOrders = state.historyItems

    // filter status sesuai tab
    val filteredOrders = when (selectedTab) {
        0 -> allOrders
        1 -> allOrders.filter { it.status.equals("Selesai", ignoreCase = true) }
        2 -> allOrders.filter { it.status.equals("Proses", ignoreCase = true) || it.status.equals("Menunggu", ignoreCase = true) }
        3 -> allOrders.filter { it.status.equals("Dibatalkan", ignoreCase = true) }
        else -> allOrders
    }

    Scaffold(containerColor = Color(0xFFF8F9FD)) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ======= HEADER =======
            item {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Tebengan Akan Datang",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(12.dp))
            }

            // ======= TAB =======
            item {
//                PrimaryTabRow(selectedTabIndex = selectedTab) {
//                    tabs.forEachIndexed { index, title ->
//                        Tab(
//                            selected = selectedTab == index,
//                            onClick = { selectedTab = index },
//                            text = {
//                                Text(
//                                    text = title,
//                                    color = if (selectedTab == index) Color(0xFF0F1C43) else Color.Gray,
//                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
//                                )
//                            }
//                        )
//                    }
//                }
                ScrollableStatusTab(
                    tabs = tabs,
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
                Spacer(Modifier.height(12.dp))
            }

            // ======= CONTENT =======
            if (filteredOrders.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Belum ada tebengan dengan status '${tabs[selectedTab]}'",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            } else {
                items(filteredOrders) { order ->
                    DriverHistoryCard(order)
                }
            }

            item { Spacer(Modifier.height(32.dp)) }
        }
    }
}

@Composable
private fun DriverHistoryCard(order: HistoryItemData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // ======= Top Row =======
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Text(
//                    text = "Sab, 04 Januari 2025 | 13.45 - 18.45 | ${order.title}",
//                    fontSize = 10.sp,
//                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//                )
                Text(
                    text = "${order.dayDate} | ${order.time} | Nebeng ${order.category}",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )

                StatusBadge(order.status)
            }

            Spacer(Modifier.height(12.dp))

            // ======= Route =======
//            RideRouteItem(
//                from = "Yogyakarta",
//                fromDetail = "Alun-alun Yogyakarta",
//                to = "Purwokerto",
//                toDetail = "Alun-alun Purwokerto"
//            )
            RideRouteItem(
                from = order.fromCity,
                fromDetail = order.fromPos,
                to = order.toCity,
                toDetail = order.toPos
            )

            Spacer(Modifier.height(16.dp))
            Divider(color = Color(0xFFECECEC))
            Spacer(Modifier.height(8.dp))

            // ======= Pendapatan =======
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (order.status.equals("Selesai", true)) "Pendapatan" else "Estimasi Pendapatan",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    text = order.totalPrice,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F1C43)
                    )
                )
            }
        }
    }
}

@Composable
private fun StatusBadge(status: String) {
    val (bgColor, textColor) = when (status.lowercase()) {
        "selesai" -> Color(0xFFE8F5E9) to Color(0xFF388E3C)
        "proses", "menunggu" -> Color(0xFFE3F2FD) to Color(0xFF1565C0)
        "kosong" -> Color(0xFFFFF1E6) to Color(0xFFE27A00)
        "dibatalkan" -> Color(0xFFFFEBEE) to Color(0xFFD32F2F)
        else -> Color(0xFFF5F5F5) to Color.Gray
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = status.replaceFirstChar { it.uppercase() },
            fontSize = 11.sp,
            color = textColor,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
private fun RideRouteItem(
    from: String,
    fromDetail: String,
    to: String,
    toDetail: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier
                .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
                .width(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Titik Biru
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1565C0))
            )

            // Garis Vertikal abu-abu
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(32.dp)
                    .padding(vertical = 2.dp)
                    .background(Color(0xFFCFD8DC)) // Abu-abu
            )

            // Titik merah
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD32F2F))
            )
        }

        Spacer(Modifier.width(8.dp))
        // Kolom
        Column {
            Text(
                text = from,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = fromDetail,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = to,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = toDetail,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
    }
}

@Composable
private fun ScrollableStatusTab(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val activeBlue = Color(0xFF002F6C)      // 🔹 biru gelap khas profesional
    val borderBlue = Color(0xFF1A47B8)      // 🔹 sedikit lebih terang untuk outline

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(tabs) { index, title ->
            val isSelected = selectedTab == index

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isSelected) activeBlue else Color.Transparent
                    )
                    .border(
                        width = 1.dp,
                        color = borderBlue,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onTabSelected(index) }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = if(isSelected) Color.White else Color(0xFF0F1C43),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}

//@Composable
//private fun ScrollableStatusTab(
//    tabs: List<String>,
//    selectedTab: Int,
//    onTabSelected: (Int) -> Unit
//) {
//    // 🎨 Warna utama dan gradasi biru
//    val gradientBrush = Brush.horizontalGradient(
//        colors = listOf(
//            Color(0xFF004C97), // biru tua
//            Color(0xFF007BFF)  // biru terang
//        )
//    )
//
//    LazyRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        itemsIndexed(tabs) { index, title ->
//            val isSelected = selectedTab == index
//
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(12.dp))
////                    .background(
////                        if (isSelected) Brush.horizontalGradient(
////                            listOf(Color(0xFF004C97), Color(0xFF007BFF))
////                        )
////                        else Color.Transparent
////                    )
//                    .then(
//                        if (isSelected)
//                            Modifier.background(brush = gradientBrush, shape = RoundedCornerShape(12.dp))
//                        else
//                            Modifier.background(color = Color.Transparent, shape = RoundedCornerShape(12.dp))
//                    )
//                    .border(
//                        width = 1.5.dp,
//                        color = Color(0xFF004C97),
//                        shape = RoundedCornerShape(12.dp)
//                    )
//                    .clickable { onTabSelected(index) }
//                    .padding(horizontal = 20.dp, vertical = 10.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = title,
//                    color = if (isSelected) Color.White else Color(0xFF004C97),
//                    style = MaterialTheme.typography.bodyMedium.copy(
//                        fontWeight = FontWeight.SemiBold
//                    )
//                )
//            }
//        }
//    }
//}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHistoryOrderDriverScreenUi() {
    MaterialTheme {
        HistoryOrderDriverScreenUi(
            state = HistoryOrderUiState(
                historyItems = listOf(
                    HistoryItemData(
                        code = "1",
                        category = "Motor",
                        status = "Selesai",
                        fromCity = "Yogyakarta",
                        toCity = "Purwokerto",
                        fromPos = "Pos 1",
                        toPos = "Pos 2",
                        dayDate = "Sabtu, 4 Januari 2025",
                        time = "13.45 - 18.45",
                        vehicle = "Beat Street",
                        plate = "AB 2010 KA",
                        pax = 1,
                        totalPrice = "Rp 150.000"
                    ),
                    HistoryItemData(
                        code = "2",
                        category = "Mobil",
                        status = "Proses",
                        fromCity = "Solo",
                        toCity = "Semarang",
                        fromPos = "Pos 3",
                        toPos = "Pos 4",
                        dayDate = "Sabtu, 4 Januari 2025",
                        time = "08.30 - 12.00",
                        vehicle = "Avanza",
                        plate = "B 1243 CD",
                        pax = 2,
                        totalPrice = "Rp 200.000"
                    )
                )
            )
        )
    }
}
