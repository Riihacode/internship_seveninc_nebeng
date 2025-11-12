package com.example.nebeng.feature_a_history_order.presentation.support_for_present.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R
import com.example.nebeng.feature_a_history_order.presentation.support_for_present.model.HistoryItemData


//@Composable
//fun HistoryItemCard(
//    data: HistoryItemData,
//    onChangeSchedule: () -> Unit
//) {
//    val shape = RoundedCornerShape(12.dp)
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = shape,
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Column(Modifier.padding(14.dp)) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = data.title,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFF1A47B8)
//                )
//                StatusPill(data.status)
//            }
//
//            Spacer(Modifier.height(8.dp))
//            Text(text = data.route, color = Color(0xFF2C2F36), fontWeight = FontWeight.Bold)
//            Spacer(Modifier.height(4.dp))
//            Text(text = data.price, color = Color(0xFF6B7280), fontSize = 13.sp)
//        }
//    }
//}
//@Composable
//fun HistoryItemCard(
//    data: HistoryItemData,
//    onChangeSchedule: (HistoryItemData) -> Unit
//) {
//    val shape = RoundedCornerShape(12.dp)
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = shape,
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Column(Modifier.padding(14.dp)) {
//
//            // ===== HEADER =====
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    val icon = when (data.category) {
//                        "Motor" -> Icons.Default.TwoWheeler
//                        "Mobil" -> Icons.Default.DirectionsCar
//                        else -> Icons.Default.Inventory2
//                    }
//                    Icon(
//                        icon, null, tint = Color(0xFF1A47B8),
//                        modifier = Modifier
//                            .size(18.dp)
//                            .clip(CircleShape)
//                    )
//                    Spacer(Modifier.width(8.dp))
//                    Text(
//                        "Nebeng ${data.category}",
//                        fontSize = 13.sp,
//                        color = Color(0xFF1A47B8),
//                        fontWeight = FontWeight.SemiBold
//                    )
//                }
//
//                when (data.status) {
//                    "Ubah Jadwal" -> Text(
//                        "Ubah Jadwal",
//                        color = Color(0xFFE27A00),
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        textDecoration = TextDecoration.Underline,
//                        modifier = Modifier.clickable { onChangeSchedule(data) }
//                    )
//                    "Dibatalkan" -> StatusPill("Dibatalkan", Color(0xFFFFE2E2), Color(0xFFD11A2A))
//                    "Selesai" -> StatusPill("Selesai", Color(0xFFE8F5EC), Color(0xFF1B8E5F))
//                    "Proses" -> StatusPill("Proses", Color(0xFFE9E7FF), Color(0xFF5C3BDB))
//                }
//            }
//
//            Spacer(Modifier.height(8.dp))
//
//            // ===== RUTE =====
//            Row(verticalAlignment = Alignment.Bottom) {
//                Text(
//                    buildAnnotatedString {
//                        append(data.fromCity)
//                        append(" ")
//                        withStyle(SpanStyle(color = Color(0xFF1A47B8))) {
//                            append(data.fromPos)
//                        }
//                    },
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF2C2F36)
//                )
//
//                Spacer(Modifier.width(6.dp))
//                Text(
//                    text = "→",
//                    color = Color(0xFF2C2F36),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 24.sp,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//
//                Spacer(Modifier.width(6.dp))
//                Text(
//                    text = buildAnnotatedString {
//                        append(data.toCity)
//                        append(" ")
//                        withStyle(SpanStyle(color = Color(0xFF1A47B8))) {
//                            append(data.toPos)
//                        }
//                    },
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF2C2F36)
//                )
//            }
//
//            Spacer(Modifier.height(8.dp))
//
//            // ===== WAKTU =====
//            Text("${data.dayDate}  •  ${data.time}", color = Color(0xFF6B7280), fontSize = 12.sp)
//
//            // ===== KENDARAAN =====
//            Spacer(Modifier.height(4.dp))
//            Text(
//                text = "${data.vehicle} • ${data.plate}",
//                color = Color(0xFF6B7280),
//                fontSize = 12.sp
//            )
//
//            Spacer(Modifier.height(12.dp))
//            Divider(color = Color(0xFFE5E7EB))
//
//            // ===== FOOTER =====
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_person_grey_24),
//                        contentDescription = null,
//                        tint = Color(0xFF6B7280),
//                        modifier = Modifier
//                            .size(20.dp)
//                            .padding(end = 4.dp)
//                    )
//
//                    Text(
//                        text = "${data.pax} Orang  ",
//                        color = Color(0xFF6B7280),
//                        fontSize = 12.sp
//                    )
//                }
//            }
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 8.dp),
//                horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text("Total Harga: ", color = Color(0xFF6B7280), fontSize = 13.sp)
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(
//                        data.totalPrice,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF0F2E6C)
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun StatusPill(text: String, bg: Color, fg: Color) {
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(12.dp))
//            .background(bg)
//            .padding(horizontal = 10.dp, vertical = 4.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text, color = fg, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
//    }
//}
//
//
//@Composable
//fun StatusPill(status: String) {
//    val (bg, fg) = when (status) {
//        "Selesai" -> Color(0xFFE8F5EC) to Color(0xFF1B8E5F)
//        "Dibatalkan" -> Color(0xFFFFE2E2) to Color(0xFFD11A2A)
//        "Dalam Perjalanan" -> Color(0xFFE9E7FF) to Color(0xFF5C3BDB)
//        else -> Color(0xFFF2F2F2) to Color(0xFF6B7280)
//    }
//
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(12.dp))
//            .background(bg)
//            .padding(horizontal = 10.dp, vertical = 4.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(status, color = fg, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
//    }
//}