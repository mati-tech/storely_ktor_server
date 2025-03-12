package com.mati.db
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object ProductTable : IntIdTable("products") {
    val name = varchar("nameproducts", 45)
    val description = text("descriptionproducts").nullable()
    val price = decimal("priceproducts", 10, 2)
    val stock = integer("stockproducts").nullable()
    val categoryId = integer("categories_idcategories")
    val imageUrl = varchar("imageurlproducts", 2083).nullable()
    val createdAt = datetime("createdat").nullable()
}