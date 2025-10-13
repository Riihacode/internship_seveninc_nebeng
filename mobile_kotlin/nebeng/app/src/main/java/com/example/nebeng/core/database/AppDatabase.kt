package com.example.nebeng.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nebeng.feature_auth.data.local.dao.AuthDao
import com.example.nebeng.feature_auth.data.local.entity.AuthEntity

@Database(
    entities = [AuthEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun authDao(): AuthDao
}