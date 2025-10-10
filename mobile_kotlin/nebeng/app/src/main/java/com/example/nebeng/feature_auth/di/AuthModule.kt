package com.example.nebeng.feature_auth.di

import com.example.nebeng.core.session.UserPreferencesRepository
import com.example.nebeng.feature_auth.data.local.dao.AuthDao
import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_auth.data.repository.AuthRepositoryImpl
import com.example.nebeng.feature_auth.domain.usecase.CreateAuthUseCase
import com.example.nebeng.feature_auth.domain.usecase.DeleteAuthUseCase
import com.example.nebeng.feature_auth.domain.usecase.GetAllAuthUseCase
import com.example.nebeng.feature_auth.domain.usecase.LoginUseCase
import com.example.nebeng.feature_auth.domain.usecase.LogoutUseCase
import com.example.nebeng.feature_auth.domain.usecase.UpdateAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        dao: AuthDao
    ): AuthRepository {
        // Contoh palai versi lokal dulu, nanti bisa ditambah / diganti api kalau udah siap
        return AuthRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(
        repository: AuthRepository,
        userPrefsRepo: UserPreferencesRepository
    ) = AuthUseCases(
        getAllAuth  = GetAllAuthUseCase(repository),
        createAuth  = CreateAuthUseCase(repository),
        updateAuth  = UpdateAuthUseCase(repository),
        deleteAuth  = DeleteAuthUseCase(repository),
        loginAuth   = LoginUseCase(repository),
        logout      = LogoutUseCase(userPrefsRepo)
    )
}

data class AuthUseCases(
    val getAllAuth  : GetAllAuthUseCase,
    val createAuth  : CreateAuthUseCase,
    val updateAuth  : UpdateAuthUseCase,
    val deleteAuth  : DeleteAuthUseCase,
    val loginAuth   : LoginUseCase,
    val logout      : LogoutUseCase
)