package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_04

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nebeng.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen(
    onBack: () -> Unit = {},
    onNext: (String) -> Unit = {}      // mengirim metode pembayaran terpilih
) {
    var selectedMethod by remember { mutableStateOf<String?>(null) }

    val methods = listOf(
        PaymentMethodModel("QRIS", "Pindai QR pengemudi untuk membayar", R.drawable.qris),
        PaymentMethodModel("Tunai", null, R.drawable.qris),
        PaymentMethodModel("BRI Virtual Account", null, R.drawable.qris),
        PaymentMethodModel("BCA Virtual Account", null, R.drawable.qris),
        PaymentMethodModel("Dana", null, R.drawable.qris)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD))
    ) {

        // ===== TOP BAR =====
        TopAppBar(
            title = {
                Text(
                    "Pilih Pembayaran",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF0F3D82)
            )
        )

        Spacer(Modifier.height(10.dp))

        // ===== PAYMENT METHOD LIST =====
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            items(methods) { method ->

                PaymentMethodItem(
                    method = method,
                    selected = selectedMethod == method.name,
                    onSelect = { selectedMethod = method.name }
                )

                Spacer(Modifier.height(12.dp))
            }
        }

        Spacer(Modifier.weight(1f))

        // ===== BUTTON LANJUTKAN =====
        Button(
            onClick = { selectedMethod?.let { onNext(it) } },
            enabled = selectedMethod != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0F3D82),
                disabledContainerColor = Color(0xFF98A7C3)
            )
        ) {
            Text(
                "Lanjutkan",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun PaymentMethodItem(
    method: PaymentMethodModel,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color(0xFFE3E6EB))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = method.icon),
                contentDescription = method.name,
                modifier = Modifier.size(44.dp)
            )

            Spacer(Modifier.width(14.dp))

            Column(Modifier.weight(1f)) {
                Text(method.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                method.subtitle?.let {
                    Spacer(Modifier.height(2.dp))
                    Text(it, fontSize = 12.sp, color = Color.Gray)
                }
            }

            RadioButton(
                selected = selected,
                onClick = onSelect
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewPaymentMethodScreen() {
    PaymentMethodScreen()
}
