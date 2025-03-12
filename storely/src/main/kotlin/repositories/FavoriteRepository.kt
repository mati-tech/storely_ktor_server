package com.mati.repositories

import com.mati.model.Favorite
import com.mati.model.Product

interface FavoriteRepository {
    suspend fun addFavorite(userId: Int, productId: Int): Favorite?
    suspend fun removeFavorite(userId: Int, productId: Int): Boolean
    suspend fun getFavoritesByUser(userId: Int): List<Favorite>
}

