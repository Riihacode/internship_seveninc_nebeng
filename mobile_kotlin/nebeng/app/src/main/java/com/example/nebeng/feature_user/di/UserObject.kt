package com.example.nebeng.feature_user.di

import com.example.nebeng.feature_user.data.remote.api.UserApi
import com.example.nebeng.feature_user.data.repository.UserRepository
import com.example.nebeng.feature_user.data.repository.UserRepositoryImpl
import com.example.nebeng.feature_user.domain.usecase.DeleteUserUseCase
import com.example.nebeng.feature_user.domain.usecase.ReadAlUserUseCase
import com.example.nebeng.feature_user.domain.usecase.ReadByIdUserUseCase
import com.example.nebeng.feature_user.domain.usecase.UpdateUserUseCase
import com.example.nebeng.feature_user.domain.usecase.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserObject {
    @Provides
    @Singleton
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(
        api: UserApi
    ): UserRepository = UserRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUserUseCases(
        repository: UserRepository
    ): UserUseCases {
        return UserUseCases(
            readAll = ReadAlUserUseCase(repository),
            readById = ReadByIdUserUseCase(repository),
            update = UpdateUserUseCase(repository),
            delete = DeleteUserUseCase(repository)
        )
    }
}