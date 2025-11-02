package com.example.nebeng.feature_a_history_order.presentation.support_for_present.component

//@Composable
//fun HistoryCard(
//    item: HistoryItemData,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(4.dp),
//        modifier = modifier.fillMaxWidth()
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            // Header
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Kode Pemesanan ${item.code}",
//                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//                )
//                StatusChip(item.status)
//            }
//
//            Spacer(Modifier.height(12.dp))
//
//            // Main Info
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column {
//                    Text(
//                        text = item.title,
//                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
//                    )
//                    Text(
//                        text = item.route,
//                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
//                    )
//                }
//
//                Text(
//                    text = item.price,
//                    style = MaterialTheme.typography.bodyMedium.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF1877F2)
//                    )
//                )
//            }
//        }
//    }
//}