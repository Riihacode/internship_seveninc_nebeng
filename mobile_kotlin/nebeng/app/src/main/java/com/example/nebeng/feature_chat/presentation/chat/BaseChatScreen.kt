package com.example.nebeng.feature_chat.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nebeng.R

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BaseChatScreen() {
//    val chats = listOf(
//        ChatItem("Driver A", "Baik mba, sedang OTW", "Now", R.drawable.ic_profile_black_24, 2),
//        ChatItem("Driver B", "Sudah sampai di lokasi", "Now", R.drawable.ic_profile_black_24, 0),
//        ChatItem("Driver C", "Mohon tunggu sebentar ya", "Now", R.drawable.ic_profile_black_24, 5),
//        ChatItem("Driver D", "Terima kasih, hati-hati di jalan", "Now", R.drawable.ic_profile_black_24, 0),
//        ChatItem("Driver E", "Barang sudah saya antar", "Kemarin", R.drawable.ic_profile_black_24, 1),
//        ChatItem("Driver F", "Nanti saya kabari lagi", "Kemarin", R.drawable.ic_profile_black_24, 0),
//        ChatItem("Driver G", "Baik kak, ditunggu ya", "Kemarin", R.drawable.ic_profile_black_24, 3),
//        ChatItem("Driver H", "Bisa saya ambil jam 10?", "2 hari lalu", R.drawable.ic_profile_black_24, 0),
//        ChatItem("Driver I", "Sudah di pos 1", "2 hari lalu", R.drawable.ic_profile_black_24, 1),
//        ChatItem("Driver J", "Terima kasih banyak!", "3 hari lalu", R.drawable.ic_profile_black_24, 0)
//    )
//
//    var searchText by remember { mutableStateOf("") }
//
//    Scaffold(
//        containerColor = Color(0xFFF8F9FD)
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = 96.dp)
//        ) {
//            // ===== TITLE =====
//            Text(
//                text = "Chat",
//                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 12.dp)
//            )
//
//            // ===== SEARCH BAR =====
//            OutlinedTextField(
//                value = searchText,
//                onValueChange = { searchText = it },
//                placeholder = { Text("Cari") },
//                leadingIcon = {
//                    Icon(Icons.Default.Search, contentDescription = "Cari")
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .clip(RoundedCornerShape(50))
//                    .background(Color.White),
//                singleLine = true,
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent
//                )
//            )
//
//            Spacer(Modifier.height(12.dp))
//
//            // ===== CHAT LIST =====
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(bottom = 80.dp)
//            ) {
//                items(chats.filter {
//                    it.name.contains(searchText, ignoreCase = true)
//                }) { chat ->
//                    ChatItemView(chat)
//                }
//            }
//        }
//    }
//}
@Composable
fun BaseChatScreen(role: String = "customer") {
    val chats = if (role == "driver") {
        listOf(
            ChatItem("Customer A", "Paketnya sudah dikirim?", "Now", R.drawable.ic_profile_black_24, 1),
            ChatItem("Customer B", "Terima kasih sudah diantar!", "1 jam lalu", R.drawable.ic_profile_black_24, 0),
            ChatItem("Customer C", "Boleh saya titip kiriman lagi?", "Kemarin", R.drawable.ic_profile_black_24, 2),
        )
    } else {
        listOf(
            ChatItem("Driver A", "Baik mba, sedang OTW", "Now", R.drawable.ic_profile_black_24, 2),
            ChatItem("Driver B", "Sudah sampai di lokasi", "Now", R.drawable.ic_profile_black_24, 0),
            ChatItem("Driver C", "Mohon tunggu sebentar ya", "Now", R.drawable.ic_profile_black_24, 5),
        )
    }

    var searchText by remember { mutableStateOf("") }

    Scaffold(containerColor = Color(0xFFF8F9FD)) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 96.dp)
        ) {
            Text(
                text = if (role == "Driver") "Chat Pelanggan" else "Chat Driver",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 12.dp)
            )

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Cari") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(chats.filter {
                    it.name.contains(searchText, ignoreCase = true)
                }) { chat ->
                    ChatItemView(chat)
                }
            }
        }
    }
}


// ==== DATA CLASS ====
data class ChatItem(
    val name: String,
    val lastMessage: String,
    val time: String,
    val profileImage: Int,
    val unreadCount: Int = 0    // Jumlah pesan belum dibaca
)

// ==== ITEM VIEW ====
@Composable
fun ChatItemView(chat: ChatItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { /* TODO: open chat detail */ }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            // Profile image
            Image(
                painter = painterResource(id = chat.profileImage),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

//            // Indicator pesan belum dibaca (dot biru kecil)
//            if (chat.unreadCount > 0) {
//                Box(
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .offset(x = 4.dp, y = 4.dp)
//                        .size(18.dp)
//                        .clip(CircleShape)
//                        .background(Color(0xFF1877F2)),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = chat.unreadCount.toString(),
//                        color = Color.White,
//                        style = MaterialTheme.typography.labelSmall.copy(
//                            fontWeight = FontWeight.Bold
//                        )
//                    )
//                }
//            }
        }




        Spacer(Modifier.width(12.dp))

        // Name + last message
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = chat.lastMessage,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }

        // Time + info icon
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = chat.time,
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF1877F2))
            )
//            Icon(
//                painter = painterResource(id = R.drawable.ic_profile_black_24),
//                contentDescription = "Info",
//                tint = Color(0xFF1877F2),
//                modifier = Modifier.size(18.dp)
//            )
            // Indicator pesan belum dibaca (dot biru kecil)
            if (chat.unreadCount > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
//                        .offset(x = 4.dp, y = 4.dp)
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1877F2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chat.unreadCount.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }

    HorizontalDivider(Modifier, DividerDefaults.Thickness, color = Color(0xFFE8EAF0))
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Chat Screen Preview"
)
@Composable
fun PreviewChatScreen() {
    // Bungkus dengan tema agar warna dan typography tampil benar
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography()
    ) {
        // Gunakan Scaffold atau Surface agar background mengikuti tema
        Surface {
            BaseChatScreen()
        }
    }
}
