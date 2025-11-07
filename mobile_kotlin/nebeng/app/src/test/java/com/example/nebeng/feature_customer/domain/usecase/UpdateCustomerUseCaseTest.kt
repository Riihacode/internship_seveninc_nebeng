package com.example.nebeng.feature_customer.domain.usecase

import com.example.nebeng.core.common.Result
import com.example.nebeng.feature_customer.data.remote.model.request.UpdateCustomerRequest
import com.example.nebeng.feature_customer.data.repository.CustomerRepository
import com.example.nebeng.feature_customer.domain.model.Customer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateCustomerUseCaseTest {
    @Mock
    private lateinit var repository: CustomerRepository

    private lateinit var useCase: UpdateCustomerUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = UpdateCustomerUseCase(repository)
    }

    @Test
    fun `invoke should emit Success when customer updated`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        // 🔹 Wajib isi semua field yang ada di UpdateCustomerRequest
        val request = UpdateCustomerRequest(
            profileImg        = "updated_profile.png",
            faceImg           = "updated_face.png",
            idCardImg         = "updated_ktp.png",
            fullName          = "Updated Riiha",
            idCardFullname    = "Updated Riiha",
            verified          = true,
            creditAmount      = 150_000,
            telephone         = "081999888",
            fullAddress       = "Jogja, Indonesia",
            idCardNumber      = "999999999",
            faceWithIdImg     = "updated_face_with_id.png",
            idCardBirthdate   = "2000-01-01"
        )

        val updatedCustomer = Customer(
            id = 1,
            userId = 1,
            fullName = "Updated Riiha",
            telephone = "081999888",
            fullAddress = "Jogja, Indonesia",
            profileImg = "updated_profile.png",
            verified = true,
            faceImg = "updated_face.png",
            faceWithIdImg = "updated_face_with_id.png",
            idCardImg = "updated_ktp.png",
            idCardNumber = "999999999",
            idCardFullName = "Updated Riiha",
            idCardBirthdate = "2000-01-01",
            creditAmount = 150_000,
//            user = null,
            createdAt = "2025-08-01",
            updatedAt = "2025-08-01"
        )

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Success(updatedCustomer))
        }

        whenever(repository.updateCustomer(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Success)
        assertEquals(
            "Updated Riiha",
            (results.last() as Result.Success).data.fullName
        )
    }

    @Test
    fun `invoke should emit Error when update fails`() = runTest(UnconfinedTestDispatcher()) {
        val token = "token"
        val id = 1

        val request = UpdateCustomerRequest(
            profileImg        = "broken.png",
            faceImg           = "broken_face.png",
            idCardImg         = "broken_ktp.png",
            fullName          = "Broken",
            idCardFullname    = "Broken",
            verified          = false,
            creditAmount      = 0,
            telephone         = "",
            fullAddress       = "",
            idCardNumber      = "0",
            faceWithIdImg     = "broken_face_with_id.png",
            idCardBirthdate   = "1900-01-01"
        )

        val flow: Flow<Result<Customer>> = flow {
            emit(Result.Loading)
            emit(Result.Error("Update failed"))
        }

        whenever(repository.updateCustomer(token, id, request)).thenReturn(flow)

        val results = mutableListOf<Result<Customer>>()
        useCase(token, id, request).collect { results.add(it) }

        assertTrue(results.first() is Result.Loading)
        assertTrue(results.last() is Result.Error)
    }
}