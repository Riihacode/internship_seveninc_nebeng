package com.example.nebeng.feature_a_homepage.domain.usecase

import com.example.nebeng.feature_driver.data.repository.DriverRepository
import javax.inject.Inject

class GetByIdDriverUseCase @Inject constructor(
    private val repository: DriverRepository
) {
}