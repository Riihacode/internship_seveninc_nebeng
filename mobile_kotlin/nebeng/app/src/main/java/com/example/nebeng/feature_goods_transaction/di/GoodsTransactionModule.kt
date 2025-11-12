package com.example.nebeng.feature_goods_transaction.di

import com.example.nebeng.feature_goods_transaction.data.remote.api.GoodsTransactionApi
import com.example.nebeng.feature_goods_transaction.data.repository.GoodsTransactionRepository
import com.example.nebeng.feature_goods_transaction.data.repository.GoodsTransactionRepositoryImpl
import com.example.nebeng.feature_goods_transaction.domain.usecase.CreateGoodsTransactionUseCase
import com.example.nebeng.feature_goods_transaction.domain.usecase.DeleteGoodsTransactionUseCase
import com.example.nebeng.feature_goods_transaction.domain.usecase.GoodsTransactionUseCases
import com.example.nebeng.feature_goods_transaction.domain.usecase.ReadAllGoodsTransactionUseCase
import com.example.nebeng.feature_goods_transaction.domain.usecase.ReadByIdGoodsTransactionUseCase
import com.example.nebeng.feature_goods_transaction.domain.usecase.UpdateGoodsTransactionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoodsTransactionModule {
    @Provides
    @Singleton
    fun provideGoodsTransactionApi(
        retrofit: Retrofit
    ): GoodsTransactionApi = retrofit.create(GoodsTransactionApi::class.java)

    @Provides
    @Singleton
    fun provideGoodsTransactionRepository(
        api: GoodsTransactionApi
    ): GoodsTransactionRepository = GoodsTransactionRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGoodsTransactionUseCases(
        repository: GoodsTransactionRepository
    ): GoodsTransactionUseCases {
        return GoodsTransactionUseCases(
            create      = CreateGoodsTransactionUseCase(repository),
            readAll     = ReadAllGoodsTransactionUseCase(repository),
            readById    = ReadByIdGoodsTransactionUseCase(repository),
            update      = UpdateGoodsTransactionUseCase(repository),
            delete      = DeleteGoodsTransactionUseCase(repository)
        )
    }
}