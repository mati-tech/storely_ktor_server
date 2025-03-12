package com.mati.repositories

import com.mati.model.Basket

interface BasketRepository {
    suspend fun addToBasket(userId: Int, productId: Int, quantity: Int): Basket?
    suspend fun getBasketByUserId(userId: Int): List<Basket>
    suspend fun updateBasketItem(userId: Int, productId: Int, newQuantity: Int): Boolean
    suspend fun removeFromBasket(userId: Int, productId: Int): Boolean
    suspend fun clearBasket(userId: Int): Boolean
}
