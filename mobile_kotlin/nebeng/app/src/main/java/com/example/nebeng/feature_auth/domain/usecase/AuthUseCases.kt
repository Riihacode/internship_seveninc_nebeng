package com.example.nebeng.feature_auth.domain.usecase

//data class AuthUseCases(
//    val getAllAuth : GetAllAuthUseCase,
//    val createAuth: CreateAuthUseCase,
//    val updateAuth: UpdateAuthUseCase,
//    val deleteAuth: DeleteAuthUseCase,
//    val loginAuth: LoginUseCase,
//    val logout: LogoutUseCase
//)
data class AuthUseCases(
    val login: CreateLoginUseCase,
    val register: CreateRegisterUseCase,
    val logout: CreateLogoutUseCase
)