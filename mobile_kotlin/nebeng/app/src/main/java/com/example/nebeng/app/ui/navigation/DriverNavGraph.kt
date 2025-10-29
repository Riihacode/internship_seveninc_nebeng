package com.example.nebeng.app.ui.navigation

import androidx.navigation.NavController
import com.example.nebeng.R

object DriverNavGraph {
    fun setup(navController: NavController) {
        val currentGraphId = runCatching { navController.graph.id }.getOrNull()
        if (currentGraphId != R.navigation.driver_nav_graph) {
            navController.setGraph(R.navigation.driver_nav_graph)
        }
    }
}