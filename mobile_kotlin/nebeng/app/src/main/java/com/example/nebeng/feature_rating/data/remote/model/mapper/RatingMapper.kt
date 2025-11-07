package com.example.nebeng.feature_rating.data.remote.model.mapper

import com.example.nebeng.feature_rating.data.remote.model.dto.DataItemDto
import com.example.nebeng.feature_rating.data.remote.model.response.CreateRatingResponse
import com.example.nebeng.feature_rating.data.remote.model.response.UpdateRatingResponse
import com.example.nebeng.feature_rating.domain.model.Rating

/* ============================================================
   🔹 Mapper untuk DataItemDto → Rating (ReadAll, ReadById, ReadByDriverId)
   ============================================================ */
fun DataItemDto.toDomain(): Rating {
    return Rating(
        id = id,
        driverId = driverId,
        customerId = customerId,
        rating = rating,
        feedback = feedback,
        createdAt = createdAt
    )
}

/* ============================================================
   🔹 Mapper untuk CreateRatingResponse → Rating
   (karena response create berisi nested "data.rating")
   ============================================================ */
fun CreateRatingResponse.toDomain(): Rating {
    val r = data.rating
    return Rating(
        id = r.id,
        driverId = r.driverId,
        customerId = r.customerId,
        rating = r.rating,
        feedback = r.feedback,
        createdAt = r.createdAt
    )
}

/* ============================================================
   🔹 Mapper untuk UpdateRatingResponse → Rating
   (karena struktur response update agak berbeda)
   ============================================================ */
fun UpdateRatingResponse.toDomain(): Rating {
    val r = data
    return Rating(
        id = r.id,
        driverId = r.driverId,
        customerId = r.customerId,
        rating = r.rating,
        feedback = r.feedback,
        createdAt = r.createdAt
    )
}