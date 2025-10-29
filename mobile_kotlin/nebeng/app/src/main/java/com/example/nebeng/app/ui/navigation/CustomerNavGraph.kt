package com.example.nebeng.app.ui.navigation

import androidx.navigation.NavController
import com.example.nebeng.R

object CustomerNavGraph {
    fun setup(navController: NavController) {
        val currentGraphId = runCatching { navController.graph.id }.getOrNull()
        if (currentGraphId != R.navigation.customer_nav_graph) {
            navController.setGraph(R.navigation.customer_nav_graph)
        }
    }
}