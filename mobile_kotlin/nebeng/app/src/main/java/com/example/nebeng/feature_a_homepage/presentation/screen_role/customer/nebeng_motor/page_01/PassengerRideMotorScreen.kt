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
                .height(320.dp)
        ) {
            // Background header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
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
                    .offset(y = 40.dp),             // geser turun dari AppBar
                onStartClick = onStartSelect,
                onEndClick  = onEndSelect,
                onDateClick = onDateSelect
            )

        }

        Spacer(Modifier.height(60.dp)) // beri ruang setelah kartu input

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
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(IntrinsicSize.Min),   // <- kunci tinggi dinamis
//                verticalAlignment = Alignment.Top
//            ) {
//
//                // KIRI -> RAIL
//                Column(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .padding(top = 6.dp, bottom = 6.dp)
//                        .width(38.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    // DOT ATAS (HIJAU)
//                    RailDot(color = Color(0xFF2ECC71))
//
//                    // VERTICAL LINE
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                            .width(2.dp)
//                            .background(Color(0xFFBCC6CE))
//                    )
//
//                    // DOT BAWAH (ORANYE)
//                    RailDot(color = Color(0xFFFF6E42))
//                }
//
//                Spacer(Modifier.width(12.dp))
//
//                // KANAN -> LABEL & INPUT
//                Column(modifier = Modifier.weight(1f)) {
//
//                    // Lokasi Awal
//                    Text("Lokasi Awal", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
//                    LocationBox("Pilih lokasi awal", onStartClick)
//
//                    Divider(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 14.dp),
//                        color = Color(0xFFE0E0E0)
//                    )
//
//                    // Lokasi Tujuan
//                    Text("Lokasi Tujuan", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
//                }
//            }
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(0.dp),
                border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp)) {

                    // =====================
                    // LOKASI AWAL & TUJUAN
                    // =====================
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),   // ← kunci agar line vertikal menyesuaikan
                        verticalAlignment = Alignment.Top
                    ) {

                        // KIRI → RAIL + DOT
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(top = 4.dp, bottom = 4.dp)
                                .width(40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            RailDot(color = Color(0xFF2ECC71)) // dot hijau lokasi awal

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .width(2.dp)
                                    .background(Color(0xFFBCC6CE)) // garis vertical
                            )

                            RailDot(color = Color(0xFFFF6E42)) // dot oranye lokasi tujuan
                        }

                        Spacer(Modifier.width(12.dp))

                        // KANAN → LABEL & INPUT
                        Column(modifier = Modifier.weight(1f)) {

                            // Lokasi Awal
                            Text("Lokasi Awal", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                            LocationBox("Pilih lokasi awal", onStartClick)

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 14.dp),
                                color = Color(0xFFE0E0E0)
                            )

                            // Lokasi Tujuan
                            Text("Lokasi Tujuan", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                            LocationBox("Pilih lokasi tujuan", onEndClick)
                        }
                    }
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

//@Composable
//private fun LocationBox(text: String, onClick: () -> Unit) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(48.dp)
////            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
//            .clickable(onClick = onClick)
//            .padding(horizontal = 12.dp),
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Text("aksdbashaldsjblabsdugialdgluashladsadadhaduaddiuhadsdasiahdludahlauiadslidaiudadadsudsadsduadladuadshaduikjmopknpnpnpn;jn;h;uiophb;jkhuigipgbip;uguiopyguipgbip;ugipfgip;yuv;iyufipyugvio;ufyipfipugipipyughbpuigipyuguipgpuigbuipgpuigipug8ipfgyuipgsd", fontSize = 15.sp, color = Color.Gray)
//    }
//}

@Composable
fun LocationBox(
    placeholder: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp), // vertical lebih fleksibel
        contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Text otomatis multiline
            Text(
                text = placeholder,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.padding(start = 6.dp, top = 2.dp)
            )
        }
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
