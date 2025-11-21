package com.example.nebeng.feature_a_homepage.presentation.screen_role.customer.nebeng_motor.page_01

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import kotlin.math.ceil

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendar(
    currentMonth: YearMonth,
    selectedDate: LocalDate?,
    onDateClick: (LocalDate) -> Unit
) {
    val firstDayOfMonth = currentMonth.atDay(1)
    val lastDayOfMonth = currentMonth.atEndOfMonth()

    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // 0 = Minggu? kita geser biar Senin awal
    val totalDays = lastDayOfMonth.dayOfMonth

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔹 Label hari (Sen — Min)
        val daysName = listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            daysName.forEach { day ->
                Text(
                    day,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF4D4D4D)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // 🔹 Render tanggal dalam grid 7 kolom
        val rows = ceil((firstDayOfWeek + totalDays) / 7f).toInt()
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var day = 1
            repeat(rows) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(7) { columnIndex ->
                        val index = it * 7 + columnIndex

                        if (index < firstDayOfWeek || day > totalDays) {
                            // 🔸 Kosong (spacer)
                            Text(
                                "",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 8.dp)
                            )
                        } else {
                            val date = currentMonth.atDay(day)
                            val isSelected = date == selectedDate

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (isSelected) Color(0xFF0F3D82) else Color.Transparent
                                    )
                                    .clickable { onDateClick(date) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day.toString(),
                                    fontSize = if (isSelected) 18.sp else 16.sp,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else Color(0xFF1B1B1B),
                                    modifier = Modifier.padding(vertical = 10.dp)
                                )
                            }
                            day++
                        }
                    }
                }
            }
        }
    }
}
