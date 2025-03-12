package com.mati.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class Basket(
    val id: Int,
    val userId: Int,
    val productId: Int,
    val quantity: Int = 1,
    @Contextual val addedAt: LocalDateTime = LocalDateTime.now()
)
