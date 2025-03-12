package com.mati.repositories.impl

import com.mati.dao.BasketDao
import com.mati.db.BasketTable
import com.mati.model.Basket
import com.mati.repositories.BasketRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime

//class BasketPostgresRepo(private val db: Database) {
//
//    suspend fun addToBasket(basket: Basket): Basket {
//        return newSuspendedTransaction(Dispatchers.IO) {
//            val basketDAO = BasketDao.new {
//                userId = basket.userId
//                productId = basket.productId
//                quantity = basket.quantity
//                addedAt = basket.addedAt
//            }
//            daoToModel(basketDAO)
//        }
//    }
//
//    suspend fun getBasketForUser(userId: Int): List<Basket> {
//        return newSuspendedTransaction(Dispatchers.IO) {
//            BasketDao.find { BasketTable.userId eq userId }.map { daoToModel(it) }
//        }
//    }
//
//    suspend fun getBasketItem(userId: Int, productId: Int): Basket? {
//        return newSuspendedTransaction(Dispatchers.IO) {
//            BasketDao.find { (BasketTable.userId eq userId) and (BasketTable.productId eq productId) }
//                .singleOrNull()?.let { daoToModel(it) }
//        }
//    }
//
//    suspend fun removeFromBasket(basketId: Int) {
//        return newSuspendedTransaction(Dispatchers.IO) {
//            BasketDao[basketId].delete()
//        }
//    }
//    override suspend fun updateBasketItem(userId: Int, productId: Int, newQuantity: Int): Boolean {
//        return newSuspendedTransaction(Dispatchers.IO) {
//            BasketDao.find { (BasketTable.userId eq userId) and (BasketTable.productId eq productId) }
//                .firstOrNull()?.let {
//                    it.quantity = newQuantity
//                    true
//                } ?: false
//        }
//    }
//    // Map DAO to Model
//    private fun daoToModel(dao: BasketDao) = Basket(
//        dao.id.value,
//        dao.userId,
//        dao.productId,
//        dao.quantity,
//        dao.addedAt
//    )
//}

class BasketPostgresRepo : BasketRepository {

    override suspend fun addToBasket(userId: Int, productId: Int, quantity: Int): Basket? {
        return newSuspendedTransaction(Dispatchers.IO) {
            BasketDao.find { (BasketTable.userId eq userId) and (BasketTable.productId eq productId) }
                .firstOrNull()?.apply {
                    this.quantity += quantity
                } ?: BasketDao.new {
                this.userId = userId
                this.productId = productId
                this.quantity = quantity
                this.addedAt = LocalDateTime.now()
            }
        }?.toModel()
    }

    override suspend fun getBasketByUserId(userId: Int): List<Basket> {
        return newSuspendedTransaction(Dispatchers.IO) {
            BasketDao.find { BasketTable.userId eq userId }
                .map { it.toModel() }
        }
    }

    override suspend fun updateBasketItem(userId: Int, productId: Int, newQuantity: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            BasketDao.find { (BasketTable.userId eq userId) and (BasketTable.productId eq productId) }
                .firstOrNull()?.let {
                    it.quantity = newQuantity
                    true
                } ?: false
        }
    }

    override suspend fun removeFromBasket(userId: Int, productId: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            BasketDao.find { (BasketTable.userId eq userId) and (BasketTable.productId eq productId) }
                .firstOrNull()?.let {
                    it.delete()
                    true
                } ?: false
        }
    }

    override suspend fun clearBasket(userId: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            BasketDao.find { BasketTable.userId eq userId }
                .forEach { it.delete() }
            true
        }
    }

    private fun BasketDao.toModel() = Basket(
        id.value,
        userId,
        productId,
        quantity,
        addedAt
    )
}
