package com.example.nebeng.feature_homepage.presentation.screen_role.driver

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nebeng.feature_homepage.presentation.HomepageViewModel

//@Composable
//fun HomepageDriverScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Homepage Driver")
//    }
//}
@Composable
fun HomepageDriverScreenUi(
    navController: NavHostController,
    viewModel: HomepageViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Driver Homepage — Coming Soon", color = Color.Gray)
    }
}
