package com.example.nebeng.feature_history_order.presentation.screen_role.customer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.nebeng.feature_history_order.presentation.HistoryOrderUiState
import com.example.nebeng.feature_history_order.presentation.support_for_present.component.HistoryItemCard
import com.example.nebeng.feature_history_order.presentation.support_for_present.model.HistoryItemData


//@Composable
//fun HistoryOrderCustomerScreenUi() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("History Order customer")
//    }
//}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HistoryOrderCustomerScreenUi(state: HistoryOrderUiState) {
//    // gunakan data dari state
//    var selectedTab by remember { mutableStateOf(0) }
//    var selectedCategory by remember { mutableStateOf("Mobil") }
//    var selectedStatus by remember { mutableStateOf("Selesai") }
//
//    val tabs = listOf("Riwayat", "Dalam Proses", "Terjadwal")
//    val categories = listOf("Mobil", "Motor", "Barang")
//
//    val allHistoryData = state.historyItems
//
//    val statusFromTab = when (selectedTab) {
//        0 -> listOf("Selesai")
//        1 -> listOf("Dalam Perjalanan", "Pending")
//        2 -> listOf("Dijadwalkan")
//        else -> listOf("Selesai")
//    }
//
//    val filteredHistory = allHistoryData.filter {
//        it.category == selectedCategory &&
//                it.status in statusFromTab &&
//                (selectedStatus == "Semua" || it.status == selectedStatus)
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
//            item {
//                Spacer(Modifier.height(16.dp))
//                Text(
//                    text = "Aktivitas",
//                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
//                )
//                Spacer(Modifier.height(12.dp))
//            }
//
//            item {
//                PrimaryTabRow(selectedTabIndex = selectedTab) {
//                    tabs.forEachIndexed { index, title ->
//                        Tab(
//                            selected = selectedTab == index,
//                            onClick = { selectedTab = index },
//                            text = {
//                                Text(
//                                    text = title,
//                                    color = if (selectedTab == index) Color(0xFF5C3BDB) else Color.DarkGray,
//                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
//                                )
//                            }
//                        )
//                    }
//                }
//                Spacer(Modifier.height(12.dp))
//            }
//
//            item {
////                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
////                    categories.forEach { cat ->
////                        CategoryChip(
////                            label = cat,
////                            selected = selectedCategory == cat,
////                            onSelected = { selectedCategory = cat }
////                        )
////                    }
////                    StatusDropdownChip(
////                        selectedStatus = selectedStatus,
////                        onStatusSelected = { selectedStatus = it }
////                    )
////                }
//                ScrollableStatusTab(
//                    tabs = tabs,
////                    selectedTab = selectedTab,
////                    selectedTab = StatusDropdownChip(
////                        selectedStatus = selectedStatus,
////                        onStatusSelected = { selectedStatus = it }
////                    ),
//                    selectedTab = selectedTab,
//                    onTabSelected = { selectedTab = it },
//                    selectedStatus = selectedStatus,
//                    onStatusSelected = { selectedStatus = it }
//                )
//
//                Spacer(Modifier.height(16.dp))
//            }
//
//            if (filteredHistory.isEmpty()) {
//                item {
//                    Text(
//                        text = "Tidak ada riwayat untuk kategori $selectedCategory ($selectedStatus)",
//                        color = Color.Gray,
//                        style = MaterialTheme.typography.bodyMedium,
//                        modifier = Modifier.padding(top = 24.dp)
//                    )
//                }
//            } else {
//                items(filteredHistory) { item ->
//                    HistoryCard(item)
//                }
//            }
//
//            item { Spacer(Modifier.height(32.dp)) }
//        }
//    }
//}
//
//@Composable
//private fun ScrollableStatusTab(
//    tabs: List<String>,
//    selectedTab: Int,
//    onTabSelected: (Int) -> Unit,
//    selectedStatus: String,
//    onStatusSelected: (String) -> Unit
//) {
//    val activeBlue = Color(0xFF002F6C)      // 🔹 biru gelap khas profesional
//    val borderBlue = Color(0xFF1A47B8)      // 🔹 sedikit lebih terang untuk outline
//
//    // Scorrable tab di kiri
//    LazyRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
//        verticalAlignment =  Alignment.CenterVertically
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
//        // Status dropdown di kanan
//        item {
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(
////                        if (isSelected) activeBlue else Color.Transparent
//                        Color.Transparent
//                    )
//                    .border(
//                        width = 1.dp,
//                        color = borderBlue,
//                        shape = RoundedCornerShape(12.dp)
//                    )
////                    .clickable { onTabSelected(index) }
//                    .padding(horizontal = 20.dp, vertical = 10.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                StatusDropdownChip(
//                    selectedStatus = selectedStatus,
//                    onStatusSelected = onStatusSelected
//                )
//            }
//        }
//    }
//
//    Spacer(Modifier.width(8.dp))
//
//
//
//
//}
//
//
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////private fun StatusDropdownChip(
////    selectedStatus: String,
////    onStatusSelected: (String) -> Unit
////) {
////    var expanded by remember { mutableStateOf(false) }
//////    var selectedStatus by remember { mutableStateOf("Selesai") }
////
////    val statuses = listOf("Semua", "Pending", "Dalam Perjalanan", "Selesai", "Dibatalkan")
////
////    // warna utama dan teks dinamis
////    val isActive = selectedStatus == "Semua"
////    val chipColor = if (isActive) Color(0xFF1877F2) else Color(0xFFE9EDF5)
////    val textColor = if (isActive) Color.White else Color(0xFF1E2E4F)
////
////    Box {
////        // ===== CHIP BUTTON =====
////        Row(
////            modifier = Modifier
////                .clip(RoundedCornerShape(12.dp))
////                .background(chipColor)
////                .clickable { expanded = !expanded }
////                .padding(horizontal = 16.dp, vertical = 10.dp),
////            verticalAlignment = Alignment.CenterVertically
////        ) {
////            Text(
////                text = selectedStatus,
////                color = textColor,
////                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
////            )
////            Icon(
////                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
////                contentDescription = null,
////                tint = textColor,
////                modifier = Modifier.size(18.dp)
////            )
////        }
////
////        // ===== DROPDOWN MENU =====
////        DropdownMenu(
////            expanded = expanded,
////            onDismissRequest = { expanded = false },
////            modifier = Modifier
////                .background(Color.White)
////                .clip(RoundedCornerShape(8.dp))
////        ) {
////            statuses.forEach { status ->
////                DropdownMenuItem(
////                    text = {
////                        Text(
////                            text = status,
////                            color = if (status == selectedStatus) Color(0xFF1877F2) else Color.Black
//////                            style = MaterialTheme.typography.bodyMedium.copy(
//////                                fontWeight = if (status == selectedStatus) FontWeight.SemiBold else FontWeight.Normal
//////                            )
////                        )
////                    },
////                    onClick = {
////                        onStatusSelected(status)
////                        expanded = false
////                    }
////                )
////            }
////        }
////    }
////}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun StatusDropdownChip(
//    selectedStatus: String,
//    onStatusSelected: (String) -> Unit
//) {
//    var expanded by remember { mutableStateOf(false) }
//    val statuses = listOf("Semua", "Pending", "Dalam Perjalanan", "Selesai", "Dibatalkan")
//
//    val isActive = selectedStatus != "Semua"
//    val chipColor = if (isActive) Color(0xFF002F6C) else Color(0xFFF5F7FB)
//    val textColor = if (isActive) Color.White else Color(0xFF0F1C43)
//
//    Row(
//        modifier = Modifier
//            .clip(RoundedCornerShape(12.dp))
//            .background(chipColor)
//            .clickable { expanded = !expanded }
//            .padding(horizontal = 16.dp, vertical = 10.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            imageVector = Icons.Default.KeyboardArrowDown,
//            contentDescription = null,
//            tint = textColor,
//            modifier = Modifier.size(18.dp)
//        )
//        Spacer(Modifier.width(6.dp))
//        Text(
//            text = selectedStatus,
//            color = textColor,
//            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
//        )
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier
//                .background(Color.White)
//                .clip(RoundedCornerShape(8.dp))
//        ) {
//            statuses.forEach { status ->
//                DropdownMenuItem(
//                    text = {
//                        Text(
//                            text = status,
//                            color = if (status == selectedStatus) Color(0xFF002F6C) else Color.Black
//                        )
//                    },
//                    onClick = {
//                        onStatusSelected(status)
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}
//
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewHistoryOrderCustomerScreenUi() {
//    MaterialTheme {
//        HistoryOrderCustomerScreenUi(
//            state = HistoryOrderUiState(
//                historyItems = listOf(
//                    HistoryItemData(
//                        "XZH80BV",
//                        "TOYOTA AVANZA VELOZ",
//                        "YOG POS 2 → SOLO POS 1",
//                        "Rp 90.000",
//                        "Selesai",
//                        "Mobil"
//                    )
//                )
//            )
//        )
//    }
//}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryOrderCustomerScreenUi(
//    orders: List<OrderCardData>,
    uiState: HistoryOrderUiState,
    onBack: () -> Unit = {},
    onChangeSchedule: (HistoryItemData) -> Unit = {},
) {
    var selectedTab by remember { mutableStateOf(0) }              // 0=Riwayat, 1=Dalam Proses, 2=Jadwal
    var selectedCategory by remember { mutableStateOf("Semua") }   // Semua / Motor / Mobil / Barang
    var selectedStatus by remember { mutableStateOf("Semua Status") }

    val blue900 = Color(0xFF0F2E6C)
    val blue500 = Color(0xFF1A47B8)
    val bg = Color(0xFFF8F9FD)

    Scaffold(
        containerColor = bg,
        topBar = {
            TopAppBar(
                title = { Text("Pesanan", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // ===== Tabs (Riwayat, Dalam Proses, Jadwal) =====
            // 🔹 TAB BAR PALING ATAS (RIWAYAT / DALAM PROSES / JADWAL)
            HistoryTopTabs(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(Modifier.height(8.dp))

            // ===== Filter chips (scrollable) =====
            CategoryChipsRow(
                categories = listOf("Semua", "Motor", "Mobil", "Barang", "Barang Transportasi Umum"),
                selectedCategory = selectedCategory,
                selectedStatus = selectedStatus,
                onCategorySelected = { selectedCategory = it },
                onStatusSelected = { selectedStatus = it },
                activeColor = blue900,
                borderColor = blue500
            )

            Spacer(Modifier.height(8.dp))

            // ===== List =====
//            val filtered = orders.filter { order ->
//                (selectedCategory == "Semua" || order.category == selectedCategory) &&
//                        when (selectedTab) {
//                            0 -> order.status in listOf("Dibatalkan", "Selesai")
//                            1 -> order.status in listOf("Proses", "Menunggu")
//                            2 -> order.status == "Jadwal"
//                            else -> true
//                        }
//            }

            // ✅ Filtering data
            val filtered = uiState.historyItems.filter { order ->
                val matchCategory = selectedCategory == "Semua" || order.category == selectedCategory
                val matchStatus = selectedStatus == "Semua Status" || order.status.equals(selectedStatus, ignoreCase = true)
                val matchTab = when (selectedTab) {
                    0 -> order.status in listOf("Dibatalkan", "Selesai")
                    1 -> order.status in listOf("Proses", "Menunggu", "Dalam Perjalanan")
                    2 -> order.status == "Jadwal"
                    else -> true
                }
                matchCategory && matchStatus && matchTab
            }

//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                items(filtered) { order ->
//                    OrderCard(
//                        data = order,
//                        onChangeSchedule = { onChangeSchedule(order) }
//                    )
//                }
//
//                item { Spacer(Modifier.height(12.dp)) }
//            }
//            // === List ===
//            when {
//                uiState.isLoading -> Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(color = blue500)
//                }
//
//                uiState.errorMessage != null -> Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        uiState.errorMessage,
//                        color = Color.Red,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//
//                filtered.isEmpty() -> Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        "Tidak ada pesanan.",
//                        color = Color.Gray,
//                        fontSize = 14.sp
//                    )
//                }
//
//                else -> LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 16.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    items(filtered) { order ->
//                        HistoryItemCard(
//                            data = order,
//                            onChangeSchedule = { onChangeSchedule(order) }
//                        )
//                    }
//                }
//            }
            // ===== Content area =====
            when {
                uiState.isLoading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = blue500)
                }

                uiState.errorMessage != null -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(uiState.errorMessage ?: "", color = Color.Red, fontWeight = FontWeight.Medium)
                }

                filtered.isEmpty() -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tidak ada pesanan.", color = Color.Gray, fontSize = 14.sp)
                }

                else -> LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(filtered) { order ->
                        HistoryItemCard(
                            data = order,
                            onChangeSchedule = { onChangeSchedule(order) }
                        )
                    }
                }
            }
        }
    }
}

/* ---------------------------- Chips row ---------------------------- */

@Composable
fun CategoryChipsRow(
    categories: List<String>,
    selectedCategory: String,
    selectedStatus: String,
    onCategorySelected: (String) -> Unit,
    onStatusSelected: (String) -> Unit,
    activeColor: Color,
    borderColor: Color
) {
    var expanded by remember { mutableStateOf(false) }
    val statuses = listOf("Semua Status", "Pending", "Dalam Perjalanan", "Selesai", "Dibatalkan")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { cat ->
            val isSelected = selectedCategory == cat

            if (cat == "Semua") {
                Box {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) activeColor else Color.Transparent)
                            .border(1.dp, borderColor, RoundedCornerShape(14.dp))
                            .clickable { expanded = !expanded }
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = selectedStatus,
                                color = if (isSelected) Color.White else activeColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = if (isSelected) Color.White else activeColor,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        statuses.forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status) },
                                onClick = {
                                    onStatusSelected(status)
                                    expanded = false
                                    onCategorySelected(cat)
                                }
                            )
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) activeColor else Color.Transparent)
                        .border(1.dp, borderColor, RoundedCornerShape(14.dp))
                        .clickable { onCategorySelected(cat) }
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = cat,
                        color = if (isSelected) Color.White else activeColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

/* ---------------------------- Order card ---------------------------- */

@Composable
private fun OrderCard(
    data: OrderCardData,
    onChangeSchedule: () -> Unit
) {
    val shape = RoundedCornerShape(12.dp)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(Modifier.padding(14.dp)) {

            // header: layanan + aksi/status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val icon = when (data.category) {
                        "Motor" -> Icons.Default.TwoWheeler
                        "Mobil" -> Icons.Default.DirectionsCar
                        else -> Icons.Default.Inventory2
                    }
                    Icon(
                        icon, null, tint = Color(0xFF1A47B8),
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Nebeng ${data.category}",
                        fontSize = 13.sp,
                        color = Color(0xFF1A47B8),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                when (data.status) {
                    "Ubah Jadwal" -> Text(
                        "Ubah Jadwal",
                        color = Color(0xFFE27A00),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { onChangeSchedule() }
                    )
                    "Dibatalkan" -> StatusPill("Dibatalkan", Color(0xFFFFE2E2), Color(0xFFD11A2A))
                    "Selesai" -> StatusPill("Selesai", Color(0xFFE8F5EC), Color(0xFF1B8E5F))
                    "Proses" -> StatusPill("Proses", Color(0xFFE9E7FF), Color(0xFF5C3BDB))
                }
            }

            Spacer(Modifier.height(8.dp))

            // rute
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    buildAnnotatedString {
                        append(data.fromCity)
                        append(" ")
                        withStyle(SpanStyle(color = Color(0xFF1A47B8) )) {
                            append(data.fromPos)
                        }
                    },
//                    text = data.fromCity + " ${data.fromPos}",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C2F36)
                )

                Spacer(Modifier.width(6.dp))
                Text(
                    text = "→",
                    color = Color(0xFF2C2F36),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
//                    padding = padding(8.dp)
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(Modifier.width(6.dp))
                Text(
                    buildAnnotatedString {
                        append(data.toCity)
                        append(" ")
                        withStyle(SpanStyle(color = Color(0xFF1A47B8) )) {
                            append(data.toPos)
                        }
                    },
//                    text = data.toCity + " ${data.toPos}",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C2F36)
                )
            }

            Spacer(Modifier.height(8.dp))
            // tanggal jam
            Text("${data.dayDate}  •  ${data.time}", color = Color(0xFF6B7280), fontSize = 12.sp)

            // kendaraan
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${data.vehicle} • ${data.plate}",
                color = Color(0xFF6B7280),
                fontSize = 12.sp
            )

            Spacer(Modifier.height(12.dp))
            Divider(color = Color(0xFFE5E7EB))

            // footer: total harga dan pax
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person_grey_24),
                        contentDescription = null,
                        tint =Color(0xFF6B7280),
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 4.dp)
                    )

                    Text(
                        text = "${data.pax} Orang  ",
                        color = Color(0xFF6B7280),
                        fontSize = 12.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Total Harga: ", color = Color(0xFF6B7280), fontSize = 13.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text("${data.pax} Orang  ", color = Color(0xFF6B7280), fontSize = 12.sp)
                    Text(
                        data.totalPrice,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F2E6C)
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusPill(text: String, bg: Color, fg: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = fg, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistoryTopTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val activeBlue = Color(0xFF002F6C)
    val inactiveGray = Color(0xFF6B7280)
    val tabs = listOf("Riwayat", "Dalam Proses", "Jadwal Pesanan")

    PrimaryTabRow(
        selectedTabIndex = selectedTab,
        containerColor = Color.White,
        indicator = {}, // Nonaktifkan indikator bawaan
        divider = {
            Divider(
                color = activeBlue.copy(alpha = 0.15f),
                thickness = 1.dp
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedTab == index
            Tab(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                modifier = Modifier.height(64.dp), // ✅ Tinggi tetap untuk semua tab
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight() // isi penuh area Tab
                            .padding(top = 6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Box teks agar tinggi seragam (misal 2 baris maksimal)
                        Box(
                            modifier = Modifier
                                .height(40.dp) // ✅ tinggi teks seragam
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (isSelected) activeBlue else inactiveGray,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 18.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )
                        }

                        // Garis bawah
                        if (isSelected) {
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .height(3.dp)
                                    .width(60.dp)
                                    .background(
                                        color = activeBlue,
                                        shape = RoundedCornerShape(2.dp)
                                    )
                            )
                        }
                    }
                }
            )
        }
    }
}

/* ---------------------------- UI model (khusus layar ini) ---------------------------- */
data class OrderCardData(
    val category: String,          // "Motor" | "Mobil" | "Barang"
    val status: String,            // "Dibatalkan" | "Ubah Jadwal" | "Selesai" | "Proses" | "Menunggu" | "Jadwal"
    val fromCity: String,
    val toCity: String,
    val fromPos: String,           // "Yogyakarta Pos 1"
    val toPos: String,             // "Purwokerto Pos 1"
    val dayDate: String,           // "Senin, 2 September 2024"
    val time: String,              // "09:00"
    val vehicle: String,           // "Mobil Avanza"
    val plate: String,             // "R 2424 MJ"
    val pax: Int,
    val totalPrice: String         // "Rp120.000"
)

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun PreviewHistoryOrderCustomerUi() {
//    val demo = listOf(
//        OrderCardData("Mobil","Dibatalkan","Yogyakarta","Purwokerto","Pos 1","Pos 1",
//            "Senin, 2 September 2024","09:00","Mobil Avanza","R 2424 MJ",2,"Rp120.000"),
//        OrderCardData("Mobil","Ubah Jadwal","Yogyakarta","Purwokerto","Pos 1","Pos 1",
//            "Kamis, 4 September 2024","09:00","Mobil Avanza","R 2424 MJ",2,"Rp120.000"),
//        OrderCardData("Motor","Selesai","Yogyakarta","Malang","Pos 1","Pos 2",
//            "Senin, 1 September 2024","13:00","Motor Beat","AB 2010 KA",1,"Rp 90.000")
//    )
//    MaterialTheme { HistoryOrderCustomerScreenUi(orders = demo) }
//}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun PreviewHistoryOrderCustomerUi() {
//    val demoState = HistoryOrderUiState(
//        historyItems = listOf(
//            HistoryItemData("C001", "Nebeng Mobil", "Yogyakarta → Purwokerto", "Rp120.000", "Selesai", "Mobil"),
//            HistoryItemData("C002", "Nebeng Motor", "Solo → Semarang", "Rp90.000", "Dalam Perjalanan", "Motor"),
//            HistoryItemData(
//                "C003",
//                "Nebeng Barang",
//                "Yogya → Purwokerto",
//                "Rp150.000",
//                "Dibatalkan",
//                "Barang"
//            )
//        )
//    )
//    MaterialTheme { HistoryOrderCustomerScreenUi(uiState = demoState) }
//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHistoryCustomer() {
    val sample = HistoryOrderUiState(
        historyItems = listOf(
            HistoryItemData("1", "Mobil", "Selesai", "Yogyakarta", "Purwokerto", "Pos 1", "Pos 2", "Senin, 2 Sept 2024", "09:00", "Avanza", "R 2424 MJ", 2, "Rp120.000"),
            HistoryItemData("2", "Motor", "Proses", "Solo", "Semarang", "Pos 1", "Pos 2", "Selasa, 3 Sept 2024", "13:00", "Beat", "AB 2010 KA", 1, "Rp90.000"),
            HistoryItemData("3", "Barang", "Ubah Jadwal", "Yogyakarta", "Magelang", "Pos 3", "Pos 4", "Rabu, 4 Sept 2024", "08:00", "Pickup", "B 1234 XY", 1, "Rp150.000")
        )
    )
    MaterialTheme {
        HistoryOrderCustomerScreenUi(uiState = sample)
    }
}
