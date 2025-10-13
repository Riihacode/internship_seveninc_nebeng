package com.example.nebeng.feature_auth.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.nebeng.R
import com.example.nebeng.app.ui.MainActivity
import com.example.nebeng.core.session.UserPreferencesRepository
import com.example.nebeng.feature_auth.presentation.navigation.AuthNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    @Inject lateinit var userPrefsRepo: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_auth)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        lifecycleScope.launch {
            val isLoggedIn = userPrefsRepo.isLoggedInFlow.first()
            if (isLoggedIn) {
                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                finish()
            } else {
                setContent{
                    Surface(color = MaterialTheme.colorScheme.background) {
                        AuthNavGraph(userPrefsRepo = userPrefsRepo)
                    }
                }
            }
        }
    }
}