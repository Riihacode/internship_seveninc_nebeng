package com.example.nebeng.feature_history_order.presentation.support_for_present.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusChip(status: String) {
    val (bgColor, textColor) = when (status.lowercase()) {
        "selesai" -> Color(0xFFE7F6EC) to Color(0xFF1B8E5F)
        "pending" -> Color(0xFFFFF6E6) to Color(0xFFE27A00)
        "dalam perjalanan" -> Color(0xFFEAF3FF) to Color(0xFF0066CC)
        "dibatalkan" -> Color(0xFFFDEAEA) to Color(0xFFD83A3A)
        else -> Color(0xFFE9EDF5) to Color(0xFF1E2E4F)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            style = MaterialTheme.typography.labelSmall.copy(color = textColor)
        )
    }
}