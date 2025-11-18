package com.example.nebeng.feature_auth.di

import com.example.nebeng.core.session.data.SessionRepositoryImpl
import com.example.nebeng.core.session.data.UserPreferencesRepository
import com.example.nebeng.core.session.domain.SessionRepository
import com.example.nebeng.feature_auth.domain.usecase.AuthUseCases
import com.example.nebeng.feature_auth.domain.usecase.CreateLoginUseCase
import com.example.nebeng.feature_auth.domain.usecase.CreateLogoutUseCase
import com.example.nebeng.feature_auth.domain.usecase.CreateRegisterUseCase
import com.example.nebeng.feature_auth.data.remote.api.AuthApi
import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_auth.data.repository.AuthRepositoryImpl
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
        api: AuthApi
    ): AuthRepository {
        // Contoh palai versi lokal dulu, nanti bisa ditambah / diganti api kalau udah siap
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(
        userPrefsRepo: UserPreferencesRepository
    ): SessionRepository {
        return SessionRepositoryImpl(userPrefsRepo)
    }

//    @Provides
//    @Singleton
//    fun provideProfileInteractor(
//        authUseCases: AuthUseCases
//    ): ProfileUseCases {
//        return ProfileUseCases(
//            getAllAuth = authUseCases.getAllAuth,
//            updateAuth = authUseCases.updateAuth,
//            deleteAuth = authUseCases.deleteAuth,
//            logout = authUseCases.logout
//        )
//    }

//    @Provides
//    @Singleton
//    fun provideAuthUseCase(
//        repository: AuthRepository,
//        sessionRepository: SessionRepository
//    ): AuthUseCases {
//        return AuthUseCases(
//            getAllAuth  = GetAllAuthUseCase(repository),
//            createAuth  = CreateAuthUseCase(repository),
//            updateAuth  = UpdateAuthUseCase(repository),
//            deleteAuth  = DeleteAuthUseCase(repository),
//            loginAuth   = LoginUseCase(repository),
//            logout      = LogoutUseCase(sessionRepository)
//        )
//    }
    @Provides
    @Singleton
    fun provideAuthUseCases(
        repository: AuthRepository
    ): AuthUseCases {
        return AuthUseCases(
            login = CreateLoginUseCase(repository),
            register = CreateRegisterUseCase(repository),
            logout = CreateLogoutUseCase(repository)
        )
    }
}