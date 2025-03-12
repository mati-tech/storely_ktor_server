package com.mati.dao

import com.mati.db.FavoriteTable
import com.mati.model.Favorite
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class FavoriteDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FavoriteDao>(FavoriteTable)
    var userId by FavoriteTable.userId
    var productId by FavoriteTable.productId
}