package com.mati.model

import kotlinx.serialization.Serializable

@Serializable
data class Favorite(
    val id: Int,
    val userId: Int,          // Foreign Key: users_idusers
    val productId: Int       // Foreign Key: products_idproducts
)