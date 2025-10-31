package com.example.nebeng.feature_history_order.presentation.history_order

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryOrderScreen() {
//    val tabs = listOf("Riwayat", "Dalam Proses", "Terjadwal")
    var selectedTab by remember { mutableStateOf(0) }
    var selectedCategory by remember { mutableStateOf("Mobil") }
    var selectedStatus by remember { mutableStateOf("Selesai") }

    val tabs = listOf("Riwayat", "Dalam Proses", "Terjadwal")
    val categories = listOf("Mobil", "Motor", "Barang")

    // Dummy data semua kategori
    val allHistoryData = listOf(
        HistoryItemData("XZH80BV", "TOYOTA AVANZA VELOZ", "YOG POS 2 → SOLO POS 1", "Rp 90.000", "Selesai", "Mobil"),
        HistoryItemData("GH12UW2", "NEBENG BARANG", "YOG POS 2 → SOLO POS 1", "Rp 50.000", "Selesai", "Barang"),
        HistoryItemData("ZH12M3T", "YAMAHA NMAX", "YOG POS 2 → SOLO POS 1", "Rp 110.000", "Selesai", "Motor"),
        HistoryItemData("HY34BN2", "HONDA SCOOPY", "YOG POS 1 → MAGELANG", "Rp 60.000", "Dalam Perjalanan", "Motor"),
        HistoryItemData("BB32KPL", "GRANMAX BOX", "YOG POS 3 → KLATEN", "Rp 70.000", "Pending", "Barang")
    )

    val statusFromTab = when(selectedTab) {
        0   -> listOf("Selesai")
        1   -> listOf("Dalam Perjalanan", "Pending")
        2   -> listOf("Dijadwalkan")
        else -> listOf("Selesai")
    }

    // Filter data berdasarkan kategori & status
    val filteredHistory = allHistoryData.filter {
        it.category == selectedCategory &&
                it.status in statusFromTab &&
                (selectedStatus == "Semua" || it.status == selectedStatus)
    }


    Scaffold(
        containerColor = Color(0xFFF8F9FD)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Aktivitas",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(12.dp))
            }

            item {
                // Tabs
                PrimaryTabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = {
                                Text(
                                    text = title,
                                    color = if (selectedTab == index) Color(0xFF5C3BDB) else Color.DarkGray,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
            }

            // ===== FILTER BAR (Kategori + Status) =====
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    categories.forEach { cat ->
                        CategoryChip(
                            label = cat,
                            selected = selectedCategory == cat,
                            onSelected = { selectedCategory = cat }
                        )
                    }

                    StatusDropdownChip(
                        selectedStatus = selectedStatus,
                        onStatusSelected = { selectedStatus = it }
                    )
                }
                Spacer(Modifier.height(16.dp))
            }

            // ===== HISTORY ITEMS =====
            if (filteredHistory.isEmpty()) {
                item {
                    Text(
                        text = "Tidak ada riwayat untuk kategori $selectedCategory ($selectedStatus)",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }
            } else {
                items(filteredHistory) { item ->
                    HistoryCard(item)
                }
            }

            item { Spacer(Modifier.height(32.dp)) }
        }
    }
}

@Composable
fun HistoryCard(item: HistoryItemData) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Kode Pemesanan ${item.code}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                StatusChip(item.status)
            }

            Spacer(Modifier.height(12.dp))

            // Main Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = item.route,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }

                Text(
                    text = item.price,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1877F2)
                    )
                )
            }
        }
    }
}

@Composable
fun StatusChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE7F6EC))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFF1B8E5F))
        )
    }
}

// ======== COMPONENTS ========
@Composable
fun CategoryChip(label: String, selected: Boolean, onSelected: () -> Unit) {
    val bgColor = if (selected) Color(0xFFDDE7FF) else Color(0xFFF0F2F7)
    val textColor = if (selected) Color(0xFF0F1C43) else Color(0xFF4A4A4A)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable { onSelected() }
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusDropdownChip(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
//    var selectedStatus by remember { mutableStateOf("Selesai") }

    val statuses = listOf("Semua", "Pending", "Dalam Perjalanan", "Selesai", "Dibatalkan")

    // warna utama dan teks dinamis
    val isActive = selectedStatus == "Semua"
    val chipColor = if (isActive) Color(0xFF1877F2) else Color(0xFFE9EDF5)
    val textColor = if (isActive) Color.White else Color(0xFF1E2E4F)

    Box {
        // ===== CHIP BUTTON =====
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(chipColor)
                .clickable { expanded = !expanded }
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedStatus,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(18.dp)
            )
        }

        // ===== DROPDOWN MENU =====
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
                .clip(RoundedCornerShape(8.dp))
        ) {
            statuses.forEach { status ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = status,
                            color = if (status == selectedStatus) Color(0xFF1877F2) else Color.Black
//                            style = MaterialTheme.typography.bodyMedium.copy(
//                                fontWeight = if (status == selectedStatus) FontWeight.SemiBold else FontWeight.Normal
//                            )
                        )
                    },
                    onClick = {
                        onStatusSelected(status)
                        expanded = false
                    }
                )
            }
        }
    }
}

// ==== Data Model + Card ====
data class HistoryItemData(
    val code: String,
    val title: String,
    val route: String,
    val price: String,
    val status: String,
    val category: String
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "History Order Screen Preview"
)
@Composable
fun PreviewHistoryOrderScreen() {
    // Bungkus dengan tema agar warna dan typography tampil benar
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography()
    ) {
        // Gunakan Scaffold atau Surface agar background mengikuti tema
        Surface {
            HistoryOrderScreen()
        }
    }
}


