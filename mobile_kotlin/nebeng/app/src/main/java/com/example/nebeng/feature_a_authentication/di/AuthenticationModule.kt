package com.example.nebeng.feature_a_authentication.di

import com.example.nebeng.feature_a_authentication.domain.usecase.AuthenticationUseCases
import com.example.nebeng.feature_a_authentication.domain.usecase.DeleteCustomerAccountUseCase
import com.example.nebeng.feature_a_authentication.domain.usecase.GetCustomerProfileUseCase
import com.example.nebeng.feature_a_authentication.domain.usecase.LoginCustomerUseCase
import com.example.nebeng.feature_a_authentication.domain.usecase.RegisterCustomerUseCase
import com.example.nebeng.feature_a_authentication.domain.usecase.UpdateCustomerProfileUseCase
import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import com.example.nebeng.feature_user.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationUseCases(
        authRepository: AuthRepository,
        userRepository: UserRepository,
        customerRepository: CustomerRepository
    ): AuthenticationUseCases {
        return AuthenticationUseCases(
            loginCustomer = LoginCustomerUseCase(authRepository, customerRepository),
            registerCustomer = RegisterCustomerUseCase(authRepository, customerRepository),
            getCustomerProfile = GetCustomerProfileUseCase(userRepository),
            updateCustomerProfile = UpdateCustomerProfileUseCase(userRepository),
            deleteCustomerAccount = DeleteCustomerAccountUseCase(userRepository)
        )
    }
}