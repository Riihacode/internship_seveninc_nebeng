package com.example.nebeng.feature_a_history_order.presentation.support_for_present.model

data class HistoryItemData(
    val code: String,              // Kode pesanan unik
    val category: String,          // "Motor" | "Mobil" | "Barang"
    val status: String,            // "Dibatalkan" | "Ubah Jadwal" | "Selesai" | "Proses" | "Menunggu" | "Jadwal"
    val fromCity: String,
    val toCity: String,
    val fromPos: String,           // misalnya: "Pos 1"
    val toPos: String,             // misalnya: "Pos 2"
    val dayDate: String,           // contoh: "Senin, 2 September 2024"
    val time: String,              // contoh: "09:00"
    val vehicle: String,           // contoh: "Mobil Avanza"
    val plate: String,             // contoh: "R 2424 MJ"
    val pax: Int,                  // jumlah penumpang
    val totalPrice: String         // contoh: "Rp120.000"
)