package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PassengerRideMotorScreen(
//    onBack: () -> Unit = {},
//    onStartSelect: () -> Unit = {},
//    onEndSelect: () -> Unit = {},
//    onDateSelect: () -> Unit = {},
//    onHistoryClick: (String) -> Unit = {},
//    onNext: () -> Unit = {}
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF8F9FD))
//    ) {
//
//        // =============================================================
//        // HEADER BIRU + TOP APP BAR + INPUT CARD
//        // =============================================================
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(330.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(360.dp)
//                    .clip(RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp))
//                    .background(Color(0xFF0F3D82))
//            )
//
//            TopAppBar(
//                title = { Text("Nebeng Motor", color = Color.White, fontWeight = FontWeight.SemiBold) },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(Icons.Default.ArrowBack, null, tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
//                modifier = Modifier.padding(top = 10.dp)
//            )
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .offset(y = 55.dp)
//                    .align(Alignment.BottomCenter),
//                shape = RoundedCornerShape(18.dp),
//                elevation = CardDefaults.cardElevation(8.dp)
//            ) {
//                PassengerMotorInputSection(
//                    startLocation = "",
//                    endLocation = "",
//                    selectedDate = "",
//                    onStartChange = onStartSelect,
//                    onEndChange = onEndSelect,
//                    onDateClick = onDateSelect,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//        }
//
//        Spacer(Modifier.height(75.dp))
//
//        Text(
//            text = "Histori Alamat",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(start = 20.dp, bottom = 12.dp)
//        )
//
//        // =============================================================
//        // HANYA BAGIAN HISTORI YANG BISA DISCROLL
//        // =============================================================
//        val historyLocations = listOf(
//            "Yogyakarta · Pos 1" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 2" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 3" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 1" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 2" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 3" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 1" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 2" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 3" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 1" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 2" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 3" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 1" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 2" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
//            "Yogyakarta · Pos 3" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133"
//        )
//
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f) // area scroll fleksibel
//                .fillMaxWidth()
//        ) {
//            itemsIndexed(historyLocations) { index, (title, detail) ->
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { onHistoryClick(title) }
//                        .padding(horizontal = 20.dp, vertical = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_location_24),
//                        contentDescription = null,
//                        tint = Color(0xFF1A47B8),
//                        modifier = Modifier.size(30.dp)
//                    )
//                    Spacer(Modifier.width(12.dp))
//                    Column(Modifier.weight(1f)) {
//                        Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
//                        Text(detail, fontSize = 13.sp, color = Color.Gray)
//                    }
//                }
//
//                if (index != historyLocations.lastIndex) {
//                    Divider(
//                        Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp),
//                        color = Color(0xFFE0E0E0)
//                    )
//                }
//            }
//
//            item { Spacer(Modifier.height(24.dp)) }
//        }
//
//        // =============================================================
//        // BUTTON SELANJUTNYA — SELALU DI BAWAH SCREEN
//        // =============================================================
//        Button(
//            onClick = onNext,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp, vertical = 12.dp)
//                .height(54.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF0F3D82)
//            ),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text("Selanjutnya", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
//        }
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PassengerRideMotorScreen(
//    onBack: () -> Unit = {},
//    onStartSelect: () -> Unit = {},
//    onEndSelect: () -> Unit = {},
//    onDateSelect: () -> Unit = {},
//    onHistoryClick: (String) -> Unit = {},
//    onNext: () -> Unit = {}
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF8F9FD))
//    ) {
//
//        // ================= HEADER BIRU ==================
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(340.dp)
//                    .clip(RoundedCornerShape(bottomStart = 34.dp, bottomEnd = 34.dp))
//                    .background(Color(0xFF0F3D82))
//            )
//
//            TopAppBar(
//                title = {
//                    Text("Nebeng Motor", color = Color.White, fontWeight = FontWeight.SemiBold)
//                },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
//                modifier = Modifier.padding(top = 6.dp)
//            )
//
//            // ========= CARD INPUT =============
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .padding()
//                    .offset(y = 100.dp)    // ini yang pas agar tidak menutupi TopAppBar
//                    .align(Alignment.BottomCenter),
//                shape = RoundedCornerShape(18.dp),
//                elevation = CardDefaults.cardElevation(6.dp),
//                colors = CardDefaults.cardColors(Color.White)
//            ) {
//                PassengerMotorInputSection(
//                    modifier = Modifier.padding(18.dp),
//                    onStartChange = onStartSelect,
//                    onEndChange = onEndSelect,
//                    onDateClick = onDateSelect,
//                )
//            }
//        }
//
//        Spacer(Modifier.height(85.dp)) // ruang antara card dan daftar histori
//
//        // ================= HISTORI ALAMAT ================
//        Text(
//            text = "Histori Alamat",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(horizontal = 20.dp)
//                .padding(top =  32.dp)
//        )
//
//        Spacer(Modifier.height(10.dp))
//
//        val history = (1..10).map {
//            "Yogyakarta · Pos $it" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133"
//        }
//
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//        ) {
//            itemsIndexed(history) { index, (title, detail) ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { onHistoryClick(title) }
//                        .padding(horizontal = 20.dp, vertical = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_location_24),
//                        contentDescription = null,
//                        tint = Color(0xFF1A47B8),
//                        modifier = Modifier.size(30.dp)
//                    )
//                    Spacer(Modifier.width(12.dp))
//                    Column(Modifier.weight(1f)) {
//                        Text(title, fontWeight = FontWeight.Bold)
//                        Text(detail, color = Color.Gray, fontSize = 13.sp)
//                    }
//                }
//                if (index != history.lastIndex) {
//                    Divider(
//                        Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp),
//                        color = Color(0xFFE0E0E0)
//                    )
//                }
//            }
//        }
//
//        // ===== BUTTON SELANJUTNYA (fix ke bawah) =====
//        Button(
//            onClick = onNext,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp, vertical = 16.dp)
//                .height(52.dp),
//            colors = ButtonDefaults.buttonColors(Color(0xFF0F3D82)),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text("Selanjutnya", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
//        }
//    }
//}
//
//
//
//
////@Composable
////fun PassengerMotorInputSection(
////    startLocation: String,
////    endLocation: String,
////    selectedDate: String,
////    onStartChange: () -> Unit,
////    onEndChange: () -> Unit,
////    onDateClick: () -> Unit,
////    modifier: Modifier = Modifier
////) {
////    Column(modifier = modifier) {
////
////        Text("Lokasi Awal", fontWeight = FontWeight.SemiBold)
////        Spacer(Modifier.height(6.dp))
////
////        OutlinedTextField(
////            value = startLocation,
////            onValueChange = { },
////            placeholder = { Text("Pilih lokasi awal") },
////            readOnly = true,
////            trailingIcon = { Icon(Icons.Default.KeyboardArrowRight, null) },
////            modifier = Modifier
////                .fillMaxWidth()
////                .clickable { onStartChange() }
////        )
////
////        Spacer(Modifier.height(18.dp))
////        Text("Lokasi Tujuan", fontWeight = FontWeight.SemiBold)
////        Spacer(Modifier.height(6.dp))
////
////        OutlinedTextField(
////            value = endLocation,
////            onValueChange = { },
////            placeholder = { Text("Pilih lokasi tujuan") },
////            readOnly = true,
////            trailingIcon = { Icon(Icons.Default.KeyboardArrowRight, null) },
////            modifier = Modifier
////                .fillMaxWidth()
////                .clickable { onEndChange() }
////        )
////
////        Spacer(Modifier.height(18.dp))
////        OutlinedTextField(
////            value = selectedDate,
////            onValueChange = { },
////            placeholder = { Text("Tanggal Keberangkatan") },
////            readOnly = true,
////            leadingIcon = {
////                Icon(
////                    painter = painterResource(id = R.drawable.ic_calendar_24),
////                    contentDescription = null
////                )
////            },
////            modifier = Modifier
////                .fillMaxWidth()
////                .clickable { onDateClick() }
////        )
////    }
////}
//@Composable
//fun PassengerMotorInputSection(
//    modifier: Modifier = Modifier,
//    onStartChange: () -> Unit = {},
//    onEndChange: () -> Unit = {},
//    onDateClick: () -> Unit = {},
//) {
//    Column(modifier) {
//
//        // Card lokasi awal + tujuan
//        Card(
//            shape = RoundedCornerShape(16.dp),
//            border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
//            colors = CardDefaults.cardColors(Color.White)
//        ) {
//            Column(Modifier.padding(16.dp)) {
//
//                RideLocationRow(
//                    iconColor = Color(0xFF2ECC71),
//                    label = "Lokasi Awal",
//                    placeholder = "Pilih lokasi awal",
//                    onClick = onStartChange
//                )
//
//                RideLocationDivider() // garis penghubung dinamis
//
//                RideLocationRow(
//                    iconColor = Color(0xFFFF6E42),
//                    label = "Lokasi Tujuan",
//                    placeholder = "Pilih lokasi tujuan",
//                    onClick = onEndChange
//                )
//            }
//        }
//
//        Spacer(Modifier.height(14.dp))
//
//        // Card Tanggal Keberangkatan
//        Card(
//            shape = RoundedCornerShape(16.dp),
//            border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
//            colors = CardDefaults.cardColors(Color.White)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable(onClick = onDateClick)
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_calendar_24),
//                    contentDescription = null,
//                    tint = Color(0xFF0F3D82),
//                    modifier = Modifier.size(26.dp)
//                )
//                Spacer(Modifier.width(12.dp))
//                Text("Tanggal Keberangkatan", fontWeight = FontWeight.Medium)
//            }
//        }
//    }
//}
//
//@Composable
//fun RideLocationRow(
//    iconColor: Color,
//    label: String,
//    placeholder: String,
//    onClick: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        // ===== LABEL =====
////        Row(verticalAlignment = Alignment.CenterVertically) {
////            Canvas(
////                modifier = Modifier
////                    .size(18.dp)
////            ) {
////                drawCircle(color = iconColor)
////            }
////
////            Spacer(Modifier.width(10.dp))
////
////            Text(
////                text = label,
////                fontWeight = FontWeight.SemiBold,
////                fontSize = 16.sp,
////                color = Color(0xFF0F1C43)
////            )
////        }
//
//        Spacer(Modifier.height(8.dp))
//
//        // ===== BOX SELECTOR (KLIKABLE) =====
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(48.dp)
//                .clickable(onClick = onClick)
//                .padding(horizontal = 12.dp),
//            contentAlignment = Alignment.CenterStart
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Canvas(
//                    modifier = Modifier
//                        .size(18.dp)
//                ) {
//                    drawCircle(color = iconColor)
//                }
//
//                Spacer(Modifier.width(16.dp))
//                Text(
//                    text = placeholder,
//                    fontSize = 15.sp,
//                    color = Color.Gray,
//                    modifier = Modifier.weight(1f)
//                )
//
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = null,
//                    tint = Color.Gray
//                )
//            }
//        }
//    }
//}
//
//
//@Composable
//private fun RideLocationDivider() {
//    Divider(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 10.dp),
//        color = Color(0xFFE6E6E6),
//        thickness = 1.dp
//    )
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PassengerRideMotorScreen(
//    onBack: () -> Unit = {},
//    onStartSelect: () -> Unit = {},
//    onEndSelect: () -> Unit = {},
//    onDateSelect: () -> Unit = {},
//    onHistoryClick: (String) -> Unit = {},
//    onNext: () -> Unit = {}
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF8F9FD))
//    ) {
////        Box(
////            modifier = Modifier
////                .fillMaxWidth()
////                .height(300.dp)
////        ) {
////            Box(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(340.dp)
////                    .clip(RoundedCornerShape(bottomStart = 34.dp, bottomEnd = 34.dp))
////                    .background(Color(0xFF0F3D82))
////            )
////
////            TopAppBar(
////                title = { Text("Nebeng Motor", color = Color.White) },
////                navigationIcon = {
////                    IconButton(onClick = onBack) {
////                        Icon(Icons.Default.ArrowBack, null, tint = Color.White)
////                    }
////                },
////                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
////            )
////
////            PassengerMotorRouteCard(
////                onStartClick = onStartSelect,
////                onEndClick = onEndSelect,
////                onDateClick = onDateSelect,
////            )
////                .let {
////                    Card(
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .padding(horizontal = 20.dp)
////                            .offset(y = 90.dp)
////                            .align(Alignment.BottomCenter)
////                    ) { it() }
////                }
////        }
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(340.dp)
//                    .clip(RoundedCornerShape(bottomStart = 34.dp, bottomEnd = 34.dp))
//                    .background(Color(0xFF0F3D82))
//            )
//
//            TopAppBar(
//                title = { Text("Nebeng Motor", color = Color.White) },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
//            )
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .offset(y = 90.dp)
//                    .align(Alignment.BottomCenter),
//                shape = RoundedCornerShape(18.dp),
//                elevation = CardDefaults.cardElevation(6.dp),
//                colors = CardDefaults.cardColors(Color.White)
//            ) {
////                PassengerMotorRouteCard(
////                    onStartClick = onStartSelect,
////                    onEndClick = onEndSelect,
////                    onDateClick = onDateSelect
////                )
//                MotorRouteSelectorInputCard(
//                    startTitle = "Lokasi Awal",
//                    startDetail = "Pilih lokasi awal",
//                    endTitle = "Lokasi Tujuan",
//                    endDetail = "Pilih lokasi tujuan",
//                    onStartClick = onStartSelect,
//                    onEndClick = onEndSelect
//                )
//
//            }
//        }
//
//
//        Spacer(Modifier.height(100.dp))
//
//        Text(
//            "Histori Alamat",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(horizontal = 20.dp)
//        )
//
//        val history = (1..10).map { "Yogyakarta · Pos $it" }
//
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f)
//                .padding(top = 12.dp)
//        ) {
//            items(history) {
//                HistoryAddressItem(address = it) { onHistoryClick(it) }
//            }
//            item { Spacer(Modifier.height(8.dp)) }
//        }
//
//        Button(
//            onClick = onNext,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp)
//                .height(52.dp),
//            colors = ButtonDefaults.buttonColors(Color(0xFF0F3D82)),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text("Selanjutnya", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
//        }
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PassengerRideMotorScreen(
//    onBack: () -> Unit = {},
//    onStartSelect: () -> Unit = {},
//    onEndSelect: () -> Unit = {},
//    onDateSelect: () -> Unit = {},
//    onHistoryClick: (String) -> Unit = {},
//    onNext: () -> Unit = {}
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF8F9FD))
//    ) {
//
//        // ========= HEADER BIRU =========
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(340.dp)
//                    .clip(RoundedCornerShape(bottomStart = 34.dp, bottomEnd = 34.dp))
//                    .background(Color(0xFF0F3D82))
//            )
//
//            TopAppBar(
//                title = {
//                    Text(
//                        "Nebeng Motor",
//                        color = Color.White,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 20.sp
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(
//                            Icons.Default.ArrowBack,
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
//                modifier = Modifier.padding(top = 6.dp)
//            )
//
//            // ========= CARD INPUT =========
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .offset(y = 60.dp) // Posisi tepat agar tidak menutupi AppBar
//                    .align(Alignment.BottomCenter),
//                shape = RoundedCornerShape(22.dp),
//                elevation = CardDefaults.cardElevation(8.dp),
//                colors = CardDefaults.cardColors(Color.White)
//            ) {
//                PassengerMotorInputSection(
//                    modifier = Modifier.padding(18.dp),
//                    onStartClick = onStartSelect,
//                    onEndClick = onEndSelect,
//                    onDateClick = onDateSelect
//                )
//            }
//        }
//
//        Spacer(Modifier.height(100.dp))
//
//        // ========= LIST HISTORI ALAMAT =========
//        Text(
//            text = "Histori Alamat",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(horizontal = 20.dp)
//        )
//
//        Spacer(Modifier.height(10.dp))
//
//        val history = (1..10).map {
//            "Yogyakarta · Pos $it" to
//                    "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133"
//        }
//
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//        ) {
//            itemsIndexed(history) { index, (title, detail) ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { onHistoryClick(title) }
//                        .padding(horizontal = 20.dp, vertical = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_location_24),
//                        contentDescription = null,
//                        tint = Color(0xFF1A47B8),
//                        modifier = Modifier.size(30.dp)
//                    )
//                    Spacer(Modifier.width(12.dp))
//                    Column(Modifier.weight(1f)) {
//                        Text(title, fontWeight = FontWeight.Bold)
//                        Text(detail, color = Color.Gray, fontSize = 13.sp)
//                    }
//                }
//                if (index != history.lastIndex) {
//                    Divider(
//                        Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp),
//                        color = Color(0xFFE0E0E0)
//                    )
//                }
//            }
//        }
//
//        // ========= BUTTON BOTTOM =========
//        Button(
//            onClick = onNext,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp, vertical = 16.dp)
//                .height(52.dp),
//            colors = ButtonDefaults.buttonColors(Color(0xFF0F3D82)),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text("Selanjutnya", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerRideMotorScreen(
    onBack: () -> Unit = {},
    onStartSelect: () -> Unit = {},
    onEndSelect: () -> Unit = {},
    onDateSelect: () -> Unit = {},
    onHistoryClick: (String) -> Unit = {},
    onNext: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD))
    ) {

        // ================= HEADER BIRU ==================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            // Background header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .clip(RoundedCornerShape(bottomStart = 34.dp, bottomEnd = 34.dp))
                    .background(Color(0xFF0F3D82))
            )

            TopAppBar(
                title = {
                    Text(
                        "Nebeng Motor",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.padding(top = 6.dp)
            )

            // ========= CARD INPUT (ROUTE + DATE) =============
//            PassengerRouteCard(
//                onStartClick = onStartSelect,
//                onEndClick = onEndSelect,
//                onDateClick = onDateSelect
//            ).modifier(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp)
//                        .offset(y = 110.dp) // posisi sempurna agar tidak menutup TopBar
//                        .align(Alignment.BottomCenter)
//                )
            // ========= CARD INPUT (ROUTE + DATE) =============
            PassengerRouteCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)   // karena dipanggil di dalam Box
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = 90.dp),             // geser turun dari AppBar
                onStartClick = onStartSelect,
                onEndClick  = onEndSelect,
                onDateClick = onDateSelect
            )

        }

        Spacer(Modifier.height(125.dp)) // beri ruang setelah kartu input

        // ================= HISTORI ALAMAT ================
        Text(
            text = "Histori Alamat",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 20.dp)
        )

        Spacer(Modifier.height(12.dp))

        val history = (1..10).map {
            "Yogyakarta · Pos $it" to "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133"
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            itemsIndexed(history) { index, (title, detail) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onHistoryClick(title) }
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location_24),
                        contentDescription = null,
                        tint = Color(0xFF1A47B8),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(detail, color = Color.Gray, fontSize = 13.sp)
                    }
                }
                if (index != history.lastIndex) {
                    Divider(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }

        // ===== BUTTON SELANJUTNYA — FIX DI BAWAH =====
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF0F3D82))
        ) {
            Text("Selanjutnya", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}


@Composable
fun PassengerRouteCard(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
    onDateClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, Color(0xFFE6E6E6))
//        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(20.dp)) {

            // =====================
            // LOKASI AWAL & TUJUAN
            // =====================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),   // <- kunci tinggi dinamis
                verticalAlignment = Alignment.Top
            ) {

                // KIRI -> RAIL
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 6.dp, bottom = 6.dp)
                        .width(38.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // DOT ATAS (HIJAU)
                    RailDot(color = Color(0xFF2ECC71))

                    // VERTICAL LINE
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(2.dp)
                            .background(Color(0xFFBCC6CE))
                    )

                    // DOT BAWAH (ORANYE)
                    RailDot(color = Color(0xFFFF6E42))
                }

                Spacer(Modifier.width(12.dp))

                // KANAN -> LABEL & INPUT
                Column(modifier = Modifier.weight(1f)) {

                    // Lokasi Awal
                    Text("Lokasi Aw/alskdn;lanmdkansldja;ndnalnd;naslkndkndadah;adad;dha;dhak.lhjadkahjdkadj.adhakdj.had.jabdh.adh.jah.adadal", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
//                    LocationBox("Pilih lokasi awal", onStartClick)

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp),
                        color = Color(0xFFE0E0E0)
                    )

                    // Lokasi Tujuan
                    Text("Lokasi Tujuan", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
//                    LocationBox("Pilih lokasi tujuan", onEndClick)
                }
            }

            Spacer(Modifier.height(18.dp))

            // Tanggal Keberangkatan
            Card(
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDateClick() }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar_24),
                        contentDescription = null,
                        tint = Color(0xFF0F3D82),
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(Modifier.width(14.dp))
                    Text("Tanggal Keberangkatan", fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
private fun RailDot(color: Color) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .background(color, CircleShape)
            .shadow(8.dp, CircleShape)
    )
}

@Composable
private fun LocationBox(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text("aksdbashaldsjblabsdugialdgluashladsadadhaduaddiuhadsdasiahdludahlauiadslidaiudadadsudsadsduadladuadshaduisd", fontSize = 15.sp, color = Color.Gray)
    }
}



@Composable
fun PassengerMotorInputSection(
    startLabel: String = "Lokasi Awal",
    endLabel: String = "Lokasi Tujuan",
    dateLabel: String = "Tanggal Keberangkatan",
    onStartClick: () -> Unit = {},
    onEndClick: () -> Unit = {},
    onDateClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier) {

        // ===================== CARD LOKASI AWAL & TUJUAN =====================
        Card(
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(18.dp)) {

                // Lokasi Awal
                LocationRow(
                    color = Color(0xFF2ECC71),
                    label = startLabel,
                    onClick = onStartClick
                )

                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(0.84f), // lebih pendek dari card
                    color = Color(0xFFE6E6E6)
                )

                // Lokasi Tujuan
                LocationRow(
                    color = Color(0xFFFF6E42),
                    label = endLabel,
                    onClick = onEndClick
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        // ===================== CARD TANGGAL =====================
        Card(
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDateClick)
        ) {
            Row(
                modifier = Modifier.padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_24), // icon kalender
                    contentDescription = null,
                    tint = Color(0xFF0F3D82),
                    modifier = Modifier.size(26.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = dateLabel,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2C3E50)
                )
            }
        }
    }
}
@Composable
private fun LocationRow(
    color: Color,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(Modifier.width(14.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0F1C43)
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFF8A8A8A)
        )
    }
}


//@Composable
//fun PassengerMotorRouteCard(
//    onStartClick: () -> Unit = {},
//    onEndClick: () -> Unit = {},
//    onDateClick: () -> Unit = {}
//) {
//    Card(
//        shape = RoundedCornerShape(18.dp),
//        colors = CardDefaults.cardColors(Color.White),
//        elevation = CardDefaults.cardElevation(0.dp),
//        border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(Modifier.padding(20.dp)) {
//
//            // ===================== RUTE (LOKASI AWAL & TUJUAN) =====================
//            var startY by remember { mutableStateOf(0f) }
//            var endY by remember { mutableStateOf(0f) }
//            var topY by remember { mutableStateOf(0f) }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .onGloballyPositioned { topY = it.boundsInWindow().top }
//            ) {
//                Row(Modifier.fillMaxWidth()) {
//
//                    Box(
//                        modifier = Modifier
//                            .width(36.dp)
//                            .fillMaxHeight()
//                    )
//
//                    Spacer(Modifier.width(10.dp))
//
//                    Column(Modifier.weight(1f)) {
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier
//                                .onGloballyPositioned { startY = it.boundsInWindow().center.y }
//                                .clickable(onClick = onStartClick)
//                        ) {
//                            Text("Lokasi Awal", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
//                        }
//
//                        OutlinedLocationBox("Pilih lokasi awal", onStartClick)
//
//                        Spacer(Modifier.height(16.dp))
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier
//                                .onGloballyPositioned { endY = it.boundsInWindow().center.y }
//                                .clickable(onClick = onEndClick)
//                        ) {
//                            Text("Lokasi Tujuan", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
//                        }
//
//                        OutlinedLocationBox("Pilih lokasi tujuan", onEndClick)
//                    }
//                }
//
//                Canvas(modifier = Modifier.matchParentSize()) {
//                    if (startY != 0f && endY != 0f) {
//                        val centerX = with(LocalDensity.current) { 36.dp.toPx() } / 2
//                        val radius = with(LocalDensity.current) { 8.dp.toPx() }
//                        val y1 = startY - topY
//                        val y2 = endY - topY
//
//                        drawLine(
//                            color = Color(0xFFC7D2DA),
//                            start = Offset(centerX, y1 + radius),
//                            end = Offset(centerX, y2 - radius),
//                            strokeWidth = 3f
//                        )
//
//                        drawCircle(Color(0xFF2ECC71), radius, Offset(centerX, y1))
//                        drawCircle(Color(0xFFFF6E42), radius, Offset(centerX, y2))
//                    }
//                }
//            }
//
//            Spacer(Modifier.height(20.dp))
//
//            // ===================== TANGGAL KEBERANGKATAN =====================
//            Card(
//                shape = RoundedCornerShape(14.dp),
//                colors = CardDefaults.cardColors(Color.White),
//                elevation = CardDefaults.cardElevation(0.dp),
//                border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable(onClick = onDateClick)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_calendar_24),
//                        contentDescription = null,
//                        tint = Color(0xFF0F3D82)
//                    )
//                    Spacer(Modifier.width(12.dp))
//                    Text("Tanggal Keberangkatan", fontWeight = FontWeight.Medium)
//                }
//            }
//        }
//    }
//}
@Composable
fun PassengerMotorRouteCard(
    onStartClick: () -> Unit = {},
    onEndClick: () -> Unit = {},
    onDateClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(20.dp)) {

            var startY by remember { mutableStateOf(0f) }
            var endY by remember { mutableStateOf(0f) }
            var topY by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { topY = it.boundsInWindow().top }
            ) {
                Row(Modifier.fillMaxWidth()) {

                    // Ruang Rail
                    Box(
                        modifier = Modifier
                            .width(34.dp)
                            .fillMaxHeight()
                    )

                    Spacer(Modifier.width(10.dp))

                    Column(Modifier.weight(1f)) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .onGloballyPositioned { startY = it.boundsInWindow().center.y }
                                .clickable { onStartClick() }
                        ) {
                            Text("Lokasi Awal", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        }

                        OutlinedLocationField("Pilih lokasi awal", onStartClick)

                        Spacer(Modifier.height(18.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .onGloballyPositioned { endY = it.boundsInWindow().center.y }
                                .clickable { onEndClick() }
                        ) {
                            Text("Lokasi Tujuan", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        }

                        OutlinedLocationField("Pilih lokasi tujuan", onEndClick)
                    }
                }

//                Canvas(modifier = Modifier.matchParentSize()) {
//                    if (startY > 0 && endY > 0) {
//                        val density = LocalDensity.current
//                        val centerX = with(density) { 34.dp.toPx() } / 2
//                        val radius = with(density) { 9.dp.toPx() }
//                        val y1 = startY - topY
//                        val y2 = endY - topY
//
//                        drawLine(
//                            color = Color(0xFFC7D2DA),
//                            start = Offset(centerX, y1 + radius),
//                            end = Offset(centerX, y2 - radius),
//                            strokeWidth = 3f
//                        )
//                        drawCircle(Color(0xFF2ECC71), radius, Offset(centerX, y1))
//                        drawCircle(Color(0xFFFF6E42), radius, Offset(centerX, y2))
//                    }
//                }
                val density = LocalDensity.current     // <-- boleh di sini

                Canvas(modifier = Modifier.matchParentSize()) {
                    if (startY > 0 && endY > 0) {

                        val centerX = with(density) { 34.dp.toPx() } / 2
                        val radius = with(density) { 9.dp.toPx() }
                        val y1 = startY - topY
                        val y2 = endY - topY

                        drawLine(
                            color = Color(0xFFC7D2DA),
                            start = Offset(centerX, y1 + radius),
                            end = Offset(centerX, y2 - radius),
                            strokeWidth = 3f
                        )

                        drawCircle(Color(0xFF2ECC71), radius, Offset(centerX, y1))
                        drawCircle(Color(0xFFFF6E42), radius, Offset(centerX, y2))
                    }
                }

            }

            Spacer(Modifier.height(22.dp))

            Card(
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDateClick() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar_24),
                        contentDescription = null,
                        tint = Color(0xFF0F3D82),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text("Tanggal Keberangkatan", fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun OutlinedLocationField(
    placeholder: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("aksdakdsbjasiudhabdasbajlsbdasbsadjldasladsladsbadsaddbsdsajbldakjabsdoab dabodgadadadoagodgaodadgagsdgaoddsag", fontSize = 15.sp, color = Color.Gray, modifier = Modifier.weight(1f))
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
        }
    }
}


@Composable
private fun OutlinedLocationBox(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, Color(0xFFE6E6E6), RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text, fontSize = 15.sp, color = Color.Gray)
    }
}

@Composable
fun MotorRouteSelectorInputCard(
    startTitle: String,
    startDetail: String,
    endTitle: String,
    endDetail: String,
    onStartClick: () -> Unit = {},
    onEndClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color(0xFFE6E6E6))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            var startCenterY by remember { mutableStateOf(0f) }
            var endCenterY by remember { mutableStateOf(0f) }
            var topRef by remember { mutableStateOf(0f) }
            val density = LocalDensity.current
            val dotSize = 14.dp

            // ==== Bagian layout kanan (judul & detail) ====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { topRef = it.boundsInWindow().top }
            ) {

                // Area kosong kiri untuk rail
                Box(modifier = Modifier.width(34.dp))

                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {

                    // Lokasi Awal
                    Text(
                        startTitle,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = Color(0xFF0F1C43),
                        modifier = Modifier.onGloballyPositioned {
                            startCenterY = it.boundsInWindow().center.y
                        }
                    )
                    Text(
                        startDetail,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .clickable { onStartClick() }   // klik keseluruhan text
                    )

                    Spacer(Modifier.height(12.dp))

                    // Lokasi Tujuan
                    Text(
                        endTitle,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = Color(0xFF0F1C43),
                        modifier = Modifier.onGloballyPositioned {
                            endCenterY = it.boundsInWindow().center.y
                        }
                    )
                    Text(
                        endDetail,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .clickable { onEndClick() }
                    )
                }
            }

            // ==== Canvas Rail Dinamis ====
            Canvas(modifier = Modifier.matchParentSize()) {
                if (startCenterY != 0f && endCenterY != 0f) {

                    val pxRadius = with(density) { dotSize.toPx() / 2 }
                    val centerX = with(density) { 34.dp.toPx() } / 2
                    val y1 = startCenterY - topRef
                    val y2 = endCenterY - topRef

                    drawLine(
                        color = Color(0xFFCFD8DC),
                        start = Offset(centerX, y1 + pxRadius + 2),
                        end = Offset(centerX, y2 - pxRadius - 2),
                        strokeWidth = with(density) { 2.dp.toPx() }
                    )

                    drawCircle(Color(0xFF1565C0), pxRadius, Offset(centerX, y1))
                    drawCircle(Color(0xFFD32F2F), pxRadius, Offset(centerX, y2))
                }
            }
        }
    }
}


//@Composable
//fun PassengerMotorRouteCard(
//    onStartClick: () -> Unit = {},
//    onEndClick: () -> Unit = {},
//    onDateClick: () -> Unit = {}
//) {
//    Card(
//        shape = RoundedCornerShape(18.dp),
//        colors = CardDefaults.cardColors(Color.White),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(modifier = Modifier.padding(18.dp)) {
//
//            // ======================================================
//            // LOKASI AWAL & TUJUAN (RAIL DINAMIS)
//            // ======================================================
//            RouteSelector(
//                startLabel = "Lokasi Awal",
//                endLabel = "Lokasi Tujuan",
//                onStartClick = onStartClick,
//                onEndClick = onEndClick
//            )
//
//            Spacer(Modifier.height(16.dp))
//
//            // ======================================================
//            // TANGGAL KEBERANGKATAN (CARD TANPA ELEVATION)
//            // ======================================================
//            Card(
//                shape = RoundedCornerShape(14.dp),
//                colors = CardDefaults.cardColors(Color.White),
//                border = BorderStroke(1.dp, Color(0xFFDFE4EA)),
//                elevation = CardDefaults.cardElevation(0.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable { onDateClick() }
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(14.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_calendar_24),
//                        contentDescription = null,
//                        tint = Color(0xFF003F8A),
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(Modifier.width(12.dp))
//                    Text(
//                        "Tanggal Keberangkatan",
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 15.sp,
//                        color = Color(0xFF003F8A)
//                    )
//                }
//            }
//        }
//    }
//}


@Composable
private fun RouteSelector(
    startLabel: String,
    endLabel: String,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit
) {
    val iconSize = 26.dp
    val railColor = Color(0xFFD0D6DD)
    val density = LocalDensity.current

    var startCenterY by remember { mutableStateOf(0f) }
    var endCenterY by remember { mutableStateOf(0f) }
    var topY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { topY = it.boundsInWindow().top }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // AREA RAIL ICONS
            Box(
                modifier = Modifier
                    .width(34.dp)
                    .fillMaxHeight()
            )

            Spacer(Modifier.width(8.dp))

            // AREA LABELS
            Column(modifier = Modifier.weight(1f)) {

                // Lokasi awal
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.onGloballyPositioned { startCenterY = it.boundsInWindow().center.y }
                        .clickable { onStartClick() }
                ) {
                    Text(startLabel, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                }

                Spacer(Modifier.height(12.dp))

                // Lokasi tujuan
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.onGloballyPositioned { endCenterY = it.boundsInWindow().center.y }
                        .clickable { onEndClick() }
                ) {
                    Text(endLabel, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                }
            }
        }

        // RAIL CANVAS
        Canvas(modifier = Modifier.matchParentSize()) {
            if (startCenterY == 0f || endCenterY == 0f) return@Canvas

            val startY = startCenterY - topY
            val endY = endCenterY - topY
            val centerX = with(density) { 34.dp.toPx() } / 2

            // garis
            drawLine(
                color = railColor,
                start = Offset(centerX, startY + with(density) { iconSize.toPx() / 2 }),
                end = Offset(centerX, endY - with(density) { iconSize.toPx() / 2 }),
                strokeWidth = with(density) { 2.dp.toPx() }
            )

            // icon atas (hijau)
            drawCircle(
                color = Color(0xFF27AE60),
                radius = with(density) { iconSize.toPx() / 2 },
                center = Offset(centerX, startY)
            )

            // icon bawah (oranye)
            drawCircle(
                color = Color(0xFFE67E22),
                radius = with(density) { iconSize.toPx() / 2 },
                center = Offset(centerX, endY)
            )
        }
    }
}



@Composable
fun HistoryAddressItem(address: String, onClick: () -> Unit) {
    val GrayLabel = Color(0xFF6B7280)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location_24),
                contentDescription = null,
                tint = Color(0xFF1E4DA2)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = address,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Patehan, Kecamatan Kraton, Kota Yogyakarta 55133",
            color = GrayLabel,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 34.dp)
        )
        Spacer(Modifier.height(6.dp))
        Divider(Modifier.fillMaxWidth(), color = Color(0xFFE8E8E8))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewPassengerRideMotorScreen() {
    PassengerRideMotorScreen (
//        historyAddresses = listOf(
//            "Yogyakarta - Pos 1",
//            "Yogyakarta - Pos 2",
//            "Yogyakarta - Pos 3"
//        )
    )
}
