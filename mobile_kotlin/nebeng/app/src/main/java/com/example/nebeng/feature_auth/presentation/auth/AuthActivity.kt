package com.example.nebeng.feature_auth.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.lifecycleScope
import com.example.nebeng.app.ui.MainActivity
import com.example.nebeng.app.ui.RoleCache
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.feature_auth.presentation.navigation.AuthNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var userPrefsRepo: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Langsung tampilkan layar Compose agar tidak blank
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                AuthNavGraph(userPrefsRepo = userPrefsRepo)
            }
        }

        // Jalankan pengecekan login di background
        lifecycleScope.launch(Dispatchers.IO) {
            val isLoggedIn = userPrefsRepo.isLoggedInFlow.firstOrNull() ?: false
            val role = userPrefsRepo.userTypeFlow.firstOrNull()

            if (isLoggedIn && !role.isNullOrEmpty()) {
                // Simpan ke cache agar MainActivity langsung tahu role
                RoleCache.role = role
                RoleCache.isLoggedIn = true

                withContext(Dispatchers.Main) {
                    startActivity(
                        Intent(this@AuthActivity, MainActivity::class.java).apply {
//                            Intent.setFlags =
//                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                    )
                    finish()
                }
            }
        }
    }
}