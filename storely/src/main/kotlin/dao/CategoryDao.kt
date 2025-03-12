package com.mati.dao

import com.mati.db.CategoryTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryDao>(CategoryTable)

    var name by CategoryTable.name
}
