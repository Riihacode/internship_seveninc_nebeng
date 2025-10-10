package com.example.nebeng.app.ui

import android.app.Application
import com.example.nebeng.core.database.AppDatabase
import com.example.nebeng.feature_auth.data.local.entity.AuthEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class NebengApp : Application()
// [Ini cuman untuk testing awal ]
//{
//    @Inject lateinit var database: AppDatabase
//
//    override fun onCreate() {
//        super.onCreate()
//
//        // Tamabahkan user dummy di background thread
//        CoroutineScope(Dispatchers.IO).launch {
//            val existing = database.authDao().login("admin", "1234")
//            if(existing == null) {
//                database.authDao().insertUser(
//                    AuthEntity(
//                        id = 1,
//                        username = "admin",
//                        password = "1234",
//                        role = "admin"
//                    )
//                )
//            }
//        }
//    }
//}