package com.example.nebeng.app.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nebeng.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userName: String = "Nadya Amalya",
    points: Int = 0,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF8F9FD))
    ) {
        // ===== HEADER BLUE SECTION =====
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(220.dp)
//                .background(
//                    Brush.verticalGradient(
//                        colors = listOf(Color(0xFF1877F2), Color(0xFF3B8DFE))
//                    )
//                )
////                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)) // ✅ biar tetap aman dari notch
////                .windowInsetsPadding(WindowInsets.statusBars) // ✅ ini ganti statusBarsPadding()
////                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()) // ✅ ini langsung dorong isi teks ke bawah
//
//        )
//        Box(
//            modifier = Modifier
//                .fillMaxSize() // = match_parent
//                .background(
//                    Brush.verticalGradient(
//                        listOf(Color(0xFF1877F2), Color(0xFF3B8DFE))
//                    )
//                )
//                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()) // agar konten tidak ketimpa jam
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 24.dp, vertical = 32.dp)
//            ) {
//                Text(
//                    text = "Selamat Siang,",
//                    color = Color.White,
//                    style = MaterialTheme.typography.bodyLarge
//                )
//                Text(
//                    text = userName,
//                    color = Color.White,
//                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
//                )
//            }
//
//            IconButton(
//                onClick = { /* TODO: notifikasi */ },
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(top = 32.dp, end = 16.dp)
//                    .size(32.dp)
//                    .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Notifikasi",
//                    tint = Color.White
//                )
//            }
//        }
        // ===== HEADER BLUE SECTION =====
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(220.dp)
//                .background(
//                    Brush.verticalGradient(
//                        colors = listOf(Color(0xFF1877F2), Color(0xFF3B8DFE))
//                    )
//                )
//                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp, vertical = 32.dp)
//            ) {
//                Text(
//                    text = "Selamat Siang,",
//                    color = Color.White,
//                    style = MaterialTheme.typography.bodyLarge
//                )
//                Text(
//                    text = userName,
//                    color = Color.White,
//                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
//                )
//            }
//
//            IconButton(
//                onClick = { /* TODO: notifikasi */ },
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(top = 32.dp, end = 16.dp)
//                    .size(32.dp)
//                    .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Notifikasi",
//                    tint = Color.White
//                )
//            }
//        }

//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFF6200EE)) // warna ungu status bar
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(220.dp)
//                    .background(
//                        Brush.verticalGradient(
//                            listOf(Color(0xFF1877F2), Color(0xFF3B8DFE))
//                        )
//                    )
//                    .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 24.dp, vertical = 32.dp)
//                ) {
//                    Text(
//                        text = "Selamat Siang,",
//                        color = Color.White,
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                    Text(
//                        text = userName,
//                        color = Color.White,
//                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
//                    )
//                }
//
//                IconButton(
//                    onClick = { /* TODO: notifikasi */ },
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .padding(top = 32.dp, end = 16.dp)
//                        .size(32.dp)
//                        .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Notifications,
//                        contentDescription = "Notifikasi",
//                        tint = Color.White
//                    )
//                }
//            }
//        }

//        // === Header: Ungu + Gradasi Biru ===
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(240.dp)
//        ) {
//            // Lapisan ungu (menyatu dengan status bar)
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp) // tinggi status bar rata-rata
//                    .background(Color(0xFF6200EE))
//            )
//
//            // Lapisan biru (mulai tepat dari atas layar, menutupi sedikit ungu)
//            Box(
//                modifier = Modifier
//                    .matchParentSize()
//                    .background(
//                        Brush.verticalGradient(
//                            listOf(
//                                Color(0xFF1877F2),
//                                Color(0xFF3B8DFE)
//                            )
//                        )
//                    )
//                    .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 24.dp, vertical = 32.dp)
//                ) {
//                    Text(
//                        text = "Selamat Siang,",
//                        color = Color.White,
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                    Text(
//                        text = userName,
//                        color = Color.White,
//                        style = MaterialTheme.typography.headlineSmall.copy(
//                            fontWeight = FontWeight.Bold
//                        )
//                    )
//                }
//
//                IconButton(
//                    onClick = { /* TODO: notifikasi */ },
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .padding(top = 32.dp, end = 16.dp)
//                        .size(32.dp)
//                        .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Notifications,
//                        contentDescription = "Notifikasi",
//                        tint = Color.White
//                    )
//                }
//            }
//        }
        // HEADER BLUE SECTION (tanpa padding status bar)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF1877F2), Color(0xFF3B8DFE))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Text("Selamat Siang,", color = Color.White, style = MaterialTheme.typography.bodyLarge)
                Text(userName, color = Color.White,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
            }

            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 32.dp, end = 16.dp)
                    .size(32.dp)
                    .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
            ) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifikasi", tint = Color.White)
            }
        }


        // ===== CARD POIN =====
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .offset(y = (-40).dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "Poin",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Poin saya", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = points.toString(),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // ===== MENU ICONS =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
//            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally)
            horizontalArrangement = Arrangement.SpaceEvenly, // ✅ jarak antar ikon sama besar
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuItem(R.drawable.ic_motor, "Motor")
            MenuItem(R.drawable.ic_mobil, "Mobil")
            MenuItem(R.drawable.ic_barang, "Barang")
            MenuItem(R.drawable.ic_transport, "Barang\n(Transportasi Umum)")
        }

        Spacer(Modifier.height(24.dp))

        // ===== BANNER =====
        Image(
            painter = painterResource(id = R.drawable.banner_nebeng),
            contentDescription = "Banner Nebeng",
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(24.dp))

        // ===== TUJUAN POPULER =====
        Text(
            text = "Tujuan Populer",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Text(
            text = "Berikut adalah kota-kota yang populer!",
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listOf("Jakarta" to R.drawable.img_jakarta, "Bandung" to R.drawable.img_jakarta)) { (city, image) ->
                PopularCityCard(city = city, imageRes = image)
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun MenuItem(iconRes: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFEAF2FF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(label, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
    }
}

@Composable
fun PopularCityCard(city: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = city,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color(0x99000000))
                    )
                )
        )
        Text(
            text = city,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}
