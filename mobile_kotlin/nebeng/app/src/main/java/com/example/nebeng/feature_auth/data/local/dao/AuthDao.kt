package com.example.nebeng.feature_auth.data.local.dao

import androidx.room.*
import com.example.nebeng.feature_auth.data.local.entity.AuthEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<AuthEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: AuthEntity)

    @Update
    suspend fun updateUser(user: AuthEntity)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUsers(id: Int)

    @Query("SELECT * FROM users WHERE username= :username AND password= :password LIMIT 1")
    suspend fun login(username: String, password: String): AuthEntity?
}