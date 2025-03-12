package com.mati.repositories.impl

import com.mati.dao.FavoriteDao
import com.mati.db.FavoriteTable
import com.mati.model.Favorite
import com.mati.repositories.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class FavPostgresRepo : FavoriteRepository {

    override suspend fun addFavorite(userId: Int, productId: Int): Favorite? {
        return newSuspendedTransaction(Dispatchers.IO) {
            FavoriteDao.new {
                this.userId = userId
                this.productId = productId
            }.toModel()
        }
    }

    override suspend fun removeFavorite(userId: Int, productId: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            FavoriteDao.find { (FavoriteTable.userId eq userId) and (FavoriteTable.productId eq productId) }
                .firstOrNull()?.let {
                    it.delete()
                    true
                } ?: false
        }
    }

    override suspend fun getFavoritesByUser(userId: Int): List<Favorite> {
        return newSuspendedTransaction(Dispatchers.IO) {
            FavoriteDao.find { FavoriteTable.userId eq userId }
                .map { it.toModel() }
        }
    }

    private fun FavoriteDao.toModel() = Favorite(
        id.value,
        userId,
        productId
    )
}
