package com.example.nebeng.feature_credit_score_log.di

import com.example.nebeng.feature_credit_score_log.data.remote.api.CreditScoreLogApi
import com.example.nebeng.feature_credit_score_log.data.repository.CreditScoreLogRepository
import com.example.nebeng.feature_credit_score_log.data.repository.CreditScoreLogRepositoryImpl
import com.example.nebeng.feature_credit_score_log.domain.usecase.CreateCreditScoreLogUseCase
import com.example.nebeng.feature_credit_score_log.domain.usecase.CreditScoreLogUseCases
import com.example.nebeng.feature_credit_score_log.domain.usecase.DeleteCreditScoreLogUseCase
import com.example.nebeng.feature_credit_score_log.domain.usecase.ReadAllCreditScoreLogUseCase
import com.example.nebeng.feature_credit_score_log.domain.usecase.ReadByDriverIdCreditScoreLogUseCase
import com.example.nebeng.feature_credit_score_log.domain.usecase.ReadByIdCreditScoreLogUseCase
import com.example.nebeng.feature_credit_score_log.domain.usecase.UpdateCreditScoreLogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CreditScoreLogModule {
    @Provides
    @Singleton
    fun provideCreditScoreLogApi(
        retrofit: Retrofit
    ): CreditScoreLogApi = retrofit.create(CreditScoreLogApi::class.java)

    @Provides
    @Singleton
    fun provideCreditScoreLogRepository(
        api: CreditScoreLogApi
    ): CreditScoreLogRepository = CreditScoreLogRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCreditScoreLogUseCases(
        repository: CreditScoreLogRepository
    ): CreditScoreLogUseCases {
        return CreditScoreLogUseCases(
            create = CreateCreditScoreLogUseCase(repository),
            readAll = ReadAllCreditScoreLogUseCase(repository),
            readById = ReadByIdCreditScoreLogUseCase(repository),
            readByDriverId = ReadByDriverIdCreditScoreLogUseCase(repository),
            update = UpdateCreditScoreLogUseCase(repository),
            delete = DeleteCreditScoreLogUseCase(repository)
        )
    }
}