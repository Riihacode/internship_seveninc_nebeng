package com.example.nebeng.feature_payment_method.di

import com.example.nebeng.feature_payment_method.data.remote.api.PaymentMethodApi
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepository
import com.example.nebeng.feature_payment_method.data.repository.PaymentMethodRepositoryImpl
import com.example.nebeng.feature_payment_method.domain.usecase.CreatePaymentMethodUseCase
import com.example.nebeng.feature_payment_method.domain.usecase.DeletePaymentMethodUseCase
import com.example.nebeng.feature_payment_method.domain.usecase.PaymentMethodUseCases
import com.example.nebeng.feature_payment_method.domain.usecase.ReadAllPaymentMethodUseCase
import com.example.nebeng.feature_payment_method.domain.usecase.ReadByIdPaymentMethodUseCase
import com.example.nebeng.feature_payment_method.domain.usecase.UpdatePaymentMethodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PaymentMethodModule {
    @Provides
    @Singleton
    fun providePaymentMethodApi(
        retrofit: Retrofit
    ): PaymentMethodApi = retrofit.create(PaymentMethodApi::class.java)

    @Provides
    @Singleton
    fun providePaymentMethodRepository(
        api: PaymentMethodApi
    ): PaymentMethodRepository = PaymentMethodRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePaymentMethodUseCase(
        repository: PaymentMethodRepository
    ): PaymentMethodUseCases  {
        return PaymentMethodUseCases(
            create = CreatePaymentMethodUseCase(repository),
            readAll = ReadAllPaymentMethodUseCase(repository),
            readById = ReadByIdPaymentMethodUseCase(repository),
            update = UpdatePaymentMethodUseCase(repository),
            delete = DeletePaymentMethodUseCase(repository)
        )
    }
}