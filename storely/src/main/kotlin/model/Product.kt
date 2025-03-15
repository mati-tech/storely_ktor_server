package com.mati.model

import com.mati.utils.BigDecimalSerializer
import com.mati.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.math.BigDecimal

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String?,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val stock: Int?,
    val categoryId: Int,
    val imageUrl: String?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? //Tells the serializer to use a registered serializer for LocalDateTime.
)
