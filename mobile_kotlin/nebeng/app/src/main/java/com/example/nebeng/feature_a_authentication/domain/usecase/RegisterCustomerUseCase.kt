package com.example.nebeng.feature_a_authentication.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_authentication.domain.mapper.toAuthenticationItem
import com.example.nebeng.feature_a_authentication.domain.mapper.withCustomerInfo
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import com.example.nebeng.feature_auth.data.remote.model.request.CreateRegisterRequest
import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//class RegisterCustomerUseCase @Inject constructor(
//    private val authRepository: AuthRepository
//) {
//
//    suspend operator fun invoke(
//        request: CreateRegisterRequest
//    ): Flow<Result<AuthenticationItem>> {
//        return authRepository.register(request).map { result ->
//            when (result) {
//                is Result.Loading -> Result.Loading
//                is Result.Error -> Result.Error(result.message)
//                is Result.Success -> Result.Success(result.data.toAuthenticationItem())
//            }
//        }
//    }
//}
class RegisterCustomerUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val customerRepository: CustomerRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        request: CreateRegisterRequest
    ): Flow<Result<AuthenticationItem>> {
        return authRepository.registerSummary(request).flatMapLatest { registerResult ->
            when (registerResult) {
                is Result.Loading -> flow { emit(Result.Loading) }
                is Result.Error -> flow { emit(Result.Error(registerResult.message)) }

                is Result.Success -> {
                    val baseAuth = registerResult.data.toAuthenticationItem()

                    // Ambil informasi customer berdasarkan userId setelah register
                    customerRepository.getUserCustomerByUserIdSummary(
                        token = baseAuth.token,
                        userId = baseAuth.userId
                    ).map { custResult ->
                        when (custResult) {
                            is Result.Loading -> Result.Loading
                            is Result.Error -> Result.Success(baseAuth) // tetap berhasil register meskipun fetch customer gagal
                            is Result.Success -> Result.Success(baseAuth.withCustomerInfo(custResult.data))
                        }
                    }
                }
            }
        }
    }
}
