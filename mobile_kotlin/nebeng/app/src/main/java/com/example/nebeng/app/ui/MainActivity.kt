package com.example.nebeng.app.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nebeng.R
import com.example.nebeng.databinding.ActivityMainBinding
import com.example.nebeng.feature_auth.presentation.navigation.AuthNavGraph
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // ===== FIX WHITE SPACE AT STATUS BAR =====
//        // 1. Biar Compose bisa gambar sampai area status bar
//        WindowCompat.setDecorFitsSystemWindows(window, true)
//
//        // 2. Jadikan status bar transparan
//        window.statusBarColor = Color.TRANSPARENT
//
//        // 3. Pastikan ikon status bar (jam, sinyal, baterai) berwarna putih
//        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false


        // ===== STATUS BAR SETUP (warna ungu, bukan transparan) =====
        // 1️⃣ Kembalikan agar sistem tidak menggambar di balik status bar
//        WindowCompat.setDecorFitsSystemWindows(window, true)
//
//        // 2️⃣ Atur warna status bar jadi ungu
//        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_700)
//
//        // 3️⃣ Pastikan ikon status bar tetap putih
//        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window.statusBarColor = Color.TRANSPARENT

        // ⚙️ Izinkan Compose menggambar sampai status bar,
//        // tapi status bar tetap berwarna ungu
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_700)
//
//        // ⚙️ Pastikan ikon status bar tetap putih
//        WindowInsetsControllerCompat(window, window.decorView)
//            .isAppearanceLightStatusBars = false

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }
}