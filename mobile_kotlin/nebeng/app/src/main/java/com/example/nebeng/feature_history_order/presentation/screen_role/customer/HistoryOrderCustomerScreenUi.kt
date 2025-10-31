package com.example.nebeng.feature_history_order.presentation.screen_role.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nebeng.feature_history_order.presentation.support_for_present.model.HistoryItemData
import com.example.nebeng.feature_history_order.presentation.HistoryOrderUiState
import com.example.nebeng.feature_history_order.presentation.history_order.CategoryChip
import com.example.nebeng.feature_history_order.presentation.history_order.StatusDropdownChip
import com.example.nebeng.feature_history_order.presentation.support_for_present.component.HistoryCard

//@Composable
//fun HistoryOrderCustomerScreenUi() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("History Order customer")
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryOrderCustomerScreenUi(state: HistoryOrderUiState) {
    // gunakan data dari state
    var selectedTab by remember { mutableStateOf(0) }
    var selectedCategory by remember { mutableStateOf("Mobil") }
    var selectedStatus by remember { mutableStateOf("Selesai") }

    val tabs = listOf("Riwayat", "Dalam Proses", "Terjadwal")
    val categories = listOf("Mobil", "Motor", "Barang")

    val allHistoryData = state.historyItems

    val statusFromTab = when (selectedTab) {
        0 -> listOf("Selesai")
        1 -> listOf("Dalam Perjalanan", "Pending")
        2 -> listOf("Dijadwalkan")
        else -> listOf("Selesai")
    }

    val filteredHistory = allHistoryData.filter {
        it.category == selectedCategory &&
                it.status in statusFromTab &&
                (selectedStatus == "Semua" || it.status == selectedStatus)
    }

    Scaffold(containerColor = Color(0xFFF8F9FD)) { padding ->
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHistoryOrderCustomerScreenUi() {
    MaterialTheme {
        HistoryOrderCustomerScreenUi(
            state = HistoryOrderUiState(
                historyItems = listOf(
                    HistoryItemData(
                        "XZH80BV",
                        "TOYOTA AVANZA VELOZ",
                        "YOG POS 2 → SOLO POS 1",
                        "Rp 90.000",
                        "Selesai",
                        "Mobil"
                    )
                )
            )
        )
    }
}
