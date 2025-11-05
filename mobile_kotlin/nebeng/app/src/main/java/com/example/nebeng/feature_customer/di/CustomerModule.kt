package com.example.nebeng.feature_customer.di

import com.example.nebeng.feature_customer.data.remote.api.CustomerApi
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import com.example.nebeng.feature_customer.data.repository.CustomerRepositoryImpl
import com.example.nebeng.feature_customer.domain.usecase.CreateCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.CustomerUseCases
import com.example.nebeng.feature_customer.domain.usecase.DeleteCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.PatchAddCreditAmountCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.PatchAutoVerifyTrueCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.PatchDeductCreditAmountCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.ReadAllCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.ReadByIdCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.ReadByUserIdCustomerUseCase
import com.example.nebeng.feature_customer.domain.usecase.UpdateCustomerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CustomerModule {
    @Provides
    @Singleton
    fun provideCustomerApi(
        retrofit: Retrofit
    ): CustomerApi = retrofit.create(CustomerApi::class.java)

    @Provides
    @Singleton
    fun provideCustomerRepository(
        api: CustomerApi
    ): CustomerRepository = CustomerRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCustomerUseCase(
        repository: CustomerRepository
    ): CustomerUseCases {
        return CustomerUseCases(
            create                  = CreateCustomerUseCase(repository),
            readAll                 = ReadAllCustomerUseCase(repository),
            readById                = ReadByIdCustomerUseCase(repository),
            readByUserId            = ReadByUserIdCustomerUseCase(repository),
            update                  = UpdateCustomerUseCase(repository),
            patchAddCreditAmount    = PatchAddCreditAmountCustomerUseCase(repository),
            patchAutoVerifyTrue     = PatchAutoVerifyTrueCustomerUseCase(repository),
            patchDeductCreditAmount = PatchDeductCreditAmountCustomerUseCase(repository),
            delete                  = DeleteCustomerUseCase(repository)
        )
    }
}