package com.example.nebeng.feature_a_authentication.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_a_authentication.domain.mapper.toAuthenticationItem
import com.example.nebeng.feature_a_authentication.domain.mapper.withCustomerInfo
import com.example.nebeng.feature_a_authentication.domain.model.AuthenticationItem
import com.example.nebeng.feature_auth.data.remote.model.request.CreateLoginRequest
import com.example.nebeng.feature_auth.data.repository.AuthRepository
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//class LoginCustomerUseCase @Inject constructor(
//    private val authRepository: AuthRepository
//) {
//
//    suspend operator fun invoke(
//        request: CreateLoginRequest
//    ): Flow<Result<AuthenticationItem>> {
//        return authRepository.login(request).map { result ->
//            when (result) {
//                is Result.Loading -> Result.Loading
//                is Result.Error -> Result.Error(result.message)
//                is Result.Success -> Result.Success(result.data.toAuthenticationItem())
//            }
//        }
//    }
//}

class LoginCustomerUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val customerRepository: CustomerRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(
        request: CreateLoginRequest
    ): Flow<Result<AuthenticationItem>> {
        return authRepository.loginSummary(request).flatMapLatest { loginResult ->
            when (loginResult) {
                is Result.Loading -> flow { emit(Result.Loading) }

                is Result.Error -> flow { emit(Result.Error(loginResult.message)) }

                is Result.Success -> {
                    val baseAuth = loginResult.data.toAuthenticationItem()

                    // ⚡ Tahap 2 — ambil customer berdasarkan userId
                    customerRepository.getCustomerByUserIdSummary(
                        token = baseAuth.token,
                        userId = baseAuth.userId
                    ).map { customerResult ->
                        when (customerResult) {
                            is Result.Loading -> Result.Loading
                            is Result.Error -> {
                                // DEBUG
                                android.util.Log.d(
                                    "LOGIN_USECASE",
                                    "getCustomerByUserIdSummary ERROR: ${customerResult.message}"
                                )
                                // tetap login, tapi tanpa customerId
                                Result.Success(data = baseAuth)
                            }

                            is Result.Success -> {
                                android.util.Log.d(
                                    "LOGIN_USECASE",
                                    "getCustomerByUserIdSummary OK: id=${customerResult.data.customerId}, name=${customerResult.data.customerName}"
                                )
                                val enriched = baseAuth.withCustomerInfo(summary = customerResult.data)
                                Result.Success(data = enriched)
                            }
                        }
                    }
                }
            }
        }
    }
}