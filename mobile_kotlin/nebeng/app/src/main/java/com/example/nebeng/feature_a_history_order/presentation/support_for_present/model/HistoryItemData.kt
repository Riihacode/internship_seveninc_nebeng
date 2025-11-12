package com.example.nebeng.feature_a_history_order.presentation.support_for_present.model

import com.example.nebeng.core.utils.RideStatus

data class HistoryItemData(
    val id: String,
    val category: String,       // contoh: "Motor", "Mobil", "Barang"
    val status: RideStatus?,         // contoh: "Selesai", "Pending", "Dibatalkan"
    val fromCity: String,       // nama kota asal (atau ID jika belum tersedia)
    val toCity: String,         // nama kota tujuan (atau ID jika belum tersedia)
    val fromTerminal: String,   // nama terminal asal (opsional)
    val toTerminal: String,     // nama terminal tujuan (opsional)
    val dayDate: String,        // contoh: "Senin, 12 Mei 2025"
    val time: String,           // contoh: "09:30"
    val vehicle: String,        // contoh: "Avanza", "Beat", "Pickup"
    val plate: String,          // contoh: "AB 1234 CD" atau nama driver sementara
    val seats: Int,             // jumlah kursi dipesan
    val totalPrice: String      // contoh: "Rp120.000"
)