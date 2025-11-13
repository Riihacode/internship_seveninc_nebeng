package com.example.nebeng.feature_a_history_order.presentation.screen_role.driver

import android.R.attr.onClick
import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.core.utils.BookingStatus
import com.example.nebeng.core.utils.RideStatus
import com.example.nebeng.core.utils.VehicleType
import com.example.nebeng.feature_a_auth.domain.model.Auth
import com.example.nebeng.feature_a_history_order.domain.model.HistoryOrderItem
import com.example.nebeng.feature_a_history_order.presentation.HistoryOrderUiState
import com.example.nebeng.feature_a_history_order.presentation.support_for_present.model.HistoryItemData
import kotlin.Int
import kotlin.String

//@Composable
//fun HistoryOrderDriverScreenUi() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("History Order driver")
//    }
//}
//
//
//@Composable
//fun HistoryOrderDriverScreenUi(state: HistoryOrderUiState) {
//    var selectedTab by remember { mutableStateOf(0) }
//    val tabs = listOf("Semua", "Selesai", "Menunggu", "Dibatalkan")
//
//    // ambil semua data order dari state
//    val allOrders = state.historyItems
//
//    // filter status sesuai tab
//    val filteredOrders = when (selectedTab) {
//        0 -> allOrders
//        1 -> allOrders.filter { it.status.equals("Selesai", ignoreCase = true) }
//        2 -> allOrders.filter { it.status.equals("Proses", ignoreCase = true) || it.status.equals("Menunggu", ignoreCase = true) }
//        3 -> allOrders.filter { it.status.equals("Dibatalkan", ignoreCase = true) }
//        else -> allOrders
//    }
//
//    Scaffold(containerColor = Color(0xFFF8F9FD)) { padding ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            // ======= HEADER =======
//            item {
//                Spacer(Modifier.height(16.dp))
//                Text(
//                    text = "Tebengan Akan Datang",
//                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
//                )
//                Spacer(Modifier.height(12.dp))
//            }
//
//            // ======= TAB =======
//            item {
//                ScrollableStatusTab(
//                    tabs = tabs,
//                    selectedTab = selectedTab,
//                    onTabSelected = { selectedTab = it }
//                )
//                Spacer(Modifier.height(12.dp))
//            }
//
//            // ======= CONTENT =======
//            if (filteredOrders.isEmpty()) {
//                item {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 32.dp),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "Belum ada tebengan dengan status '${tabs[selectedTab]}'",
//                            color = Color.Gray,
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                    }
//                }
//            } else {
//                items(filteredOrders) { order ->
//                    DriverHistoryCard(order)
//                }
//            }
//
//            item { Spacer(Modifier.height(32.dp)) }
//        }
//    }
//}
//
//@Composable
//private fun DriverHistoryCard(order: HistoryItemData) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            // ======= Top Row =======
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
////                Text(
////                    text = "Sab, 04 Januari 2025 | 13.45 - 18.45 | ${order.title}",
////                    fontSize = 10.sp,
////                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
////                )
//                Text(
//                    text = "${order.dayDate} | ${order.time} | Nebeng ${order.category}",
//                    fontSize = 10.sp,
//                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//                )
//
//                StatusBadge(order.status)
//            }
//
//            Spacer(Modifier.height(12.dp))
//
//            // ======= Route =======
////            RideRouteItem(
////                from = "Yogyakarta",
////                fromDetail = "Alun-alun Yogyakarta",
////                to = "Purwokerto",
////                toDetail = "Alun-alun Purwokerto"
////            )
//            RideRouteItem(
//                from = order.fromCity,
//                fromDetail = order.fromPos,
//                to = order.toCity,
//                toDetail = order.toPos
//            )
//
//            Spacer(Modifier.height(16.dp))
//            Divider(color = Color(0xFFECECEC))
//            Spacer(Modifier.height(8.dp))
//
//            // ======= Pendapatan =======
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = if (order.status.equals("Selesai", true)) "Pendapatan" else "Estimasi Pendapatan",
//                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
//                )
//                Text(
//                    text = order.totalPrice,
//                    style = MaterialTheme.typography.bodyMedium.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF0F1C43)
//                    )
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun StatusBadge(status: String) {
//    val (bgColor, textColor) = when (status.lowercase()) {
//        "selesai" -> Color(0xFFE8F5E9) to Color(0xFF388E3C)
//        "proses", "menunggu" -> Color(0xFFE3F2FD) to Color(0xFF1565C0)
//        "kosong" -> Color(0xFFFFF1E6) to Color(0xFFE27A00)
//        "dibatalkan" -> Color(0xFFFFEBEE) to Color(0xFFD32F2F)
//        else -> Color(0xFFF5F5F5) to Color.Gray
//    }
//
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(8.dp))
//            .background(bgColor)
//            .padding(horizontal = 10.dp, vertical = 4.dp)
//    ) {
//        Text(
//            text = status.replaceFirstChar { it.uppercase() },
//            fontSize = 11.sp,
//            color = textColor,
//            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium)
//        )
//    }
//}
//
//@Composable
//private fun RideRouteItem(
//    from: String,
//    fromDetail: String,
//    to: String,
//    toDetail: String
//) {
//    Row(modifier = Modifier.fillMaxWidth()) {
//
//        Column(
//            modifier = Modifier
//                .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
//                .width(20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Titik Biru
//            Box(
//                modifier = Modifier
//                    .size(10.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFF1565C0))
//            )
//
//            // Garis Vertikal abu-abu
//            Box(
//                modifier = Modifier
//                    .width(2.dp)
//                    .height(32.dp)
//                    .padding(vertical = 2.dp)
//                    .background(Color(0xFFCFD8DC)) // Abu-abu
//            )
//
//            // Titik merah
//            Box(
//                modifier = Modifier
//                    .size(10.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFFD32F2F))
//            )
//        }
//
//        Spacer(Modifier.width(8.dp))
//        // Kolom
//        Column {
//            Text(
//                text = from,
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
//            )
//
//            Text(
//                text = fromDetail,
//                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//            )
//
//            Spacer(Modifier.height(8.dp))
//
//            Text(
//                text = to,
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
//            )
//
//            Text(
//                text = toDetail,
//                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//            )
//        }
//    }
//}
//
//@Composable
//private fun ScrollableStatusTab(
//    tabs: List<String>,
//    selectedTab: Int,
//    onTabSelected: (Int) -> Unit
//) {
//    val activeBlue = Color(0xFF002F6C)      // 🔹 biru gelap khas profesional
//    val borderBlue = Color(0xFF1A47B8)      // 🔹 sedikit lebih terang untuk outline
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
//                    .background(
//                        if (isSelected) activeBlue else Color.Transparent
//                    )
//                    .border(
//                        width = 1.dp,
//                        color = borderBlue,
//                        shape = RoundedCornerShape(12.dp)
//                    )
//                    .clickable { onTabSelected(index) }
//                    .padding(horizontal = 20.dp, vertical = 10.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = title,
//                    color = if(isSelected) Color.White else Color(0xFF0F1C43),
//                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewHistoryOrderDriverScreenUi() {
//    MaterialTheme {
//        HistoryOrderDriverScreenUi(
//            state = HistoryOrderUiState(
//                historyItems = listOf(
//                    HistoryItemData(
//                        code = "1",
//                        category = "Motor",
//                        status = "Selesai",
//                        fromCity = "Yogyakarta",
//                        toCity = "Purwokerto",
//                        fromPos = "Pos 1",
//                        toPos = "Pos 2",
//                        dayDate = "Sabtu, 4 Januari 2025",
//                        time = "13.45 - 18.45",
//                        vehicle = "Beat Street",
//                        plate = "AB 2010 KA",
//                        pax = 1,
//                        totalPrice = "Rp 150.000"
//                    ),
//                    HistoryItemData(
//                        code = "2",
//                        category = "Mobil",
//                        status = "Proses",
//                        fromCity = "Solo",
//                        toCity = "Semarang",
//                        fromPos = "Pos 3",
//                        toPos = "Pos 4",
//                        dayDate = "Sabtu, 4 Januari 2025",
//                        time = "08.30 - 12.00",
//                        vehicle = "Avanza",
//                        plate = "B 1243 CD",
//                        pax = 2,
//                        totalPrice = "Rp 200.000"
//                    )
//                )
//            )
//        )
//    }
//}

// ==========================================================

//
//@Composable
//fun HistoryOrderDriverScreenUi(
//    uiState: HistoryOrderUiState,
//    onBack: () -> Unit = {},
//    onDetailCLick: (HistoryOrderItem) -> Unit = {}
//) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("History Order driver")
//    }
//}

/* ================================================================
 * 🎨 Warna khas Nebeng
 * ================================================================ */
//private val NebengBlue = Color(0xFF002F6C)
//private val NebengGray = Color(0xFF6B7280)
//private val NebengBg = Color(0xFFF8F9FD)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HistoryOrderDriverScreenUi(
//    uiState: HistoryOrderUiState,
//    onBack: () -> Unit = {},
//    onDetailClick: (HistoryOrderItem) -> Unit = {}
//) {
//    var selectedTab by remember { mutableStateOf(0) }
//    var selectedVehicleType by remember { mutableStateOf<VehicleType?>(null) }
//    var selectedStatus by remember { mutableStateOf<RideStatus?>(null) }
////    val tabs = listOf("Semua", "Selesai", "Menunggu", "Dibatalkan")
//    var selectedStatusLabel by remember { mutableStateOf("Semua Status") }
//
//    // ambil semua data order dari state
////    val allOrders = uiState.historyItems
//
//    // filter status sesuai tab
////    val filteredOrders = when (selectedTab) {
////        0 -> allOrders
////        1 -> allOrders.filter { it.status.equals("Selesai", ignoreCase = true) }
////        2 -> allOrders.filter { it.status.equals("Proses", ignoreCase = true) || it.status.equals("Menunggu", ignoreCase = true) }
////        3 -> allOrders.filter { it.status.equals("Dibatalkan", ignoreCase = true) }
////        else -> allOrders
////    }
////    val filtered = allOrders.filter { order ->
////        when(selectedTab) {
////            0 -> order.rideStatus == RideStatus.SELESAI
////            1 -> order.rideStatus == RideStatus.DALAM_PERJALANAN || order.bookingStatus == BookingStatus.PENDING
////            2 -> order.rideStatus == RideStatus.DIBATALKAN
////            else -> true
////        }
////    }
//
//    Scaffold(
//        containerColor = NebengBg,
//        topBar = {
//            TopAppBar (
//                title = { Text("Riwayat Tebengan", fontWeight = FontWeight.SemiBold) },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
//            )
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            DriverTopTabs(selectedTab, onTabSelected = { selectedTab = it })
//            Spacer(Modifier.height(8.dp))
//
////            val filtered = allOrders.filter { order ->
//            val filtered = uiState.historyItems.filter { order ->
//                val matchVehicle = selectedVehicleType == null || order.vehicleType == selectedVehicleType
//                val matchStatus = selectedStatus == null || order.rideStatus == selectedStatus
//                val matchTab =  when(selectedTab) {
//                    0 -> order.rideStatus == RideStatus.SELESAI
//                    1 -> order.rideStatus == RideStatus.DALAM_PERJALANAN || order.bookingStatus == BookingStatus.PENDING
//                    2 -> order.rideStatus == RideStatus.DIBATALKAN
//                    else -> true
//                }
//
//                matchVehicle && matchStatus && matchTab
//            }
//
////            // ======= HEADER =======
////            item {
////                Spacer(Modifier.height(16.dp))
////                Text(
////                    text = "Tebengan Akan Datang",
////                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
////                )
////                Spacer(Modifier.height(12.dp))
////            }
////
////            // ======= TAB =======
////            item {
////                ScrollableStatusTab(
////                    tabs = tabs,
////                    selectedTab = selectedTab,
////                    onTabSelected = { selectedTab = it }
////                )
////                Spacer(Modifier.height(12.dp))
////            }
////
////            // ======= CONTENT =======
////            if (filteredOrders.isEmpty()) {
////                item {
////                    Box(
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .padding(top = 32.dp),
////                        contentAlignment = Alignment.Center
////                    ) {
////                        Text(
////                            text = "Belum ada tebengan dengan status '${tabs[selectedTab]}'",
////                            color = Color.Gray,
////                            style = MaterialTheme.typography.bodyMedium
////                        )
////                    }
////                }
////            } else {
////                items(filteredOrders) { order ->
////                    DriverHistoryCard(order)
////                }
////            }
////
////            item { Spacer(Modifier.height(32.dp)) }
//
//            when {
//                uiState.isLoading -> Box(
//                    Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(color = NebengBlue)
//                }
//
//                uiState.errorMessage != null -> Box(
//                    Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(uiState.errorMessage, color = Color.Red)
//                }
//
//                filtered.isEmpty() -> Box(
//                    Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text("Belum ada tebengan.", color = NebengGray)
//                }
//
//                else -> LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 16.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    items(filtered) { order ->
//                        DriverHistoryCard(order, onDetailClick)
//                    }
//                }
//            }
//        }
//    }
//}
//
///* ================================================================
// * 🔹 TABS
// * ================================================================ */
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DriverTopTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
//    val tabs = listOf("Selesai", "Dalam Proses", "Dibatalkan")
//
//    PrimaryTabRow(
//        selectedTabIndex = selectedTab,
//        containerColor = Color.White,
//        indicator = {},
//        divider = { Divider(color = NebengBlue.copy(alpha = 0.15f), thickness = 1.dp) }
//    ) {
//        tabs.forEachIndexed { index, title ->
//            val isSelected = selectedTab == index
//            Tab(
//                selected = isSelected,
//                onClick = { onTabSelected(index) },
//                modifier = Modifier.height(60.dp),
//                text = {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxHeight()
//                            .padding(top = 6.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = title,
//                            color = if (isSelected) NebengBlue else NebengGray,
//                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
//                            fontSize = 15.sp,
//                            textAlign = TextAlign.Center,
//                            lineHeight = 18.sp,
//                            overflow = TextOverflow.Ellipsis,
//                            maxLines = 2
//                        )
//                        Box(
//                            modifier = Modifier
//                                .height(3.dp)
//                                .width(60.dp)
//                                .background(
//                                    if (isSelected) NebengBlue else Color.Transparent,
//                                    RoundedCornerShape(2.dp)
//                                )
//                        )
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//private fun DriverHistoryCard(
////    order: HistoryItemData
//    data: HistoryOrderItem,
//    onClick: (HistoryOrderItem) -> Unit
//) {
////    Card(
////        modifier = Modifier.fillMaxWidth(),
////        shape = RoundedCornerShape(16.dp),
////        colors = CardDefaults.cardColors(containerColor = Color.White),
////        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
////    )
//    ElevatedCard(
////        modifier = Modifier.fillMaxWidth(),
////        shape = RoundedCornerShape(16.dp),
////        colors = CardDefaults.cardColors(containerColor = Color.White),
////        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//        onClick = { onClick(data) },
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.elevatedCardColors(containerColor =  Color.White),
//        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            // ======= Top Row =======
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
////                Text(
////                    text = "${order.dayDate} | ${order.time} | Nebeng ${order.category}",
////                    fontSize = 10.sp,
////                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
////                )
////
////                StatusBadge(order.status)
//
//                // ===========
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        imageVector = when (data.vehicleType) {
//                            VehicleType.MOBIL -> Icons.Default.DirectionsCar
//                            VehicleType.MOTOR -> Icons.Default.TwoWheeler
//                            VehicleType.BARANG -> Icons.Default.LocalShipping
//                            VehicleType.BARANG_TRANSPORTASI_UMUM -> Icons.Default.LocalShipping
//
//                        },
//                        contentDescription = null,
//                        tint = NebengBlue,
//                        modifier = Modifier.size(22.dp)
//                    )
//                    Spacer(Modifier.width(6.dp))
//                    Text(
//                        "Tebengan ${data.vehicleType.value}",
//                        color = NebengBlue,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 14.sp
//                    )
//                }
//
//                Text(
//                    text = when (data.rideStatus) {
//                        RideStatus.DIBATALKAN -> "Dibatalkan"
//                        RideStatus.SELESAI -> "Selesai"
//                        RideStatus.DALAM_PERJALANAN -> "Dalam Perjalanan"
//                        else -> "Pending"
//                    },
//                    color = when (data.rideStatus) {
//                        RideStatus.DIBATALKAN -> Color(0xFFD32F2F)
//                        RideStatus.DALAM_PERJALANAN -> Color(0xFF1A47B8)
//                        RideStatus.SELESAI -> Color(0xFF1B5E20)
//                        else -> NebengBlue
//                    },
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 13.sp
//                )
//            }
//
//            Spacer(Modifier.height(8.dp))
//
//            // ======= Route =======
////            RideRouteItem(
////                from = "Yogyakarta",
////                fromDetail = "Alun-alun Yogyakarta",
////                to = "Purwokerto",
////                toDetail = "Alun-alun Purwokerto"
////            )
//            Text(
//                "${data.departureTerminalId} → ${data.arrivalTerminalId}",
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 16.sp,
//                color = Color.Black
//            )
//
//            Spacer(Modifier.height(6.dp))
//
//            Text("${data.createdAt.take(10)} • ${data.createdAt.takeLast(8)}", color = Color.Gray, fontSize = 13.sp)
//
//            Spacer(Modifier.height(12.dp))
//            Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
//            Spacer(Modifier.height(12.dp))
//
//
//
//
////            RideRouteItem(
////                from = order.fromCity,
////                fromDetail = order.fromPos,
////                to = order.toCity,
////                toDetail = order.toPos
////            )
//
////            Spacer(Modifier.height(16.dp))
////            Divider(color = Color(0xFFECECEC))
////            Spacer(Modifier.height(8.dp))
//
//            // ======= Pendapatan =======
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
////                Text(
////                    text = if (order.status.equals("Selesai", true)) "Pendapatan" else "Estimasi Pendapatan",
////                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
////                )
////                Text(
////                    text = order.totalPrice,
////                    style = MaterialTheme.typography.bodyMedium.copy(
////                        fontWeight = FontWeight.Bold,
////                        color = Color(0xFF0F1C43)
////                    )
////                )
//                Text(
//                    if (data.rideStatus == RideStatus.SELESAI) "Pendapatan"
//                    else "Estimasi Pendapatan",
//                    color = Color.Black,
//                    fontSize = 13.sp,
//                    fontWeight = FontWeight.Medium
//                )
//
//                Text(
//                    text = "Rp${data.totalPrice}",
//                    color = NebengBlue,
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//        }
//    }
//}
//
//@Composable
//private fun StatusBadge(status: String) {
//    val (bgColor, textColor) = when (status.lowercase()) {
//        "selesai" -> Color(0xFFE8F5E9) to Color(0xFF388E3C)
//        "proses", "menunggu" -> Color(0xFFE3F2FD) to Color(0xFF1565C0)
//        "kosong" -> Color(0xFFFFF1E6) to Color(0xFFE27A00)
//        "dibatalkan" -> Color(0xFFFFEBEE) to Color(0xFFD32F2F)
//        else -> Color(0xFFF5F5F5) to Color.Gray
//    }
//
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(8.dp))
//            .background(bgColor)
//            .padding(horizontal = 10.dp, vertical = 4.dp)
//    ) {
//        Text(
//            text = status.replaceFirstChar { it.uppercase() },
//            fontSize = 11.sp,
//            color = textColor,
//            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium)
//        )
//    }
//}
//
//@Composable
//private fun RideRouteItem(
//    from: String,
//    fromDetail: String,
//    to: String,
//    toDetail: String
//) {
//    Row(modifier = Modifier.fillMaxWidth()) {
//
//        Column(
//            modifier = Modifier
//                .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
//                .width(20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Titik Biru
//            Box(
//                modifier = Modifier
//                    .size(10.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFF1565C0))
//            )
//
//            // Garis Vertikal abu-abu
//            Box(
//                modifier = Modifier
//                    .width(2.dp)
//                    .height(32.dp)
//                    .padding(vertical = 2.dp)
//                    .background(Color(0xFFCFD8DC)) // Abu-abu
//            )
//
//            // Titik merah
//            Box(
//                modifier = Modifier
//                    .size(10.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFFD32F2F))
//            )
//        }
//
//        Spacer(Modifier.width(8.dp))
//        // Kolom
//        Column {
//            Text(
//                text = from,
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
//            )
//
//            Text(
//                text = fromDetail,
//                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//            )
//
//            Spacer(Modifier.height(8.dp))
//
//            Text(
//                text = to,
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
//            )
//
//            Text(
//                text = toDetail,
//                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//            )
//        }
//    }
//}
//
//@Composable
//private fun ScrollableStatusTab(
//    tabs: List<String>,
//    selectedTab: Int,
//    onTabSelected: (Int) -> Unit
//) {
//    val activeBlue = Color(0xFF002F6C)      // 🔹 biru gelap khas profesional
//    val borderBlue = Color(0xFF1A47B8)      // 🔹 sedikit lebih terang untuk outline
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
//                    .background(
//                        if (isSelected) activeBlue else Color.Transparent
//                    )
//                    .border(
//                        width = 1.dp,
//                        color = borderBlue,
//                        shape = RoundedCornerShape(12.dp)
//                    )
//                    .clickable { onTabSelected(index) }
//                    .padding(horizontal = 20.dp, vertical = 10.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = title,
//                    color = if(isSelected) Color.White else Color(0xFF0F1C43),
//                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewHistoryOrderDriverScreenUi() {
////    MaterialTheme {
////        HistoryOrderDriverScreenUi(
////            uiState = HistoryOrderUiState(
////                historyItems = listOf(
////                    HistoryItemData(
////                        code = "1",
////                        category = "Motor",
////                        status = "Selesai",
////                        fromCity = "Yogyakarta",
////                        toCity = "Purwokerto",
////                        fromPos = "Pos 1",
////                        toPos = "Pos 2",
////                        dayDate = "Sabtu, 4 Januari 2025",
////                        time = "13.45 - 18.45",
////                        vehicle = "Beat Street",
////                        plate = "AB 2010 KA",
////                        pax = 1,
////                        totalPrice = "Rp 150.000"
////                    ),
////                    HistoryItemData(
////                        code = "2",
////                        category = "Mobil",
////                        status = "Proses",
////                        fromCity = "Solo",
////                        toCity = "Semarang",
////                        fromPos = "Pos 3",
////                        toPos = "Pos 4",
////                        dayDate = "Sabtu, 4 Januari 2025",
////                        time = "08.30 - 12.00",
////                        vehicle = "Avanza",
////                        plate = "B 1243 CD",
////                        pax = 2,
////                        totalPrice = "Rp 200.000"
////                    )
////                )
////            )
////        )
////    }
//
//    val sample = HistoryOrderUiState(
//        currentUser = Auth(
//             id = 1,
//             name = "Zenitsu",
//             username = "zen",
//             email = "z@demo@gmail.com",
//             password = "1234",
//             user_type = "driver",
//             token = "test-token",
//
//            // [BELUM DITAMBAH DI BACKEND]
//             phone = "",      // sementara
//             avatarUrl = "",   // sementara
//
//            // [BELUM DITAMBAH DI BACKEND] HomepageDriverScreenUi
//             isVerified = true
//        ),
//        historyItems = listOf(
//            HistoryOrderItem(
//                bookingId = 1,
//                bookingCode = "NB321",
//                createdAt = "2025-11-09T13:00:00",
//                departureTerminalId = 1,
//                arrivalTerminalId = 3,
//                seatsReserved = 3,
//                totalPrice = 300000,
//                bookingStatus = BookingStatus.DITERIMA,
//                vehicleType = VehicleType.MOBIL,
//                rideStatus = RideStatus.SELESAI,
//                driverName = "Zenitsu"
//            ),
//            HistoryOrderItem(
//                bookingId = 2,
//                bookingCode = "NB322",
//                createdAt = "2025-11-10T09:30:00",
//                departureTerminalId = 2,
//                arrivalTerminalId = 4,
//                seatsReserved = 2,
//                totalPrice = 180000,
//                bookingStatus = BookingStatus.PENDING,
//                vehicleType = VehicleType.MOTOR,
//                rideStatus = RideStatus.DALAM_PERJALANAN,
//                driverName = "Zenitsu"
//            )
//        )
//    )
//    MaterialTheme {
//        HistoryOrderDriverScreenUi(uiState = sample)
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryOrderDriverScreenUi(
    uiState: HistoryOrderUiState,
    onBack: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onHistoryClick: (Int) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Semua", "Selesai", "Dalam Proses", "Dibatalkan")

    val refreshState = rememberPullToRefreshState()

    // Filter berdasarkan tab
    val filteredOrders = when (selectedTab) {
        0 -> uiState.historyItems
        1 -> uiState.historyItems.filter { it.rideStatus == RideStatus.SELESAI }
        2 -> uiState.historyItems.filter { it.rideStatus == RideStatus.DALAM_PERJALANAN || it.bookingStatus == BookingStatus.PENDING }
        3 -> uiState.historyItems.filter { it.rideStatus == RideStatus.DIBATALKAN }
        else -> uiState.historyItems
    }

    Scaffold(containerColor = Color(0xFFF8F9FD)) { padding ->
        PullToRefreshBox(
            state = refreshState,
            isRefreshing = uiState.isLoading,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ===== HEADER =====
                item {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Tebengan Akan Datang",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(12.dp))
                }

                // ===== TAB =====
                item {
                    ScrollableStatusTab(
                        tabs = tabs,
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it }
                    )
                    Spacer(Modifier.height(12.dp))
                }

                // ===== CONTENT =====
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
//                    items(filteredOrders) { order ->
//                        DriverHistoryCard(order)
//                    }
                    items(filteredOrders) { order ->
                        DriverHistoryCard(
                            order = order,
                            onClick = { onHistoryClick(order.bookingId) }
                        )
                    }
                }

                item { Spacer(Modifier.height(32.dp)) }
            }
        }
    }
}

/* ================================================================
 * 🔹 Kartu Riwayat Driver
 * ================================================================ */
@SuppressLint("DefaultLocale")
@Composable
private fun DriverHistoryCard(
    order: HistoryOrderItem,
    onClick: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(order.bookingId) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // ====== Header Atas ======
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sab, ${order.createdAt.take(10)} | ${order.createdAt.takeLast(8)} | Nebeng ${order.vehicleType.value}",
                    fontSize = 10.sp,
                    color = Color.Gray
                )

                StatusBadge(order.rideStatus)
            }

            Spacer(Modifier.height(12.dp))

//            // ====== Jalur (Rute) ======
            // ====== Jalur (Rute) ======
            RideRouteItem(
                from = order.departureTerminalName.ifBlank { "Terminal A (placeholder)" },
                fromDetail = order.departureTerminalDetail.ifBlank { "Jl. Contoh No. 123, Kota Placeholder" },
                to = order.arrivalTerminalName.ifBlank { "Terminal B (placeholder)" },
                toDetail = order.arrivalTerminalDetail.ifBlank { "Jl. Ujung Timur No. 45, Kota Placeholder" }
            )

            Spacer(Modifier.height(16.dp))
            Divider(color = Color(0xFFECECEC))
            Spacer(Modifier.height(8.dp))

            // ====== Pendapatan ======
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (order.rideStatus == RideStatus.SELESAI) "Pendapatan" else "Estimasi Pendapatan",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    text = "Rp ${String.format("%,d", order.totalPrice)}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F1C43)
                    )
                )
            }
        }
    }
}

/* ================================================================
 * 🔹 Status Badge
 * ================================================================ */
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

/* ================================================================
 * 🔹 Jalur Rute (Yogyakarta → Purwokerto)
 * ================================================================ */
// [ALMOST]
//@Composable
//private fun RideRouteItem(
//    from: String,
//    fromDetail: String,
//    to: String,
//    toDetail: String
//) {
//    val dotSize = 10.dp
//    val gapBetweenDotAndLine = 2.dp
//    val lineWidth = 2.dp
//    val lineColor = Color(0xFFCFD8DC)
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(IntrinsicSize.Min)     // Kunci agar garis mengikuti tinggi teks
//    ) {
//
//        // ===============================
//        // KOLUMN KIRI (DOT + LINE + DOT)
//        // ===============================
//        Box(
//            modifier = Modifier
//                .width(24.dp)
//                .fillMaxHeight(),
//            contentAlignment = Alignment.TopCenter
//        ) {
//
//            // ---- GARIS VERTIKAL ----
//            Box(
//                modifier = Modifier
//                    .padding(
//                        top = dotSize + gapBetweenDotAndLine,
//                        bottom = dotSize + gapBetweenDotAndLine
//                    )   // 👈 GARIS TIDAK MENYENTUH TITIK
//                    .width(lineWidth)
//                    .fillMaxHeight()
//                    .background(lineColor)
//            )
//
//            // ---- DOT ATAS (biru) ----
//            Box(
//                modifier = Modifier
//                    .size(dotSize)
//                    .clip(CircleShape)
//                    .background(Color(0xFF1565C0))
//            )
//
//            // ---- DOT BAWAH (merah) ----
//            Column(
//                modifier = Modifier.fillMaxHeight(),
//                verticalArrangement = Arrangement.Bottom,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(dotSize)
//                        .clip(CircleShape)
//                        .background(Color(0xFFD32F2F))
//                )
//            }
//        }
//
//        Spacer(Modifier.width(8.dp))
//
//        // ===============================
//        // KOLUMN KANAN (TEXT)
//        // ===============================
//        Column(modifier = Modifier.weight(1f)) {
//
//            // FROM
//            Text(
//                text = from,
//                style = MaterialTheme.typography.bodyMedium.copy(
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF0F1C43)
//                )
//            )
//
//            Text(
//                text = fromDetail,
//                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // TO
//            Text(
//                text = to,
//                style = MaterialTheme.typography.bodyMedium.copy(
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF0F1C43)
//                )
//            )
//
//            Text(
//                text = toDetail,
//                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//            )
//        }
//    }
//}

//  [ALMOST 95%]
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

/* ================================================================
 * 🔹 Scrollable Tabs
 * ================================================================ */
@Composable
private fun ScrollableStatusTab(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val activeBlue = Color(0xFF002F6C)
    val borderBlue = Color(0xFF1A47B8)

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
                    .background(if (isSelected) activeBlue else Color.Transparent)
                    .border(1.dp, borderBlue, RoundedCornerShape(12.dp))
                    .clickable { onTabSelected(index) }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = if (isSelected) Color.White else Color(0xFF0F1C43),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/* ================================================================
 * 🔹 Preview Dummy
 * ================================================================ */
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewHistoryOrderDriverScreenUi() {
    val sample = HistoryOrderUiState(
        currentUser = Auth(2, "Zenitsu", "zen", "z@demo.com", "1234", "driver"),
        historyItems = listOf(
            HistoryOrderItem(
                bookingId = 1,
                bookingCode = "NB321",
                createdAt = "2025-01-04T13:45:00",
                departureTerminalId = 1,
                arrivalTerminalId = 3,
                seatsReserved = 3,
                totalPrice = 150000,
                bookingStatus = BookingStatus.DITERIMA,
                vehicleType = VehicleType.MOTOR,
                rideStatus = RideStatus.SELESAI,
                driverName = "Zenitsu"
            ),
            HistoryOrderItem(
                bookingId = 2,
                bookingCode = "NB322",
                createdAt = "2025-01-05T09:00:00",
                departureTerminalId = 2,
                arrivalTerminalId = 4,
                seatsReserved = 2,
                totalPrice = 200000,
                bookingStatus = BookingStatus.PENDING,
                vehicleType = VehicleType.MOBIL,
                rideStatus = RideStatus.DALAM_PERJALANAN,
                driverName = "Zenitsu"
            )
        )
    )
    MaterialTheme {
        HistoryOrderDriverScreenUi(uiState = sample)
    }
}
